package com.codegym.controller;

import com.codegym.config.sercurity.JwtTokenUtil;
import com.codegym.dto.CustomerDto;
import com.codegym.model.*;
import com.codegym.security.userprinciple.AccountPrinciple;
import com.codegym.service.ICustomerService;
import com.codegym.service.sercurity.impl.AccountServiceImpl;
import com.codegym.service.sercurity.impl.RoleServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class SignInController {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JavaMailSender javaMailSender;
    @PostMapping(value = "api/signUp")
    public ResponseEntity<?> register(@Valid @RequestBody SignForm signForm) {
//        new SignForm().validate(signForm, bindingResult);
//        System.out.println(bindingResult.getAllErrors());
        CustomerDto customerDto = new CustomerDto();
        System.out.println("sign up");
        if (accountService.existAccountByPhone(signForm.getPhone())) {
            return new ResponseEntity<>("duplicate phone", HttpStatus.BAD_REQUEST);
        }
        if (accountService.existAccountByIdCard(signForm.getIdCard())) {
            return new ResponseEntity<>("duplicate idCard", HttpStatus.BAD_REQUEST);
        }
        if (accountService.existAccountByEmail(signForm.getEmail())) {
            return new ResponseEntity<>("duplicate email", HttpStatus.BAD_REQUEST);
        }
        Account acc = new Account();
        BeanUtils.copyProperties(signForm, acc);
        acc.setPassword(passwordEncoder.encode(signForm.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(3L, RoleName.ROLE_CUSTOMER));
        if(acc.getEmail().equals("admin@gmail.com")){
            roleSet.add(new Role(2L, RoleName.ROLE_EMPLOYEE));
            roleSet.add(new Role(1L, RoleName.ROLE_ADMIN));
        }
        if(acc.getEmail().equals("employee@gmail.com")){
            roleSet.add(new Role(2L, RoleName.ROLE_EMPLOYEE));
        }
        acc.setRoles(roleSet);
        // iCustomerRepository.saveCustomer(
        //                customerDto.getNameCustomer(),
        //                customerDto.getPhoneCustomer(),
        //                customerDto.getGenderCustomer(),
        //                customerDto.getEmailCustomer(),
        //                customerDto.getIdCardCustomer(),
        //                customerDto.getBirthdayCustomer(),
        //                customerDto.getAddressCustomer(),
        //                customerDto.getCustomerType(),
        //                customerDto.getCountries(),
        //                false);
        customerDto.setNameCustomer(acc.getFullName());
        customerDto.setPhoneCustomer(acc.getPhone());
        customerDto.setBirthdayCustomer(acc.getBirthday());
        customerDto.setGenderCustomer(acc.getGender());
        customerDto.setEmailCustomer(acc.getEmail());
        customerDto.setIdCardCustomer(acc.getIdCard());
        customerDto.setAddressCustomer(acc.getAddress());
        customerDto.setCustomerType(4L);
        customerDto.setCountries(acc.getCountry().getId());
        customerService.save(customerDto);
        accountService.save(acc);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }

    @PostMapping("api/signIn")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        System.out.println("sign in");
        Authentication authentication;
        if (!accountService.existAccountByEmail(user.getEmail())) {
            return new ResponseEntity<>("emailError", HttpStatus.BAD_REQUEST);
        }
        try{
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        }catch (Exception e){
            return new ResponseEntity<>("passwordError",HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        return new ResponseEntity<>(new JwtReponse(token, accountPrinciple.getEmail(), accountPrinciple.getAuthorities()), HttpStatus.OK);
    }

    @PostMapping("api/forgetPassword")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody PasswordForgettedForm passwordForgettedForm){
        SimpleMailMessage message = new SimpleMailMessage();
        if(accountService.existAccountByEmail(passwordForgettedForm.getEmail())){
            Account account = accountService.findAccountByEmail(passwordForgettedForm.getEmail());
            account.setPassword(passwordEncoder.encode(passwordForgettedForm.getPassword()));
            account.setConfirmPassword(passwordForgettedForm.getConfirmPassword());
            accountService.save(account);
            message.setTo(passwordForgettedForm.getEmail());
            message.setSubject("Thay đổi mật khẩu");
            message.setText("Mật khẩu của bạn đã ở website C1021G1 Flight đã được thay đôi thành "+ passwordForgettedForm.getPassword());
            javaMailSender.send(message);
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }else  return new ResponseEntity<>("Email không tồn taị",HttpStatus.BAD_REQUEST);


    }

    @PostMapping("api/changePassword")
    public ResponseEntity<?> forgetPassword(@RequestBody ChangingPassword changingPassword){
        if(accountService.existAccountByEmail(changingPassword.getEmail())&&
        accountService.existAccountByPassword(changingPassword.getPassword())){
            Account account = accountService.findAccountByEmail(changingPassword.getEmail());
            account.setPassword(passwordEncoder.encode(changingPassword.getNewPassword()));
            account.setConfirmPassword(changingPassword.getConfirmPassword());
            accountService.save(account);
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }else  return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);

    }

    @PatchMapping("/api/changePassword")
    public ResponseEntity <?> changePassword (@RequestBody PasswordForgettedForm passwordForgettedForm) {
        if (accountService.existAccountByEmail(passwordForgettedForm.getEmail())
                && (accountService.existAccountByPassword(passwordForgettedForm.getPassword()))) {
            Account account = accountService.findAccountByEmail(passwordForgettedForm.getEmail());
            account.setPassword(passwordEncoder.encode(passwordForgettedForm.getPassword()));
            accountService.save(account);
        }
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

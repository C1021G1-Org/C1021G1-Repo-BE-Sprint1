package com.codegym.controller;

import com.codegym.config.sercurity.JwtTokenUtil;
import com.codegym.model.*;
import com.codegym.security.userprinciple.AccountPrinciple;
import com.codegym.service.sercurity.impl.AccountServiceImpl;
import com.codegym.service.sercurity.impl.RoleServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    RoleServiceImpl roleService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "signUp")
    public ResponseEntity<?> register(@Valid @RequestBody SignForm signForm) {
//        new SignForm().validate(signForm, bindingResult);
//        System.out.println(bindingResult.getAllErrors());

        if (accountService.existAccountByEmail(signForm.getEmail())) {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
        Account acc = new Account();
        BeanUtils.copyProperties(signForm, acc);
        acc.setPassword(passwordEncoder.encode(signForm.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(3L, RoleName.CUSTOMER));
        acc.setRoles(roleSet);
        accountService.save(acc);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        return new ResponseEntity<>(new JwtReponse(token, accountPrinciple.getEmail(), accountPrinciple.getAuthorities()), HttpStatus.OK);
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

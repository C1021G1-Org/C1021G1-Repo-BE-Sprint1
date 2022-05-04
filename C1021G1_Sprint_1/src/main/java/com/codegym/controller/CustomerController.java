package com.codegym.controller;

import com.codegym.dto.CustomerDto;
import com.codegym.dto.CustomerPersonalInfoDto;
import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import com.codegym.model.CustomerType;
import com.codegym.service.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICustomerTypeService iCustomerTypeService;

    /*TinhHD tao thông tinh khách hàng bời nhân viên */
    @PostMapping("/create")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
//        }
        iCustomerService.save(customerDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /*TinhHD tìm id customer */
    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        Customer customer =iCustomerService.findById(id);
        if (customer==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /*TinhHD cập nhật thông tinh khách hàng bời nhân viên */
    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer( @PathVariable Long id,@Valid @RequestBody CustomerDto customerDto) {

        customerDto.setId(id);
        iCustomerService.update(customerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//
//    @PutMapping("/{id}")
//    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
//        Optional<Customer> customerOptional = Optional.ofNullable(iCustomerService.findById(id));
//        if (!customerOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        customer.setId(customerOptional.get().getId());
//        return new ResponseEntity<>(iCustomerService.save(customer), HttpStatus.OK);
//    }

    /*LongLT hiển thị list khách hàng*/
    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getAllCustomer(@PageableDefault(size = 10) Pageable pageable) {
        Page<Customer> customers = iCustomerService.findAllCustomer(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /*LongLT hiển thị list phân loại khách hàng */
    @GetMapping("/customerType")
    public ResponseEntity<List<CustomerType>> getAllCustomerType() {
        List<CustomerType> customerTypes = iCustomerTypeService.findAll();
        if (customerTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerTypes, HttpStatus.OK);
    }

    /*LongLT xoa customer */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Customer customers = iCustomerService.findById(id);
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCustomerService.remove(id);
        return new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
    }

    /*LongLT search customer */
    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(defaultValue = "") String keyword) {
        List<Customer> customerList = iCustomerService.searchCustomer(keyword);
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    /*ThangDBX lấy thông tin cá nhân của khách hàng */
    @GetMapping("view/{id}")
    public ResponseEntity<Customer> findCustomerPersonalInfoById(@PathVariable("id") Long id){
        Customer customer = iCustomerService.findCustomerById(id);
        if (customer != null){
            return new ResponseEntity<>(customer,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    /* ThangDBX cập nhật thông tin bản thân khách hàng  */
//    @GetMapping("customer/edit/{id}")
//    public ResponseEntity<Customer> getCustomerPersonal(@PathVariable("id") Long id){
//        Customer customer = customerService.findCustomerById(id);
//
//        if (customer != null){
//            CustomerPersonalInfoDto customerDto = new CustomerPersonalInfoDto();
//            BeanUtils.copyProperties(customer,customerDto);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>( customer ,HttpStatus.NOT_FOUND);
//        }
//
//    }

    /* ThangDBX cập nhật thông tin bản thân khách hàng  */
    @PatchMapping("edit/{id}")
    public ResponseEntity<?> updateCustomerPersonalInfo(@PathVariable Long id,
                                                        @Validated
                                                        @RequestBody CustomerPersonalInfoDto customerDto,
                                                        BindingResult bindingResult){
//        new CustomerPersonalInfoDto().validate(customerDto,bindingResult);

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>( bindingResult.getFieldError() ,HttpStatus.NOT_FOUND);
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto,customer);
            iCustomerService.updatePersonalInfo(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}

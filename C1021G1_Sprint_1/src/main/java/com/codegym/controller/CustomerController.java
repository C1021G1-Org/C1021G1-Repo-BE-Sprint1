package com.codegym.controller;

import com.codegym.dto.CustomerDto;
import com.codegym.dto.CustomerDtoCheck;
import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;


import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import com.codegym.service.ICustomerService;
import com.codegym.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICustomerTypeService iCustomerTypeService;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        Customer customer = iCustomerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDtoCheck customerDtoCheck) {


        System.out.println(customerDtoCheck.getCountries().toString());
        System.out.println(customerDtoCheck.getCustomerType().toString());
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        customerDto.setGenderCustomer(customerDtoCheck.getGenderCustomer());
        customerDto.setNameCustomer(customerDtoCheck.getNameCustomer());
        customerDto.setBirthdayCustomer(customerDtoCheck.getBirthdayCustomer());
        customerDto.setIdCardCustomer(customerDtoCheck.getIdCardCustomer());
        customerDto.setGenderCustomer(customerDtoCheck.getGenderCustomer());
        customerDto.setPhoneCustomer(customerDtoCheck.getPhoneCustomer());
        customerDto.setEmailCustomer(customerDtoCheck.getEmailCustomer());
        customerDto.setAddressCustomer(customerDtoCheck.getAddressCustomer());
        customerDto.setCountries(customerDtoCheck.getCountries().getId());
        customerDto.setCustomerType(customerDtoCheck.getCustomerType().getId());
        iCustomerService.update(customerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getAllCustomer(@PageableDefault(size = 10) Pageable pageable) {
        Page<Customer> customers = iCustomerService.findAllCustomer(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customerType")
    public ResponseEntity<List<CustomerType>> getAllCustomerType() {
        List<CustomerType> customerTypes = iCustomerTypeService.findAll();
        if (customerTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerTypes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Customer customers = iCustomerService.findById(id);
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCustomerService.remove(id);
        return new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(defaultValue = "") String keyword) {
        List<Customer> customerList = iCustomerService.searchCustomer(keyword);
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

}

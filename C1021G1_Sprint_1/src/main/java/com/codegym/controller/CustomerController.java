package com.codegym.controller;

import com.codegym.dto.CustomerDto;
import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;


import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import com.codegym.service.ICustomerService;
import com.codegym.service.ICustomerTypeService;
import org.assertj.core.internal.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@CrossOrigin(origins = "*")
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

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {

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

    //*LongLT* triển khai lấy list customer
    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>> getAllCustomer(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Customer> customers = iCustomerService.findAllCustomer(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/customer-not-pagination")
    public ResponseEntity<List<Customer>> getAllCustomerNotPagination() {
        List<Customer> vaccines = iCustomerService.getAllCustomerNotPagination();
        if (vaccines.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @GetMapping("/customerType-list")
    public ResponseEntity<List<CustomerType>> getAllCustomerType() {
        List<CustomerType> customerTypes = iCustomerTypeService.findAll();
        if (customerTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerTypes, HttpStatus.OK);
    }

    //*LongLT* Triển khai phương thức xóa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Customer customers = iCustomerService.findById(id);
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCustomerService.remove(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    //*LongLT* Triển khai phương thức tìm kiếm
    @GetMapping("/search")
    public ResponseEntity<Page<Customer>> searchCustomer(@RequestParam(defaultValue = "", required = false) String keyword,
                                                         @RequestParam(defaultValue = "", required = false) String option,
                                                         @RequestParam(defaultValue = "0") int page) {
//
        Page<Customer> customerList = null;
        switch (option) {
            case "name":
                customerList = iCustomerService.searchCustomerByName(keyword, PageRequest. of(page, 10));
                break;
            case "email":
                customerList = iCustomerService.searchCustomerByEmail(keyword, PageRequest.of(page, 10));
                break;
            case "address":
                customerList = iCustomerService.searchCustomerByAddress(keyword, PageRequest.of(page, 10));
                break;
            case "country":
                customerList = iCustomerService.searchCustomerByCountry(keyword, PageRequest.of(page, 10));
                break;
            case "customerType":
                customerList = iCustomerService.searchCustomerByCustomerType(keyword, PageRequest.of(page, 10));
                break;
            case "idCard":
                customerList = iCustomerService.searchCustomerByIdCrad(keyword, PageRequest.of(page, 10));
                break;
            case "phone":
                customerList = iCustomerService.searchCustomerByPhone(keyword, PageRequest.of(page, 10));
                break;
        }
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        }
    }


}

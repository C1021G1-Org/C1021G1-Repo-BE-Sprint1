package com.codegym.controller;


import com.codegym.dto.CustomerDto;
import com.codegym.dto.CustomerDtoCheck;
import com.codegym.dto.CustomerPersonalInfoDto;
import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.web.bind.annotation.*;


import com.codegym.model.CustomerType;
import com.codegym.service.ICustomerTypeService;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "api/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICustomerTypeService iCustomerTypeService;

    /*TinhHD tao thông tinh khách hàng bời nhân viên */
    @PostMapping("/create")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {

//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
//        }
        iCustomerService.save(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // TinhHD validator
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
        Customer customer = iCustomerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /*TinhHD cập nhật thông tinh khách hàng bời nhân viên */
    @PatchMapping({"/{id}"})
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDtoCheck
            customerDtoCheck) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        if (iCustomerService.findByEmailNot(customerDtoCheck.getId(), customerDtoCheck.getEmailCustomer()) == 0
                && iCustomerService.findByPhoneNot(customerDtoCheck.getId(), customerDtoCheck.getPhoneCustomer()) == 0
                && iCustomerService.findByIdCardNot(customerDtoCheck.getId(), customerDtoCheck.getIdCardCustomer()) == 0) {
            BeanUtils.copyProperties(customerDtoCheck, customerDto);
            customerDto.setCountries(customerDtoCheck.getCountries().getId());
            customerDto.setCustomerType(customerDtoCheck.getCustomerType().getId());
            iCustomerService.update(customerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        Map<String, String> errors = new HashMap<>();
        if (iCustomerService.findByEmailNot(customerDtoCheck.getId(), customerDtoCheck.getEmailCustomer()) > 0) {
            errors.put("emailCustomer", "Email đã tồn tại!");
        }
        if (iCustomerService.findByPhoneNot(customerDtoCheck.getId(), customerDtoCheck.getPhoneCustomer()) > 0) {
            errors.put("phoneCustomer", "Số điện thoại đã tồn tại!");
        }

        if (iCustomerService.findByIdCardNot(customerDtoCheck.getId(), customerDtoCheck.getIdCardCustomer()) > 0) {
            errors.put("idCardCustomer", "CMND đã tồn tại!");
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }


    /*LongLT hiển thị list khách hàng*/
    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>> getAllCustomer(@RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page, 10);
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

        Page<Customer> customerList = null;
        switch (option) {
            case "name":
                customerList = iCustomerService.searchCustomerByName(keyword, PageRequest.of(page, 10));
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

    /*ThangDBX lấy thông tin cá nhân của khách hàng */
    @GetMapping("view/{id}")
    public ResponseEntity<Customer> findCustomerPersonalInfoById(@PathVariable("id") Long id) {
        Customer customer = iCustomerService.findByIdPersonal(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }


    /* ThangDBX cập nhật thông tin bản thân khách hàng  */
    @PatchMapping("edit/{id}")
    public ResponseEntity<?> updateCustomerPersonalInfo(@PathVariable Long id,
                                                        @Validated
                                                        @RequestBody CustomerPersonalInfoDto customerDto,
                                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.NOT_FOUND);
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            iCustomerService.updatePersonalInfo(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}


package com.codegym.controller;

import com.codegym.dto.CustomerDto;

import com.codegym.dto.CustomerDtoCheck;

import com.codegym.dto.CustomerPersonalInfoDto;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import com.codegym.model.CustomerType;
import com.codegym.service.ICustomerTypeService;

import org.assertj.core.internal.Iterables;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


@CrossOrigin("http://localhost:4200")

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
        Customer customer = iCustomerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }





    /*TinhHD cập nhật thông tinh khách hàng bời nhân viên */


    @PatchMapping({"/{id}"})
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDtoCheck
            customerDtoCheck) {


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


    //*LongLT* triển khai lấy list customer


    /*LongLT hiển thị list khách hàng*/


    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>> getAllCustomer(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Customer> customers = iCustomerService.findAllCustomer(pageable);
        System.out.println(123);
        if (customers.isEmpty()) {
            System.out.println(456);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        System.out.println(789);
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

    /*LongLT xoa customer */

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
    /*LongLT search customer */
    @GetMapping("/search")
    public ResponseEntity<Page<Customer>> searchCustomer(@RequestParam(defaultValue = "", required = false) String
                                                                 keyword, @RequestParam(defaultValue = "", required = false) String option,
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
                                                        BindingResult bindingResult) {

        /* ThangDBX cập nhật thông tin bản thân khách hàng  */

        new CustomerPersonalInfoDto().validate(customerDto,bindingResult);

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

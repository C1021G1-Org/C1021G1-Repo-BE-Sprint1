package com.codegym.service;


import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.codegym.dto.CustomerDto;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface ICustomerService {

    Page<Customer> findAllCustomer(Pageable pageable);

    void remove(Long id);

//        List<Customer> searchCustomer(String keyword);

    Customer findById(Long id);

    void save(CustomerDto customerDto);


    void update(CustomerDto customerDto);

    Page<Customer> searchCustomerByEmail(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByName(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByAddress(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByCountry(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByCustomerType(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByPhone(String keyword, Pageable pageable);

    Page<Customer> searchCustomerByIdCrad(String keyword, Pageable pageable);


//        /*ThangDBX lấy dữ liệu của khách hàng  */
//        Customer findCustomerById(Long id);

//    List<Customer> getAllCustomerNotPagination();

    /*ThangDBX lấy dữ liệu của khách hàng  */
    Customer findCustomerById(Long id);

    /*ThangDBX lấy dữ liệu của khách hàng  */
    Customer findByIdPersonal(Long id);


    /*ThangDBX: cập nhật dữ liệu của khách hàng  */
    void updatePersonalInfo(Customer customer);

    //TinhHD lấy idcard để validator
    Integer finByIdCard(String idCard);

    //TinhHD lấy idcard để validator
    Integer finByEmail(String email);

    //TinhHD lấy idcard để validator
    Integer finByPhone(String phone);
    //TinhHD  validator
    Long findByCheck(String emailCustomer,String phoneCustomer,String idCard);
    //TinhHD  validator
    Integer findByEmailNot(Long id, String emailCustomer);

    Integer findByPhoneNot(Long id, String phoneCustomer);


    // ThangDBX kiểm tra CMND/ ho chieu đã tồn tại hoặc có thay đổi không khi thực hiện cập nhật
    Integer checkIdCardIsExistUpdate(String idCard, Long id);

    // ThangDBX kiểm tra phone đã tồn tại hoặc có thay đổi không khi thực hiện cập nhật
    Integer checkPhoneIsExistUpdate(String phone, Long id);

    // ThangDBX kiểm tra email đã tồn tại hoặc có thay đổi không khi thực hiện cập nhật
    Integer checkEmailIsExistUpdate(String email, Long id);

    //ThangDBX cập nhật điểm thưởng của khách hàng
    void updatePointCustomer(Integer point, Long id);

    // ThangDBX lấy lịch sử tichket đã thanh toán để tính tổng điểm khách hàng đạt được,
    Integer getPoint(Long idCustomer);

    /* ThangDBX update customerType  */
    void updateCustomerType(Long customerId);



    Integer findByIdCardNot(Long id, String idCardCustomer);

}

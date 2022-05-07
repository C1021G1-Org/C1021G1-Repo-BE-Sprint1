package com.codegym.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;

public class CustomerDto {
    private static final String REGEX_NAME = "^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+(\\s[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+)*$";
    private static final String REGEX_PHONE = "^([0-9])*$";
    private Long id;
    @Size(max = 40, message = "Tối đa 40 kí tự!")
    @Pattern(regexp = REGEX_NAME, message = "Vui lòng nhập đúng tên của bạn!")
    @NotEmpty(message = "Vui lòng nhập tên !")
    private String nameCustomer;
    private Boolean genderCustomer;
    @NotBlank(message = "Vui lòng nhập ngày sinh!")
    private String birthdayCustomer;
    @ValidatorCustomer(message = "CMND đã tồn tại")
    @Size(min = 10,max = 10, message = "Tối đa 10 số!")
    @NotBlank(message = "Vui lòng nhập CCCD!")
    private String idCardCustomer;
    @ValidatorCustomer(message = "Số điện thoại đã tồn tại")
    @Size(min = 10,max = 13, message = "Tối đa 13 số!")
    @Pattern(regexp = REGEX_PHONE, message = "Vui lòng nhập đúng số điện thoại!")
    @NotBlank(message = "Vui lòng nhập số điện thoại!")
    private String phoneCustomer;
    @ValidatorCustomer(message = "email đã tồn tại")
    @Size(max = 40, message = "Tối đa 40 kí tự!")
    @NotBlank(message = "Vui lòng nhập email!")
    @Email(message = "Phải đúng định dạng email ví dụ: tinh@gmail.com")
    private String emailCustomer;
    @NotBlank(message = "Vui lòng nhập địa chỉ!")
    private String addressCustomer;
    private Boolean delFlagCustomer;

    //    @JsonProperty("data")
    @NotNull(message = "Vui lòng chọn quốc gia.")
    private Long countries;
    //    @JsonProperty("data")
    @NotNull(message = "Vui lòng chọn loại khách hàng.")
    private Long customerType;

    public CustomerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountries() {
        return countries;
    }

    public void setCountries(Long countries) {
        this.countries = countries;
    }

    public Long getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Long customerType) {
        this.customerType = customerType;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Boolean getGenderCustomer() {
        return genderCustomer;
    }

    public void setGenderCustomer(Boolean genderCustomer) {
        this.genderCustomer = genderCustomer;
    }


    public void setBirthdayCustomer(String birthdayCustomer) {
        this.birthdayCustomer = birthdayCustomer;
    }

    public String getBirthdayCustomer() {
        return birthdayCustomer;
    }

    public String getIdCardCustomer() {
        return idCardCustomer;
    }

    public void setIdCardCustomer(String idCardCustomer) {
        this.idCardCustomer = idCardCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public Boolean getDelFlagCustomer() {
        return delFlagCustomer;
    }

    public void setDelFlagCustomer(Boolean delFlagCustomer) {
        this.delFlagCustomer = delFlagCustomer;
    }
}

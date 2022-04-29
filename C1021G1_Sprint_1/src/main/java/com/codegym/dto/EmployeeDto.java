package com.codegym.dto;

import com.codegym.model.EmployeeType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 6)
    private String name_Employee;
    @NotNull
    @Pattern(regexp = "((NV-|nv-)\\d{3})", message = "Mã nhân viên phải là NV-XXX")
    private String code_Employee;

    private Boolean gender_Employee;
    @NotNull
    private String birthday_Employee;
    private String phone_Employee;
    @NotNull
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email bạn nhập không hợp lệ. Vui lòng nhập đúng định dạng. VD:abc@gmail.com")
    private String email_Employee;
    @NotNull
    @Size(min = 6)
    private String address_Employee;
    private Boolean del_Flag_Employee;
    private Long id_employee_type ;

    public EmployeeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_Employee() {
        return name_Employee;
    }

    public void setName_Employee(String name_Employee) {
        this.name_Employee = name_Employee;
    }

    public String getCode_Employee() {
        return code_Employee;
    }

    public void setCode_Employee(String code_Employee) {
        this.code_Employee = code_Employee;
    }

    public Boolean getGender_Employee() {
        return gender_Employee;
    }

    public void setGender_Employee(Boolean gender_Employee) {
        this.gender_Employee = gender_Employee;
    }

    public String getBirthday_Employee() {
        return birthday_Employee;
    }

    public void setBirthday_Employee(String birthday_Employee) {
        this.birthday_Employee = birthday_Employee;
    }

    public String getPhone_Employee() {
        return phone_Employee;
    }

    public void setPhone_Employee(String phone_Employee) {
        this.phone_Employee = phone_Employee;
    }

    public String getEmail_Employee() {
        return email_Employee;
    }

    public void setEmail_Employee(String email_Employee) {
        this.email_Employee = email_Employee;
    }

    public String getAddress_Employee() {
        return address_Employee;
    }

    public void setAddress_Employee(String address_Employee) {
        this.address_Employee = address_Employee;
    }

    public Boolean getDel_Flag_Employee() {
        return del_Flag_Employee;
    }

    public void setDel_Flag_Employee(Boolean del_Flag_Employee) {
        this.del_Flag_Employee = del_Flag_Employee;
    }

    public Long getEmployee_Type_Id() {
        return id_employee_type;
    }

    public void setId_Employee_Type(Long id_employee_type) {
        this.id_employee_type = id_employee_type;
    }

    public EmployeeDto(Long id, String name_Employee, String code_Employee, Boolean gender_Employee,
                       String birthday_Employee, String phone_Employee, String email_Employee, String address_Employee, Boolean del_Flag_Employee, Long id_employee_type) {
        this.id = id;
        this.name_Employee = name_Employee;
        this.code_Employee = code_Employee;
        this.gender_Employee = gender_Employee;
        this.birthday_Employee = birthday_Employee;
        this.phone_Employee = phone_Employee;
        this.email_Employee = email_Employee;
        this.address_Employee = address_Employee;
        this.del_Flag_Employee = del_Flag_Employee;
        this.id_employee_type = id_employee_type;
    }
}

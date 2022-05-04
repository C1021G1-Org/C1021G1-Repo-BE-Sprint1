package com.codegym.dto;

import javax.validation.constraints.NotNull;

public interface EmployeeFindIdDto {
    Long getId();
    String getAddress_Employee();
    String getCode_Employee();
    Boolean getDel_Flag_Employee();
    String getBirthday_Employee();
    String getEmail_Employee();
    Boolean getGender_Employee();
    String getName_Employee();
    String getPhone_Employee();
    Long getEmployee_Type_Id();
}

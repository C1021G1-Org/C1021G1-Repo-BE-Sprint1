package com.codegym.dto;


import com.codegym.model.Employee;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class TicketFirstDto {

    private Long id;

    @Email(message = "phải đúng định dạng email")
    @NotBlank(message = "email không được để trống")
    private String emailTicket;

    @NotBlank(message = "số điện thoại không được để trống")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "số điện thoại không đúng định dạng")
    private String phoneTicket;


    private Boolean genderTicket;

    @NotNull(message = "phai thay đổi giá trị không được null")
    private Boolean statusTicket;

    @NotBlank(message = "tên không được để trống")
    @Pattern(regexp = "^[A-Z|a-z]$",message = "tên không đưuọc để ký tự số")
    private String buyerTicket;

    @NotBlank(message = "ngày tháng không để trống")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$",message = "ngày tháng không đúng định dạng")
    private String birthdayTicket;


    private EmployeeTicketDto employeeTicketDto;





    public TicketFirstDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailTicket() {
        return emailTicket;
    }

    public void setEmailTicket(String emailTicket) {
        this.emailTicket = emailTicket;
    }

    public String getPhoneTicket() {
        return phoneTicket;
    }

    public void setPhoneTicket(String phoneTicket) {
        this.phoneTicket = phoneTicket;
    }

    public Boolean getGenderTicket() {
        return genderTicket;
    }

    public void setGenderTicket(Boolean genderTicket) {
        this.genderTicket = genderTicket;
    }

    public Boolean getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(Boolean statusTicket) {
        this.statusTicket = statusTicket;
    }


    public String getBuyerTicket() {
        return buyerTicket;
    }

    public void setBuyerTicket(String buyerTicket) {
        this.buyerTicket = buyerTicket;
    }

    public String getBirthdayTicket() {
        return birthdayTicket;
    }

    public void setBirthdayTicket(String birthdayTicket) {
        this.birthdayTicket = birthdayTicket;
    }

    public EmployeeTicketDto getEmployeeTicketDto() {
        return employeeTicketDto;
    }

    public void setEmployeeTicketDto(EmployeeTicketDto employeeTicketDto) {
        this.employeeTicketDto = employeeTicketDto;
    }
}

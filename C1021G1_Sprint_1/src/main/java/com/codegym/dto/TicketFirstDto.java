package com.codegym.dto;


import com.codegym.model.Employee;


import javax.validation.constraints.*;


public class TicketFirstDto {

    private Long id;

    @Email(message = "phải đúng định dạng email")
    @NotBlank(message = "email không được để trống")
    private String emailTicket;

    @NotBlank(message = "số điện thoại không được để trống")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "số điện thoại không đúng định dạng")
    private String phoneTicket;

    @NotNull(message = "số tiền không được để trống")
//    @Pattern(regexp = "^[0-9]$", message = "giá tiền không được để ký tự")
    private Double priceTicket;

    private String genderTicket;

//    @NotNull(message = "phai thay đổi giá trị không được null")
//    private Boolean statusTicket;

    @NotBlank(message = "tên không được để trống")
    @Pattern(regexp = "^[A-Z|a-z]$", message = "tên không đưuọc để ký tự số")
    private String buyerTicket;

    @NotBlank(message = "ngày tháng không để trống")
//    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", message = "ngày tháng không đúng định dạng")
    private String birthdayTicket;


    private String idCard;

    //    private EmployeeTicketDto employee;
    private Long employee;
    private Long customer;


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

//    public Boolean getGenderTicket() {
//        return genderTicket;
//    }
//
//    public void setGenderTicket(Boolean genderTicket) {
//        this.genderTicket = genderTicket;
//    }

    public String getGenderTicket() {
        return genderTicket;
    }

    public void setGenderTicket(String genderTicket) {
        this.genderTicket = genderTicket;
    }


//    public Boolean getStatusTicket() {
//        return statusTicket;
//    }
//
//    public void setStatusTicket(Boolean statusTicket) {
//        this.statusTicket = statusTicket;
//    }


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


//    public EmployeeTicketDto getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(EmployeeTicketDto employee) {
//        this.employee = employee;
//    }

    //
    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }
}

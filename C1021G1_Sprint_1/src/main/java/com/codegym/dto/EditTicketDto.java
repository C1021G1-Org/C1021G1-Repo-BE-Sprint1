package com.codegym.dto;

import javax.validation.constraints.*;

public class EditTicketDto {
    private Long id;

    private String codeTicket;

    @NotNull( message = "Vui lòng nhập email")
    @NotBlank( message = "Vui lòng không để trống email")
    @Email(message = "Vui lòng nhập đúng định dạng email - VD:xxx@gmail.com")
    private String emailTicket;

    private String phoneTicket;

    private Boolean genderTicket;

    private Boolean statusTicket;

    private Double priceTicket;

    private Boolean delFlagTicket;

    private Integer pointTicket;

    @NotNull( message = "Vui lòng nhập tên khách hàng")
    @NotBlank( message = "Vui lòng không để trống tên khách hàng")
    @Pattern(regexp = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]{10,50}$", message = "Tên khách hàng không chứa số và từ 10 - 50 kí tự")
    private String buyerTicket;

    private String dayOfBirth;

    public EditTicketDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTicket() {
        return codeTicket;
    }

    public void setCodeTicket(String codeTicket) {
        this.codeTicket = codeTicket;
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

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public Boolean getDelFlagTicket() {
        return delFlagTicket;
    }

    public void setDelFlagTicket(Boolean delFlagTicket) {
        this.delFlagTicket = delFlagTicket;
    }

    public Integer getPointTicket() {
        return pointTicket;
    }

    public void setPointTicket(Integer pointTicket) {
        this.pointTicket = pointTicket;
    }

    public String getBuyerTicket() {
        return buyerTicket;
    }

    public void setBuyerTicket(String buyerTicket) {
        this.buyerTicket = buyerTicket;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}

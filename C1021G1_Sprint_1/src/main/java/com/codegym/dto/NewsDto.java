package com.codegym.dto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NewsDto  {

    private Long id;

    @NotBlank(message = "Name may not be blank")
    private String nameNews;

    @Pattern(regexp = "^([a-zAa-Z])$", message = "Vui lòng không nhập chữ")
    @NotBlank(message = "Code may not be blank")
    private String codeNews;

    @NotBlank(message = "Date may not be blank")
    private String dateNews;

    @Column(columnDefinition = "LONGTEXT")
    @NotBlank(message = "Image may not be blank")
    private String imageNews;

    @NotBlank(message = "Title may not be blank")
    private String titleNews;

    private Boolean delFlagNews;
    @Column(columnDefinition = "LONGTEXT")
    @NotBlank(message = "description may not be blank")
    private String descriptionNews;

    private Long category;

    public NewsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public String getCodeNews() {
        return codeNews;
    }

    public void setCodeNews(String codeNews) {
        this.codeNews = codeNews;
    }

    public String getDateNews() {
        return dateNews;
    }

    public void setDateNews(String dateNews) {
        this.dateNews = dateNews;
    }

    public String getImageNews() {
        return imageNews;
    }

    public void setImageNews(String imageNews) {
        this.imageNews = imageNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public Boolean getDelFlagNews() {
        return delFlagNews;
    }

    public void setDelFlagNews(Boolean delFlagNews) {
        this.delFlagNews = delFlagNews;
    }

    public String getDescriptionNews() {
        return descriptionNews;
    }

    public void setDescriptionNews(String descriptionNews) {
        this.descriptionNews = descriptionNews;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

}

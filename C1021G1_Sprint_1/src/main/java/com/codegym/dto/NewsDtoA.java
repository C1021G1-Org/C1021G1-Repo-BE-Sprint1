package com.codegym.dto;

import com.codegym.model.Category;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NewsDtoA {
    private Long id;
    @Pattern(regexp = "^[a-zA-Zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]+(\\s[a-zA-Zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]+)*$", message = "vui lòng không nhập ký tự đặc biệt")
    @NotNull(message = "Vui lòng không được để trống")
    private String nameNews;

    @Pattern(regexp = "^TT-[0-9]{4}$",message = "vui lòng nhập đúng định danh TT-XXXX")
    @NotNull(message = "Vui lòng không được để trống")
    private String codeNews;

    @NotNull(message = "Vui lòng không được để trống")
    private String dateNews;

    @NotNull(message = "Vui lòng không được để trống")
    private String imageNews;

    @NotNull(message = "Vui lòng không được để trống")
    private String titleNews;

    private Boolean delFlagNews;

    @Column(columnDefinition = "LONGTEXT")
    @NotNull(message = "Vui lòng không được để trống")
    private String descriptionNews;
    @NotNull(message = "Vui lòng không được để trống")
    private Category category;

    public NewsDtoA() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Category getCategory() {
        return category;
    }
}



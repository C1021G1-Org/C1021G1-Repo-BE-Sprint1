package com.codegym.model;

import javax.persistence.*;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameNews;

    private String codeNews;

    @Column(columnDefinition = "DATE")
    private String dateNews;

    @Column(columnDefinition = "LONGTEXT")
    private String imageNews;

    private String titleNews;

    private Boolean delFlagNews;

    @Column(columnDefinition = "LONGTEXT")
    private String descriptionNews;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    public News() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

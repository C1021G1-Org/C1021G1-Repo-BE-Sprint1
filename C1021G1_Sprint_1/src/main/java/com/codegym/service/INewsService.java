package com.codegym.service;

import com.codegym.dto.NewsDto;
import com.codegym.model.News;

import java.util.List;
import java.util.Optional;

public interface INewsService {
    News findById(Long id);

    void createNews(NewsDto newsDto);

    List<News> findAll();

    void editNews(NewsDto newsDto);


}

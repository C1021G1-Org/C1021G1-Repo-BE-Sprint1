package com.codegym.service;


import com.codegym.dto.NewsDto;
import com.codegym.model.News;

import com.codegym.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface INewsService {



    void createNews(NewsDto newsDto);

    List<News> findAll();

    void editNews(NewsDto newsDto);


    Page<News> findAllNews(Pageable pageable);
    void deleteNewsById(Long id);

    News findById(Long id);

    List<News> getAllNewsNotPagination();


}

package com.codegym.service;

import com.codegym.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface INewsService {
    Page<News> findAllNews(Pageable pageable);
    void deleteNewsById(Long id);
    Optional<News> findNewsById(Long id);
}

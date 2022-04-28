package com.codegym.service.impl;

import com.codegym.model.News;
import com.codegym.repository.INewsRepository;
import com.codegym.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    private INewsRepository iNewsRepository;

    @Override
    public Page<News> findAllNews(Pageable pageable) {
        return iNewsRepository.findAllNews(pageable);
    }

    @Override
    public void deleteNewsById(Long id) {
        iNewsRepository.removeNewsById(id);
    }

    @Override
    public Optional<News> findNewsById(Long id)
    {
        return iNewsRepository.findNewsById(id);
    }

}

package com.codegym.service.impl;



import com.codegym.dto.NewsDto;

import com.codegym.model.News;
import com.codegym.repository.INewsRepository;
import com.codegym.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import javax.print.attribute.Attribute;
import java.util.List;
import java.util.Optional;


@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    private INewsRepository repository;


    @Override
    public News findById(Long id) {
        return repository.findNewsById(id);
    }

    @Override
    public void createNews(NewsDto newsDto) {

        repository.createNews(newsDto.getCodeNews(), newsDto.getDateNews(), newsDto.getDescriptionNews(),
                newsDto.getImageNews(), newsDto.getNameNews(), newsDto.getTitleNews(),newsDto.getCategory(), true);
    }

    @Override
    public List<News> findAll() {
        return repository.findAll();
    }

    @Override
    public void editNews(NewsDto newsDto) {
        repository.editNews(newsDto.getCodeNews(), newsDto.getDateNews(), newsDto.getDescriptionNews(),
                newsDto.getImageNews(), newsDto.getNameNews(), newsDto.getTitleNews(),
                newsDto.getCategory(), true, newsDto.getId());
    }


}






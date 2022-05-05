package com.codegym.service.impl;


import com.codegym.model.Category;
import com.codegym.repository.ICategoryRepository;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository repository;
    @Override
    public List<Category> findAllCategory() {

        return repository.findAll();
    }

}





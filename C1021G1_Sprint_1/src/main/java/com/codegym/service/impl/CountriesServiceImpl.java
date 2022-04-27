package com.codegym.service.impl;

import com.codegym.model.Countries;
import com.codegym.repository.ICountriesRepository;
import com.codegym.service.ICountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesServiceImpl implements ICountriesService {

    @Autowired
    private ICountriesRepository repository;


    @Override
    public List<Countries> fillAll() {
        return repository.findAll();
    }

    @Override
    public void save(Countries countries) {
        repository.save(countries);
    }

}

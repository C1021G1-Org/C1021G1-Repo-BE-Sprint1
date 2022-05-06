package com.codegym.service;

import com.codegym.model.Countries;

import java.util.List;

public interface ICountriesService {

   List<Countries> fillAll();


   void save(Countries countries);
}

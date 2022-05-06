package com.codegym.service;
import com.codegym.model.Category;
import com.codegym.model.News;
import java.util.List;

public interface ICategoryService {

    List<Category> findAllCategory();
}

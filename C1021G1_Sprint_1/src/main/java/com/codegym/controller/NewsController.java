package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.News;
import com.codegym.service.ICategoryService;
import com.codegym.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class NewsController {
    @Autowired
    private INewsService iNewsService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/list-news")
    public ResponseEntity<Page<News>> findAllNews(Pageable pageable) {
        Page<News> newsPage = iNewsService.findAllNews(pageable);
        if (newsPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsPage, HttpStatus.OK);
    }

    @DeleteMapping("/delete-news/{id}")
    public ResponseEntity<News> deleteNews(@PathVariable Long id) {
        Optional<News> newsOptional = iNewsService.findNewsById(id);
        if (!newsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iNewsService.deleteNewsById(id);
        return new ResponseEntity<>(newsOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list-category")
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> categoryList = iCategoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

}

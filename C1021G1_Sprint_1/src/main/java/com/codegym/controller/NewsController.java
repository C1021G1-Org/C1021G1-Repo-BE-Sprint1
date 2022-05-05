package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.News;
import com.codegym.service.ICategoryService;
import com.codegym.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class NewsController {
    @Autowired
    private INewsService iNewsService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/list-news")
    public ResponseEntity<Page<News>> findAllNews(@RequestParam(defaultValue = "0") int page) {
        Page<News> newsList = iNewsService.findAllNews(PageRequest.of(page, 10));
        if (newsList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping("/news-not-pagination")
    public ResponseEntity<List<News>> getAllNewsNotPagination() {
        List<News> vaccines = iNewsService.getAllNewsNotPagination();
        if (vaccines.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }


    @DeleteMapping("/delete-news/{id}")
    public ResponseEntity<News> deleteNews(@PathVariable Long id) {
        News news = iNewsService.findById(id);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iNewsService.deleteNewsById(id);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/list-category")
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> categoryList = iCategoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @GetMapping("/list-news/{id}")
    public ResponseEntity<News> getId(@PathVariable Long id) {
        News news = iNewsService.findById(id);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(news, HttpStatus.OK);
        }
    }

}

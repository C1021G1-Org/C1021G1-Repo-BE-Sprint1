package com.codegym.controller;


import com.codegym.dto.NewsDto;
import com.codegym.dto.NewsDtoA;

import com.codegym.model.Category;
import com.codegym.model.News;
import com.codegym.service.ICategoryService;
import com.codegym.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/api")
@CrossOrigin(origins = "*")
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
 // lấy đường link nào ae dạt trọng tự giải quyết
//    @GetMapping("/list-news/{id}")
//    public ResponseEntity<News> getId(@PathVariable Long id) {
//        News news = iNewsService.findById(id);
//        if (news == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(news, HttpStatus.OK);
//        }
//    }
    @GetMapping("/{id}")
    public ResponseEntity<News> getId(@PathVariable Long id) {
        News news = iNewsService.findById(id);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(news, HttpStatus.OK);
        }
    }
    @GetMapping("/news")
    public ResponseEntity<List<News>> getNewsList() {
        List<News> newsList = iNewsService.findAll();
        if (newsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategoryList() {
        List<Category> categoryList = iCategoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> saveNews(@Valid @RequestBody NewsDto news, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        iNewsService.createNews(news);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }




    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateFlight(@Valid @RequestBody NewsDtoA newsDtoA, BindingResult bindingResult, @PathVariable
            Long id) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(id);
        newsDto.setId(newsDtoA.getId());
        newsDto.setNameNews(newsDtoA.getNameNews());
        newsDto.setCodeNews(newsDtoA.getCodeNews());
        newsDto.setDateNews(newsDtoA.getDateNews());
        newsDto.setImageNews(newsDtoA.getImageNews());
        newsDto.setTitleNews(newsDtoA.getTitleNews());
        newsDto.setDescriptionNews(newsDtoA.getDescriptionNews());
        newsDto.setCategory(newsDtoA.getCategory().getId());
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>
                    (bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);}
        iNewsService.editNews(newsDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
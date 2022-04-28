package com.codegym.controller;

import com.codegym.dto.NewsDto;
import com.codegym.model.News;
import com.codegym.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private INewsService iNewsService;

    @GetMapping("/api")
    public ResponseEntity<List<News>> getNewsList() {
        List<News> newsList = iNewsService.findAll();
        if (newsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveNews(@Valid @RequestBody NewsDto news, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        iNewsService.createNews(news);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
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

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateFlight(@Valid @RequestBody NewsDto newsDto, BindingResult bindingResult, @PathVariable
            Long id) {
        newsDto.setId(id);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        iNewsService.editNews(newsDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}

package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.News;
import com.codegym.service.ICategoryService;
import com.codegym.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;






@RestController
@CrossOrigin("*")
public class NewsController {


}

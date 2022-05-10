package com.codegym.service.impl;

import com.codegym.model.EmployeeType;
import com.codegym.repository.IEmployeeTypeRepository;
import com.codegym.service.IEmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTypeServiceImpl implements IEmployeeTypeService {
    @Autowired
    IEmployeeTypeRepository iEmployeeTypeRepository;

    @Override
    public List<EmployeeType> findAllEmployeeType() {
        return iEmployeeTypeRepository.findAllEmployeeType();
    }
}

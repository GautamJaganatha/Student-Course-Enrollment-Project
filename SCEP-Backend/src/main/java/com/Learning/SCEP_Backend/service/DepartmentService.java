package com.Learning.SCEP_Backend.service;

import com.Learning.SCEP_Backend.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {


    DepartmentDto addDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartment();





    boolean deleteDepartmentById(Long id);
}

package com.Learning.SCEP_Backend.service;

import com.Learning.SCEP_Backend.dto.DepartmentDto;
import com.Learning.SCEP_Backend.entity.Department;
import com.Learning.SCEP_Backend.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        return departmentRepository.save(department).getDepartmentDto();
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<DepartmentDto> departmentList = departmentRepository.findAll()
                .stream()
                .map(Department::getDepartmentDto)
                .collect(Collectors.toList());
        return departmentList;
    }

    @Override
    public boolean deleteDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }


}

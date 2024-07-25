package com.Learning.SCEP_Backend.entity;

import com.Learning.SCEP_Backend.dto.DepartmentDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public DepartmentDto getDepartmentDto(){
        DepartmentDto dto = new DepartmentDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);

        return dto;
    }

}

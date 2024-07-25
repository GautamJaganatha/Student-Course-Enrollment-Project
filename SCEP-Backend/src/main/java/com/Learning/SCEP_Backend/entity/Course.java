package com.Learning.SCEP_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int credits;

    private String schedule;

    private String instructor;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    private Department department;
}

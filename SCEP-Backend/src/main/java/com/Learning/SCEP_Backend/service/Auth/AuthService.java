package com.Learning.SCEP_Backend.service.Auth;

import com.Learning.SCEP_Backend.dto.Auth.SignupRequest;
import com.Learning.SCEP_Backend.dto.StudentDto;

import java.util.List;

public interface AuthService {

    StudentDto SignupRequest(SignupRequest signupRequest);

    boolean hasUserEmail(String email);

    StudentDto updateStudent(StudentDto studentDto);

    boolean deleteStudent(Long id);

    List<StudentDto> getAllStudents();
}

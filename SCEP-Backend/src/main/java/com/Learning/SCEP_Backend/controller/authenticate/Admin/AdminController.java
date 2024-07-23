package com.Learning.SCEP_Backend.controller.authenticate.Admin;

import com.Learning.SCEP_Backend.dto.StudentDto;
import com.Learning.SCEP_Backend.service.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;

    @PutMapping("updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDto studentDto){
        StudentDto studentDto1 = authService.updateStudent(studentDto);
        return ResponseEntity.ok(studentDto1);

    }

    @DeleteMapping("deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        boolean check = authService.deleteStudent(id);
        if (check){
            return ResponseEntity.ok().body("Student with id "+ id  + " successfully deleted");
        }

        return ResponseEntity.ok().body("Student not found with id: "+id);
    }

    @GetMapping("GetAllStudents")
    public ResponseEntity<List<?>> getAllStudnets(){
        return ResponseEntity.ok(authService.getAllStudents());
    }

}

package com.Learning.SCEP_Backend.service.Auth;

import com.Learning.SCEP_Backend.dto.SignupRequest;
import com.Learning.SCEP_Backend.dto.StudentDto;
import com.Learning.SCEP_Backend.entity.Student;
import com.Learning.SCEP_Backend.enums.Roles;
import com.Learning.SCEP_Backend.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;



    @Override
    public StudentDto SignupRequest(SignupRequest signupRequest) {
        Student student = new Student();
        student.setFirstname(signupRequest.getFirstname());
        student.setLastname(signupRequest.getLastname());
        student.setEmail(signupRequest.getEmail());
        student.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        student.setRole(Roles.STUDENT);

        Student createdStudent = studentRepository.save(student);

        return createdStudent.getStudentDto();
    }

    @Override
    public boolean hasUserEmail(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {

            Student student1 = new Student();
            student1.setFirstname(studentDto.getFirstname());
            student1.setLastname(studentDto.getLastname());
            student1.setEmail(studentDto.getEmail());
            student1.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
            student1.setRole(Roles.STUDENT);

            return studentRepository.save(student1).getStudentDto();

    }

    @Override
    public boolean deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> collect = studentRepository.findAll()
                .stream()
                .map(Student::getStudentDto)
                .collect(Collectors.toList());
        return collect;
    }


    @PostConstruct
    public void createAdmin(){
        Optional<Student> optionalStudent = studentRepository.findByRole(Roles.ADMIN);
        if(optionalStudent.isEmpty()){
            Student adminStudent = new Student();
            adminStudent.setFirstname("Admin");
            adminStudent.setLastname("Admin");
            adminStudent.setEmail("Admin@admin.com");
            adminStudent.setPassword(new BCryptPasswordEncoder().encode("admin"));
            adminStudent.setRole(Roles.ADMIN);
            studentRepository.save(adminStudent);

            System.out.println("Admin account created successfully");
        }
        else {
            System.out.println("Admin account already exists");
        }
    }


}

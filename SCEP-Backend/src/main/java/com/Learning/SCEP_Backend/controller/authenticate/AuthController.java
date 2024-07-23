package com.Learning.SCEP_Backend.controller.authenticate;

import com.Learning.SCEP_Backend.dto.StudentDto;
import com.Learning.SCEP_Backend.exception.ApiRequestException;
import com.Learning.SCEP_Backend.service.studentService.StudentService;
import com.Learning.SCEP_Backend.dto.AuthenticationRequest;
import com.Learning.SCEP_Backend.dto.AuthenticationResponse;
import com.Learning.SCEP_Backend.dto.SignupRequest;
import com.Learning.SCEP_Backend.entity.Student;
import com.Learning.SCEP_Backend.repository.StudentRepository;
import com.Learning.SCEP_Backend.service.Auth.AuthServiceImpl;
import com.Learning.SCEP_Backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final StudentRepository studentRepository;

    private final JwtUtils jwtUtil;

    private final AuthServiceImpl authService;

    private final StudentService studentService;


    @PostMapping("/Signup")
    public ResponseEntity<?> SignupUser(@RequestBody SignupRequest signupRequest){
        if(authService.hasUserEmail(signupRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exists with this email: {}"+ signupRequest.getEmail());
        }
        StudentDto studentDto = authService.SignupRequest(signupRequest);
        if(studentDto==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
    }


    @PostMapping("/Login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        Optional<Student> optionalUser = studentRepository.findByEmail(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(optionalUser.get().getEmail());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setStudentId((optionalUser.get().getId()));
            authenticationResponse.setRoles(optionalUser.get().getRole());
        }

        return authenticationResponse;
    }

//    @GetMapping
//    public ResponseEntity<List<StudentDto>> getAllStudents(){
//        List<Student> studentDtoList =   studentRepository.findAll();
//    }

    @GetMapping("SeeingForException")
    public List<Student> checkingException(){
        throw new ApiRequestException("Not Found");
    }

}

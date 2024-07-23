package com.Learning.SCEP_Backend.controller.authenticate.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class homeController {

    @GetMapping("/check")
    public String GettingResult(){
        return "Success";
    }
}

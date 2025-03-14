package com.thecodereveal.medikart.controller;

import com.thecodereveal.medikart.model.Speciality;
import com.thecodereveal.medikart.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specialities")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @GetMapping
    public List<Speciality> getAll(){
        return specialityService.getAll();
    }
}

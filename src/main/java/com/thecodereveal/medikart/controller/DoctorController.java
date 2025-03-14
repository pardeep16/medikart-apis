package com.thecodereveal.medikart.controller;

import com.thecodereveal.medikart.dto.DoctorDto;
import com.thecodereveal.medikart.model.Doctor;
import com.thecodereveal.medikart.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<DoctorDto> getDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor){
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public DoctorDto getDoctors(@PathVariable String id){
        return doctorService.getDoctorById(id);
    }

}

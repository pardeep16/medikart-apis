package com.thecodereveal.medikart.service;

import com.thecodereveal.medikart.model.Speciality;
import com.thecodereveal.medikart.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public List<Speciality> getAll(){
        return specialityRepository.findAll();
    }
}

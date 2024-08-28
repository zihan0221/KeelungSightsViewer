package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sight;
import com.example.demo.param.SightRequestParameter;
import com.example.demo.service.SightService;


@RestController
public class SightController {
    
    @Autowired
    private final SightService sightService;
    

    @Autowired
    public SightController(SightService sightService){
        this.sightService=sightService;
    }


    @GetMapping("/SightAPI")
    public ResponseEntity<List<Sight>> getSights(
            @ModelAttribute SightRequestParameter param
    ) throws Exception{
        String keyword = param.getZone();
        keyword=keyword+"區";
        List<Sight>values=this.sightService.getSightsByZone(keyword);
        if (values != null) {
            return ResponseEntity.ok(values);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
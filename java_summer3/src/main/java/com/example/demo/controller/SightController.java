package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sight;
import com.example.demo.param.SightRequestParameter;
import com.example.demo.service.SightService;


@RestController
public class SightController {
    
    private final SightService sightService=new SightService();

    @GetMapping("/SightAPI")
    public ResponseEntity<List<Sight>> getSights(
            @ModelAttribute SightRequestParameter param
    ) {
        String keyword = param.getZone();
        keyword=keyword+"區";
        ArrayList<Sight>values=sightService.getSights(keyword);
        if (values != null) {
            return ResponseEntity.ok(values);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
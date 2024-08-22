package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sight;

@Repository
public interface  SightRepository extends MongoRepository<Sight, String>{
    List<Sight> findByZone(String zone);
}

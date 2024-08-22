package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sight;
import com.example.demo.model.KeelungSightCrawler;
import com.example.demo.repository.SightRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SightService {
    private  final SightRepository sightRepository;
    private final KeelungSightCrawler crawler=new KeelungSightCrawler();
    private  final String [] allZoneString={"中山區","信義區","仁愛區","中正區","安樂區","七堵區","暖暖區"};
    
    @Autowired
    public SightService(SightRepository sightRepository){
        this.sightRepository=sightRepository;
    }

    @PostConstruct
    public void init(){
        sightRepository.deleteAll();
        for(int i=0;i<7;i++){
            for(Sight sight:crawler.getSightByZone(allZoneString[i])){
                sightRepository.insert(sight);
            }
        }

        //List<Sight> owo=sightRepository.findByZone("中山區");
        /*for (Sight sight: owo) {
            System.err.println(sight.getSightName());
        }*/
    }


    public List<Sight>getSightsByZone(String zone){
        List<Sight> owo=sightRepository.findByZone(zone);
        return owo;
    }
}

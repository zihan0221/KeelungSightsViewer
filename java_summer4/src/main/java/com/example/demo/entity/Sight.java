package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="sight")
public class Sight implements Serializable{

    @Id
    private String id;
    private String sightName;
    private String zone;
    private String category;
    private String photoUrl;
    private String description;
    private String address;

    public Sight (String sightName,
                String zone,
                String category,
                String photoUrl,
                String description,
                String address) {
        this.sightName=sightName;
        this.zone=zone;
        this.category=category;
        this.photoUrl=photoUrl;
        this.description=description;
        this.address=address;
    }

    public void setSightName(String sightName){
        this.sightName=sightName;
    }
    public void setZone(String zone){
        this.zone=zone;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl=photoUrl;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setAddress(String address){
        this.address=address;
    }
    
    public String getSightName(){
        return this.sightName;
    }
    public String getZone(){
        return this.zone;
    }
    public String getCategory(){
        return this.category;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public String getDescription(){
        return this.description;
    }
    public String getAddress(){
        return this.address;
    }
    public void debugSomething(){
        if(this.photoUrl.length()<=10){
            this.photoUrl="https://imgs.699pic.com/images/600/958/461.jpg!list1x.v2";
        }
        if(this.description.length()<=10){
            this.description="當前沒有內文";
        }
    }

    @Override
    public String toString() {
        return "sightName: " +this.getSightName()+"\n"+
        "zone: " +this.getZone()+"\n"+
        "category: " +this.getCategory()+"\n"+
        "photoUrl: " +this.getPhotoUrl()+"\n"+
        "description: " +this.getDescription()+"\n"+
        "address: " +this.getAddress()+"\n\n";
    }
}

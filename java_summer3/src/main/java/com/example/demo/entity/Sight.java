package com.example.demo.entity;

public class Sight {
    private String SightName;
    private String Zone;
    private String Category;
    private String PhotoURL;
    private String Description;
    private String Address;

    public Sight (String SightNameString,
                String ZoneString,
                String CategoryString,
                String PhotoURLString,
                String DescriptionString,
                String AddressString) {
        this.SightName=SightNameString;
        this.Zone=ZoneString;
        this.Category=CategoryString;
        this.PhotoURL=PhotoURLString;
        this.Description=DescriptionString;
        this.Address=AddressString;
    }

    public void setSightName(String Name){
        this.SightName=Name;
    }
    public void setZone(String zone){
        this.Zone=zone;
    }
    public void setCategory(String category){
        this.Category=category;
    }
    public void setPhotoURL(String photoURL){
        this.PhotoURL=photoURL;
    }
    public void setDescription(String description){
        this.Description=description;
    }
    public void setAddress(String address){
        this.Address=address;
    }
    
    public String getSightName(){
        return this.SightName;
    }
    public String getZone(){
        return this.Zone;
    }
    public String getCategory(){
        return this.Category;
    }
    public String getPhotoURL(){
        return this.PhotoURL;
    }
    public String getDescription(){
        return this.Description;
    }
    public String getAddress(){
        return this.Address;
    }
    public void debugSomething(){
        if(this.PhotoURL.length()<=10){
            this.PhotoURL="https://imgs.699pic.com/images/600/958/461.jpg!list1x.v2";
        }
        if(this.Description.length()<=10){
            this.Description="當前沒有內文";
        }
    }

    @Override
    public String toString() {
        return "SightName: " +this.getSightName()+"\n"+
        "Zone: " +this.getZone()+"\n"+
        "Category: " +this.getCategory()+"\n"+
        "PhotoURL: " +this.getPhotoURL()+"\n"+
        "Description: " +this.getDescription()+"\n"+
        "Address: " +this.getAddress()+"\n\n";
    }
}

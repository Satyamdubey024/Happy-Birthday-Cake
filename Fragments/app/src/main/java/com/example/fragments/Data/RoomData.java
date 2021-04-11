package com.example.fragments.Data;

public class RoomData {

    public String image;
    public String id;


    public RoomData(String images, String id){
        this.image=images;
        this.id=id;

    }
    public String getImages(){
        return image;

    }
    public void setImages(String images){
        this.image=images;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

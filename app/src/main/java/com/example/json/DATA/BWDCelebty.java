package com.example.json.DATA;

public class BWDCelebty {

    private String id;
    private String cat_name;
    private String image;


    public BWDCelebty(String id, String cat_name, String image) {
        this.id = id;
        this.cat_name = cat_name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

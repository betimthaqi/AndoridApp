package com.example.lejlekustore.models;

public class PopularModel {
    String pname;
    String description;
    String price;
    String image;

    public PopularModel() {

    }

    public PopularModel(String pname, String description, String price, String image) {
        this.pname = pname;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}



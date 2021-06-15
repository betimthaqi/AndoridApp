package com.example.lejlekustore.models;

public class NavCategoryDetailedModel {
    String pname;
    String category;
    String image;
    String price;

    public NavCategoryDetailedModel() {
    }

    public NavCategoryDetailedModel(String pname, String category, String image, String price) {
        this.pname = pname;
        this.category = category;
        this.image = image;
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

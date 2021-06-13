package com.example.lejlekustore.models;

public class NavCategoryModel {

    String name, description, discount, image;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String name, String description, String discount, String image) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

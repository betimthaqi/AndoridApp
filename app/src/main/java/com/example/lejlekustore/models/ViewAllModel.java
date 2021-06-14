package com.example.lejlekustore.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {

    String pname, description, category, image, price;

    public ViewAllModel() {
    }

    public ViewAllModel(String pname, String description, String category, String image, String price) {
        this.pname = pname;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

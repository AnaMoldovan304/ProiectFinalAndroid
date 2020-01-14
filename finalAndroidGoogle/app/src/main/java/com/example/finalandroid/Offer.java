package com.example.finalandroid;

import java.io.Serializable;

public class Offer implements Serializable {

    private String title;
    private boolean fav;
    private Integer imageId;
    private Integer price;
    private String description;

    //model
    public Offer(String title, Integer imageId, Integer price, String description, boolean fav) {
        this.title = title;
        this.imageId = imageId;
        this.price = price;
        this.description = description;
        this.fav = fav;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    } {
}}

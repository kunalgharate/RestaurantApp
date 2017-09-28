package com.amlidaz.restaurantapp.model;

/**
 * Created by admin on 28/09/17.
 */

public class ImageUpload {

    public String name;
    public String desc;
    public String price;
    public String url;

    public ImageUpload(String name, String desc, String price, String url) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.url = url;
    }

    public ImageUpload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
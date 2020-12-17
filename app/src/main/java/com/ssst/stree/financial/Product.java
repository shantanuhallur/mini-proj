package com.ssst.stree.financial;

import android.net.Uri;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String price;
    private String category;
    private String info;
    private String imageUri;
    private String email;

    public Product() {}

    public Product(String name, String price, String category, String info, String email, Uri uri) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.info = info;
        this.email = email;
        this.imageUri = uri.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}

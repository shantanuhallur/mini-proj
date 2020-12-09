package com.ssst.stree.classes;

public class Product {
    private String id;
    private String name;
    private String price;
    private String category;
    private String info;
    private String email;

    public Product() {}

    public Product(String id, String name, String price, String category, String info, String email) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.info = info;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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

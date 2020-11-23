package com.ssst.stree.classes;

public class Product {
    private final String name;
    private final String price;
    private final String category;
    private final String info;

    public Product(String name, String price, String category, String info) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.info = info;
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
}

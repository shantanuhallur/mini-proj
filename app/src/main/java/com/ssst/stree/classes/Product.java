package com.ssst.stree.classes;

public class Product {
    private final String id;
    private final String name;
    private final String price;
    private final String category;
    private final String info;

    public Product(String id,String name, String price, String category, String info) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.info = info;
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

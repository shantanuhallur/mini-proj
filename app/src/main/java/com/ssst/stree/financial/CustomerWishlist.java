package com.ssst.stree.financial;

public class CustomerWishlist {
    private String id_email;

    public CustomerWishlist(){}

    public CustomerWishlist(String id_email) {
        this.id_email = id_email;
    }

    public String getId_email() {
        return id_email;
    }

    public void setId_email(String id_email) {
        this.id_email = id_email;
    }
}

package com.ssst.stree.financial;

public class CustomerOrders {
    private String id_email;
    private String id;
    private String email;

    public CustomerOrders(){}

    public CustomerOrders(String id_email, String id, String email) {
        this.id_email = id_email;
        this.id = id;
        this.email = email;
    }

    public String getId_email() {
        return id_email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_email(String id_email) {
        this.id_email = id_email;
    }
}

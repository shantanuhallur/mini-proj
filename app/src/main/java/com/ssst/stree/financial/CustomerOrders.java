package com.ssst.stree.financial;

public class CustomerOrders {
    private String id_email;
    public CustomerOrders(){}
    
    public CustomerOrders(String id_email) {
        this.id_email = id_email;
    }

    public String getId_email() {
        return id_email;
    }

    public void setId_email(String id_email) {
        this.id_email = id_email;
    }
}

package com.infoiv.model;

public class Order {

    private int oderId;
    private String odereeName;
    private String orderDescription;

    public Order() {
    }

    public Order(int oderId, String odereeName, String orderDescription) {
        this.oderId = oderId;
        this.odereeName = odereeName;
        this.orderDescription = orderDescription;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public String getOdereeName() {
        return odereeName;
    }

    public void setOdereeName(String odereeName) {
        this.odereeName = odereeName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
}

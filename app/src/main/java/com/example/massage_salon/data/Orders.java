package com.example.massage_salon.data;

public class Orders {
    private int id;
    private int userId;
    private int serviceId;
    private String dateRecord;

    public Orders(int userId, int serviceId, String dateRecord) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.dateRecord = dateRecord;
    }

    public Orders(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(String dateRecord) {
        this.dateRecord = dateRecord;
    }
}
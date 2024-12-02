package com.poly.quanlitramsac.Model;

public class Station {
    private int id;
    private String address;
    private int quantity;
    private String price;
    private String status;
    private String type;
    private double latitude;
    private double longitude;
    private String distance;

    // Constructor đầy đủ (khi đã có id)
    // Constructor đầy đủ
    public Station(String address, int quantity, String price, String status, String type, double latitude, double longitude) {
        this.id = id;
        this.address = address;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    // Constructor không có distance
    public Station(int id, String address, int quantity, String price, String status, String type, double latitude, double longitude) {
        this.id = id;
        this.address = address;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = "0"; // Giá trị mặc định
    }


    // Getters và Setters
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

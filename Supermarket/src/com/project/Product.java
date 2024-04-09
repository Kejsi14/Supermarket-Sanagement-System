package com.project;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable
{
    String name;
    String category;
    int supplierID;
    double price;
    String barcode;
    int quantity;
    int ID;
    Date expiryDate;
    LocalDate purchasedDate;

    Location location;
    int rack;
    int floor;
    String warehouse;

    static int idGenerator = 0;

    public Product()
    {
        ID = ++idGenerator;
        location = new Location();
        location.setRack(0);
        location.setFloor(0);
        location.setWareHouse("");
        expiryDate = new Date();
        purchasedDate = LocalDate.now();
    }

    static class Location implements Serializable
    {
        int rack;
        int floor;
        String warehouse;

        public int getRack() { return rack; }
        public void setRack(int rack) { this.rack = rack; }

        public int getFloor() { return floor; }
        public void setFloor(int floor) { this.floor = floor; }

        public String getWareHouse() { return warehouse; }
        public void setWareHouse(String wareHouse) { this.warehouse = wareHouse; }
    }

    public int getID() { return ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getSupplierID() { return supplierID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getRack() {
        rack = location.getRack();
        return rack;
    }
    public int getFloor() {
        floor = location.getFloor();
        return floor;
    }
    public String getWarehouse() {
        warehouse = location.getWareHouse();
        return warehouse;
    }

    public void setLocation(int rack, int floor, String wareHouse)
    {
        location.setRack(rack);
        location.setFloor(floor);
        location.setWareHouse(wareHouse);
    }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
}

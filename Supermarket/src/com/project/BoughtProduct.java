package com.project;

import java.io.Serializable;
import java.time.LocalDate;

public class BoughtProduct implements Serializable
{
    String name;
    String category;
    int supplierID;
    LocalDate purchasedDate;
    double price;
    String barcode;
    int quantity;
    int ID;
    Date expiryDate;
    double totalPrice;

    int boughtOverAPeriod;
    double priceOverAPeriod;

    public BoughtProduct()
    {
        purchasedDate = LocalDate.now();
        boughtOverAPeriod = 0;
        priceOverAPeriod = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getSupplierID() { return supplierID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }

    public LocalDate getPurchasedDate() { return purchasedDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public double getTotalPrice()
    {
        totalPrice = price * quantity;
        return totalPrice;
    }

    public int getBoughtOverAPeriod() { return boughtOverAPeriod; }
    public double getPriceOverAPeriod() { return priceOverAPeriod; }

    public void setBoughtOverAPeriod(int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo)
    {
        LocalDate dateFrom = LocalDate.of(yearFrom, monthFrom, dayFrom);
        LocalDate dateTo = LocalDate.of(yearTo, monthTo, dayTo);

        if(purchasedDate.isAfter(dateFrom) && purchasedDate.isBefore(dateTo))
            boughtOverAPeriod = quantity;
        else if(purchasedDate.isEqual(dateFrom) || purchasedDate.equals(dateTo))
            boughtOverAPeriod = quantity;
        else
            boughtOverAPeriod = 0;

        priceOverAPeriod = price * boughtOverAPeriod;
    }

    public void resetStatsOverAPeriod()
    {
        boughtOverAPeriod = 0;
        priceOverAPeriod = 0;
    }
}

package com.project;

import java.io.Serializable;
import java.util.ArrayList;

public class Supplier implements Serializable
{
    String name;
    int ID;
    static int idGenerator = 0;
    ArrayList<String> productsOffered;

    public Supplier(String name)
    {
        this.name = name;
        ID = ++idGenerator;
        productsOffered = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getID() { return ID; }
    public ArrayList<String> getProductsOffered() { return productsOffered; }
}

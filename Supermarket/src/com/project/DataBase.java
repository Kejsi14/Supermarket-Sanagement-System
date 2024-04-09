package com.project;

import java.io.*;
import java.util.ArrayList;

public class DataBase
{
    static String cashiersPath = "resources/Employees/Cashiers.ser";
    static String economistsPath = "resources/Employees/Economists.ser";
    static String categoriesPath = "resources/Statistics/Categories.ser";
    static String productsPath = "resources/Statistics/Products.ser";
    static String boughtProdsPath = "resources/Statistics/BoughtProducts.ser";
    static String suppliersPath = "resources/Suppliers/Suppliers.ser";

    static ArrayList<Cashier> cashiers;
    static ArrayList<Economist> economists;
    static ArrayList<String> categories;
    static ArrayList<Product> products;
    static ArrayList<BoughtProduct> boughtProducts;
    static ArrayList<Supplier> suppliers;

    public static ArrayList<Cashier> getCashiers() { return cashiers; }
    public static ArrayList<Economist> getEconomists() { return economists; }
    public static ArrayList<String> getCategories() { return categories; }
    public static ArrayList<Product> getProducts() { return products; }
    public static ArrayList<BoughtProduct> getBoughtProducts() { return boughtProducts; }
    public static ArrayList<Supplier> getSuppliers() { return suppliers; }

    public static void putDataInLists()
    {
        cashiers = new ArrayList<>();
        economists = new ArrayList<>();
        products = new ArrayList<>();
        boughtProducts = new ArrayList<>();
        categories = new ArrayList<>();
        suppliers = new ArrayList<>();

        load();

        /*fillCashiers();
        fillEconomists();
        fillCategories();
        fillProducts();
        fillBoughtProducts();
        fillSuppliers();*/
    }

    public static void load()
    {
        try
        {
            // Reading the object from a Cashiers File
            FileInputStream cashiersListFile = new FileInputStream(cashiersPath);
            ObjectInputStream inCashiers = new ObjectInputStream(cashiersListFile);

            FileInputStream economistsListFile = new FileInputStream(economistsPath);
            ObjectInputStream inEconomists = new ObjectInputStream(economistsListFile);

            FileInputStream categoriesListFile = new FileInputStream(categoriesPath);
            ObjectInputStream inCategories = new ObjectInputStream(categoriesListFile);

            FileInputStream productsListFile = new FileInputStream(productsPath);
            ObjectInputStream inProducts = new ObjectInputStream(productsListFile);

            FileInputStream boughtProductsListFile = new FileInputStream(boughtProdsPath);
            ObjectInputStream inBoughtProducts = new ObjectInputStream(boughtProductsListFile);

            FileInputStream suppliersListFile = new FileInputStream(suppliersPath);
            ObjectInputStream inSuppliers = new ObjectInputStream(suppliersListFile);

            // We are sure that each serialized list will only ever contain the same correct object Types so we can safely ignore these warning.
            cashiers = (ArrayList<Cashier>)inCashiers.readObject();
            economists = (ArrayList<Economist>)inEconomists.readObject();
            categories = (ArrayList)inCategories.readObject();
            products = (ArrayList<Product>)inProducts.readObject();
            boughtProducts = (ArrayList<BoughtProduct>)inBoughtProducts.readObject();
            suppliers = (ArrayList<Supplier>)inSuppliers.readObject();

            inCashiers.close();
            cashiersListFile.close();

            inEconomists.close();
            economistsListFile.close();

            inCategories.close();
            categoriesListFile.close();

            inProducts.close();
            productsListFile.close();

            inBoughtProducts.close();
            boughtProductsListFile.close();

            inSuppliers.close();
            suppliersListFile.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught.\n\nWasn't able to load from database.\n\nMISSING FILES!\n\n");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    public static void save()
    {
        try
        {
            FileOutputStream fosCashiers = new FileOutputStream(cashiersPath);
            ObjectOutputStream oosCashiers = new ObjectOutputStream(fosCashiers);

            FileOutputStream fosEconomists = new FileOutputStream(economistsPath);
            ObjectOutputStream oosEconomists = new ObjectOutputStream(fosEconomists);

            FileOutputStream fosProducts = new FileOutputStream(productsPath);
            ObjectOutputStream oosProducts = new ObjectOutputStream(fosProducts);

            FileOutputStream fosProdsBought = new FileOutputStream(boughtProdsPath);
            ObjectOutputStream oosProdsBought = new ObjectOutputStream(fosProdsBought);

            FileOutputStream fosCategories = new FileOutputStream(categoriesPath);
            ObjectOutputStream oosCategories = new ObjectOutputStream(fosCategories);

            FileOutputStream fosSuppliers = new FileOutputStream(suppliersPath);
            ObjectOutputStream oosSuppliers = new ObjectOutputStream(fosSuppliers);

            oosCashiers.writeObject(cashiers);
            oosEconomists.writeObject(economists);
            oosProducts.writeObject(products);
            oosProdsBought.writeObject(boughtProducts);
            oosCategories.writeObject(categories);
            oosSuppliers.writeObject(suppliers);

            oosCashiers.close();
            fosCashiers.close();

            oosEconomists.close();
            fosEconomists.close();

            oosProducts.close();
            fosProducts.close();

            oosProdsBought.close();
            fosProdsBought.close();

            oosCategories.close();
            fosCategories.close();

            oosSuppliers.close();
            fosSuppliers.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        System.out.println("Saved");
    }

    static void fillCashiers()
    {
        cashiers.add(new Cashier());
        cashiers.get(0).setUsername("cashier1");
        cashiers.get(0).setPassword("cashier123");
        cashiers.get(0).setRole("Cashier");
        cashiers.get(0).setName("Cashier1");
        cashiers.get(0).setPhoneNumber("068-5623045");
        cashiers.get(0).setBirthday(new Date(5, 7, 1990));
        cashiers.get(0).setEmail("jim1990@gmail.com");
        cashiers.get(0).setSalary(700.0);
        cashiers.get(0).setIdCardNumber("J67439835I");

        cashiers.add(new Cashier());
        cashiers.get(1).setUsername("cashier2");
        cashiers.get(1).setPassword("cashier123");
        cashiers.get(1).setRole("Cashier");
        cashiers.get(1).setName("Cashier2");
        cashiers.get(1).setPhoneNumber("068-5617273");
        cashiers.get(1).setBirthday(new Date(7, 2, 1990));
        cashiers.get(1).setEmail("john1990@gmail.com");
        cashiers.get(1).setSalary(700.0);
        cashiers.get(1).setIdCardNumber("J57483097I");
    }

    static void fillEconomists()
    {
        economists.add(new Economist());
        economists.get(0).setUsername("economist1");
        economists.get(0).setPassword("economist123");
        economists.get(0).setRole("Economist");
        economists.get(0).setName("Economist1");
        economists.get(0).setPhoneNumber("068-5609234");
        economists.get(0).setBirthday(new Date(8, 8, 1990));
        economists.get(0).setEmail("mark990@gmail.com");
        economists.get(0).setSalary(700.0);
        economists.get(0).setIdCardNumber("J50984531I");

        economists.add(new Economist());
        economists.get(1).setUsername("economist2");
        economists.get(1).setPassword("economist123");
        economists.get(1).setRole("Economist");
        economists.get(1).setName("Economist2");
        economists.get(1).setPhoneNumber("068-5640382");
        economists.get(1).setBirthday(new Date(1, 4, 1990));
        economists.get(1).setEmail("allen1990@gmail.com");
        economists.get(1).setSalary(700.0);
        economists.get(1).setIdCardNumber("J60398710I");
    }

    static void fillProducts()
    {
        products.add(new Product());
        products.get(0).setName("Sugar");
        products.get(0).setCategory(categories.get(2));
        products.get(0).setSupplierID(1);
        products.get(0).setPrice(0.5);
        products.get(0).setBarcode("ajbawRGawr.wr..wrgJWE");
        products.get(0).setQuantity(200);
        products.get(0).setLocation(1, 1, "Warehouse 1");
        products.get(0).setExpiryDate(new Date(10, 2, 2020));

        products.add(new Product());
        products.get(1).setName("Salt");
        products.get(1).setCategory(categories.get(2));
        products.get(1).setSupplierID(2);
        products.get(1).setPrice(1.0);
        products.get(1).setBarcode("aerogiaore..aergkQREGaj");
        products.get(1).setQuantity(200);
        products.get(1).setLocation(2, 1, "Warehouse 2");
        products.get(1).setExpiryDate(new Date(10, 2, 2021));

        products.add(new Product());
        products.get(2).setName("Apple");
        products.get(2).setCategory(categories.get(1));
        products.get(2).setSupplierID(2);
        products.get(2).setPrice(1.0);
        products.get(2).setBarcode("aerogiaore..aergkQREGaj");
        products.get(2).setQuantity(4);
        products.get(2).setLocation(2, 1, "Warehouse 2");
        products.get(2).setExpiryDate(new Date(10, 2, 2021));
    }

    static void fillBoughtProducts()
    {
        for(Product p : products)
        {
            boughtProducts.add(new BoughtProduct());

            int addedIndex = boughtProducts.size() - 1;
            BoughtProduct addedBoughtProd = boughtProducts.get(addedIndex);

            addedBoughtProd.setName(p.getName());
            addedBoughtProd.setID(p.getID());
            addedBoughtProd.setCategory(p.getCategory());
            addedBoughtProd.setSupplierID(p.getSupplierID());
            addedBoughtProd.setPrice(p.getPrice());
            addedBoughtProd.setBarcode(p.getBarcode());
            addedBoughtProd.setQuantity(p.getQuantity());
            addedBoughtProd.setExpiryDate(p.getExpiryDate());
        }
    }

    static void fillCategories()
    {
        categories.add("Fruit");
        categories.add("Spice");
        categories.add("Cooking");
    }

    private static void fillSuppliers()
    {
        suppliers.add(new Supplier("Supplier 1"));
        suppliers.add(new Supplier("Supplier 2"));
        suppliers.add(new Supplier("Supplier 3"));
        suppliers.add(new Supplier("Supplier 4"));

        suppliers.get(0).getProductsOffered().add("Apple");
        suppliers.get(0).getProductsOffered().add("Orange");
        suppliers.get(0).getProductsOffered().add("Banana");

        suppliers.get(1).getProductsOffered().add("Sugar");
        suppliers.get(1).getProductsOffered().add("Salt");
        suppliers.get(1).getProductsOffered().add("Pepper");

        suppliers.get(2).getProductsOffered().add("Fish");
        suppliers.get(2).getProductsOffered().add("Chicken");
        suppliers.get(2).getProductsOffered().add("Meat");

        suppliers.get(3).getProductsOffered().add("Milk");
        suppliers.get(3).getProductsOffered().add("Cheese");
        suppliers.get(3).getProductsOffered().add("Butter");
    }
}

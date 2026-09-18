package com.example.smartpantry;

public class Ingredient {
    private int id;
    private String name;
    private String unit;
    private double quantity;
    private String expiryDate;

    //Constructor initializing the ingredient information
    public Ingredient (int id, String name, String unit, double quantity, String expiryDate) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.expiryDate = expiryDate;

    }
    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

}

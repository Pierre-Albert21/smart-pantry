package com.example.smartpantry;

public class IngredientRecipe {
    private String ingredientName;
    private double ingredientQuantityRequired;
    private String ingredientUnit;

    public IngredientRecipe(String ingredientName, double ingredientQuantityRequired, String ingredientUnit) {
        this.ingredientName = ingredientName;
        this.ingredientQuantityRequired = ingredientQuantityRequired;
        this.ingredientUnit = ingredientUnit;
    }

    //Getters
    public String getIngredientName() {
        return ingredientName;
    }
    public double getIngredientQuantityRequired() {
        return ingredientQuantityRequired;
    }
    public String getIngredientUnit() {
        return ingredientUnit;
    }
}

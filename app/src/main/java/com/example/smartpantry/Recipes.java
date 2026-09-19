package com.example.smartpantry;

public class Recipes {
    private int recipeId;
    private String recipeName;
    private String recipeInstructions;

    public Recipes(int recipeId, String recipeName, String recipeInstructions) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
    }

    //Getters
    public int getRecipeId() {
        return recipeId;
    }
    public String getRecipeName() {
        return recipeName;
    }
    public String getRecipeInstructions() {
        return recipeInstructions;
    }
}

package com.example.smartpantry;

import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import org.w3c.dom.Text;

//Activity displaying recipes that could be made from ingredients in the pantry
public class SuggestedRecipesActivities extends AppCompatActivity {
    PantryDatabaseHelper dbHelper;
    //TextView displaying the suggested recipes
    LinearLayout recipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);
        //Connects TextView from XML to java
        recipeContainer = findViewById(R.id.recipeContainer);
        //Creates database helper
        dbHelper = new PantryDatabaseHelper(this);
        //Store all recipess from the DB into arrayList
        ArrayList<Recipes> recipes = dbHelper.getRecipes();
        //Get all ingredients stored in the users pantry
        ArrayList<Ingredient> pantry = dbHelper.getIngredients();
        boolean foundRecipe = false;

        //Checks each recipe to see if it could be made
        for(Recipes recipe : recipes) {

            //If recipe can be made stored name in list
            if(possibleRecipeMake(recipe, pantry)) {
                foundRecipe = true;
                TextView recipeText = new TextView(this);
                recipeText.setText(recipe.getRecipeName());
                recipeText.setTextSize(23);
                recipeText.setPadding(11, 21, 11, 21);
                recipeText.setOnClickListener(v -> {
                    Intent intent = new Intent(SuggestedRecipesActivities.this, RecipeDetails.class);
                    intent.putExtra("recipeId", recipe.getRecipeId());
                    startActivity(intent);
                });

                recipeContainer.addView(recipeText);
            }
        }
        //If no recipes can be made show clear message
        if(!foundRecipe) {
            TextView noRecipeFound = new TextView(this);
            noRecipeFound.setText("You have no suggested recipes - Add more ingredients!");
            noRecipeFound.setTextSize(19);
            recipeContainer.addView(noRecipeFound);
        }
    }

    //Compares if pantry contains enough of a certain ingredient required for the recipe
    private boolean hasIngredient(String requiredName, double quantityRequired, String unitRequired, ArrayList<Ingredient> myPantry) {
        //Go through each ingredient in the pantry
        for(Ingredient ingredient : myPantry) {
            //Checks if ingredient amount matches required ingredient
            if (pluralSingularMatch(ingredient.getName(), requiredName)) {
                //Also checks for singular and plural names of ingredient
                if (!unitMatching(ingredient.getUnit(), unitRequired)) {
                    continue;
                }
                //Converts quantity to base unit like Liter to milileter
                double pantryQuantity = unitConversion(ingredient.getQuantity(), ingredient.getUnit());
                //Converts quantity to base unit like Liter to milileter
                double recipeQuantity = unitConversion(quantityRequired, unitRequired);

                //Checks if pantry has enough of the ingredient
                if(pantryQuantity >= recipeQuantity) {
                    return true;
                }
            }
        }
        return false;
    }
    //Checks if user has every ingredient to make a recipe
    private boolean possibleRecipeMake(Recipes recipe, ArrayList<Ingredient> myPantry) {
        //Retrieve all ingredients needed for a specific recipe
        ArrayList<IngredientRecipe> ingredientsRequired = dbHelper.getIngredientRecipe(recipe.getRecipeId());
        //Check each ingredient required
        for(IngredientRecipe ingredient : ingredientsRequired) {
            //If one ingredient is missing the recipe cannot be made
            if(!hasIngredient(ingredient.getIngredientName(), ingredient.getIngredientQuantityRequired(), ingredient.getIngredientUnit(), myPantry)) {
                return false;
            }
        }
        return true;
    }
    //Converts unit to base unit example kg -> g
    private double unitConversion(double quantity, String unit) {
        if(unit.equalsIgnoreCase("kg")) {
            return quantity * 1000;
        }
        if (unit.equalsIgnoreCase("g")) {
            return quantity;
        }
        if (unit.equalsIgnoreCase("L")) {
            return quantity * 1000;
        }
        if (unit.equalsIgnoreCase("ml")) {
            return quantity;
        }
        return quantity;

    }
    //Checks if different units are compatible
    private boolean unitMatching(String pantryUnit, String recipeUnit) {
        //If units match the are compatible
        if(pantryUnit.equalsIgnoreCase(recipeUnit)) {
            return true;
        }
        //Kilograms and grams can be compared
        if((pantryUnit.equalsIgnoreCase("kg") && recipeUnit.equalsIgnoreCase("g")) || (pantryUnit.equalsIgnoreCase("g") && recipeUnit.equalsIgnoreCase("kg"))) {
            return true;
        }
        //Liter and millileter can be compared
        if((pantryUnit.equalsIgnoreCase("L") && recipeUnit.equalsIgnoreCase("ml")) || (pantryUnit.equalsIgnoreCase("ml") && pantryUnit.equalsIgnoreCase("L"))) {
            return true;
        }

        return false;
    }

    //Checks whether singular and plural names of ingredients match
    private boolean pluralSingularMatch(String pantryIngredient, String recipeIngredient) {
        //converts ingredients in pantry and in recipe to lowercase
        pantryIngredient = pantryIngredient.toLowerCase();
        recipeIngredient = recipeIngredient.toLowerCase();
        //Checks if both ingredients are the same
        if(pantryIngredient.equals(recipeIngredient)) {
            return true;
        }
        //Checks if ingredient in pantry has a plural name while recipe requires a singular version
        if(pantryIngredient.endsWith("s") && pantryIngredient.substring(0, pantryIngredient.length()-1).equals(recipeIngredient)) {
            return true;
        }
        //Checks if recipe uses a plural ingredient and pantry has a singular version
        if(recipeIngredient.endsWith("s") && recipeIngredient.substring(0, recipeIngredient.length()-1).equals(pantryIngredient)) {
            return true;
        }
        return false;
    }

}

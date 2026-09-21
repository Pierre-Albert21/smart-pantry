package com.example.smartpantry;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class RecipeDetails extends AppCompatActivity {
    TextView txtRecipeName;
    TextView txtRecipeIngredients;
    TextView txtRecipeInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);

        txtRecipeName = findViewById(R.id.txtRecipeName);
        txtRecipeIngredients = findViewById(R.id.txtRecipeIngredients);
        txtRecipeInstructions = findViewById(R.id.txtRecipeInstructions);
        PantryDatabaseHelper dbHelper = new PantryDatabaseHelper(this);

        int recipeId = getIntent().getIntExtra("recipeId", -1);
        if(recipeId == -1) {
            txtRecipeName.setText("Please select a recipe!");
            txtRecipeIngredients.setText("Ensure you have selected a recipe from the Suggested Recipes");
            txtRecipeInstructions.setText("");
            return;
        }

        ArrayList<Recipes> recipes = dbHelper.getRecipes();

        for(Recipes recipe : recipes) {
            if(recipe.getRecipeId() == recipeId) {
                txtRecipeName.setText(recipe.getRecipeName());
                txtRecipeInstructions.setText("instructions: \n\n" + recipe.getRecipeInstructions());
                ArrayList<IngredientRecipe> ingredients = dbHelper.getIngredientRecipe(recipe.getRecipeId());
                String ingredientTxt = "ingredients: \n\n";

                for(IngredientRecipe ingredient : ingredients) {
                    ingredientTxt = ingredientTxt + ingredient.getIngredientQuantityRequired() + " " + ingredient.getIngredientUnit() + " " + ingredient.getIngredientName() + "\n";
                }
                txtRecipeIngredients.setText(ingredientTxt);
                break;
            }
        }
    }
}

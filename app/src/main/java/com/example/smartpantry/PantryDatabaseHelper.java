package com.example.smartpantry;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.*;
import android.database.Cursor;
import java.util.ArrayList;

//Creation and management of the pantry database
public class PantryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SmartPantry.db";
    private static final int DB_VERSION = 2;
    //Constructor creating or opening the database
    public PantryDatabaseHelper(Context context) {

        super(context, DB_NAME, null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates the ingredients table
        String sqlQueryCreate = "CREATE TABLE ingredients (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "unit TEXT, " +
                "quantity REAL," +
                "expiryDate TEXT)";
        db.execSQL(sqlQueryCreate);

        //Creates the recipes table
        String sqlCreateRecipe = "CREATE TABLE recipes (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, " +
                "instructions TEXT)";
        db.execSQL(sqlCreateRecipe);

        //Creates table that stores ingredients for each recipe
        String sqlCreateIngredientRecipe = "CREATE table ingredient_recipes (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "recipeId INTEGER, " +
                "ingredientName TEXT, " +
                "quantityRequired REAL, " +
                "unitRequired TEXT)";
        db.execSQL(sqlCreateIngredientRecipe);

        //Creates first recipe
        ContentValues firstRecipe = new ContentValues();
        firstRecipe.put("name", "BBQ Chicken Pasta");
        //Insert recipe and store the ID inside variable
        firstRecipe.put("instructions", "Cook the pasta in a pot, Cook the chicken in a pan and add BBQ Sauce and Mayo. After pasta and chicken is cooked combine it. Sprinkle cheese on top");
        long firstRecipeId = db.insert("recipes", null, firstRecipe);

        //First Recipe Ingredient1
        ContentValues firstRecipeIngredient1 = new ContentValues();
        firstRecipeIngredient1.put("recipeId", firstRecipeId);
        firstRecipeIngredient1.put("ingredientName", "Pasta");
        firstRecipeIngredient1.put("quantityRequired", 250);
        firstRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, firstRecipeIngredient1);

        //First Recipe Ingredient2
        ContentValues firstRecipeIngredient2 = new ContentValues();
        firstRecipeIngredient2.put("recipeId", firstRecipeId);
        firstRecipeIngredient2.put("ingredientName", "Chicken");
        firstRecipeIngredient2.put("quantityRequired", 500);
        firstRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, firstRecipeIngredient2);

        //First Recipe Ingredient3
        ContentValues firstRecipeIngredient3 = new ContentValues();
        firstRecipeIngredient3.put("recipeId", firstRecipeId);
        firstRecipeIngredient3.put("ingredientName", "BBQ Sauce");
        firstRecipeIngredient3.put("quantityRequired", 125);
        firstRecipeIngredient3.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, firstRecipeIngredient3);

        //First Recipe Ingredient4
        ContentValues firstRecipeIngredient4 = new ContentValues();
        firstRecipeIngredient4.put("recipeId", firstRecipeId);
        firstRecipeIngredient4.put("ingredientName", "Mayo");
        firstRecipeIngredient4.put("quantityRequired", 125);
        firstRecipeIngredient4.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, firstRecipeIngredient4);

        //First Recipe Ingredient5
        ContentValues firstRecipeIngredient5 = new ContentValues();
        firstRecipeIngredient5.put("recipeId", firstRecipeId);
        firstRecipeIngredient5.put("ingredientName", "Cheese");
        firstRecipeIngredient5.put("quantityRequired", 100);
        firstRecipeIngredient5.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, firstRecipeIngredient5);

        //Second Recipe
        //Creates second recipe
        ContentValues secondRecipe = new ContentValues();
        secondRecipe.put("name", "Easy Omelette");
        //Insert recipe and store the ID inside variable
        secondRecipe.put("instructions", "Mix two eggs in a bowl until well combined. Add the mixture into a hot pan. Cook until mixture becomes solid. Add cheese on top. Fold the egg in half and enjoy.");
        long secondRecipeId = db.insert("recipes", null, secondRecipe);

        //Second Recipe Ingredient1
        ContentValues secondRecipeIngredient1 = new ContentValues();
        secondRecipeIngredient1.put("recipeId", secondRecipeId);
        secondRecipeIngredient1.put("ingredientName", "Eggs");
        secondRecipeIngredient1.put("quantityRequired", 2);
        secondRecipeIngredient1.put("unitRequired", "piece");
        db.insert("ingredient_recipes", null, secondRecipeIngredient1);

        //Second Recipe Ingredient2
        ContentValues secondRecipeIngredient2 = new ContentValues();
        secondRecipeIngredient2.put("recipeId", secondRecipeId);
        secondRecipeIngredient2.put("ingredientName", "Cheese");
        secondRecipeIngredient2.put("quantityRequired", 100);
        secondRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, secondRecipeIngredient2);

    }

    //Deletes table if there was changes in DB version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ingredients");
        onCreate(db);
    }

    //Adds an ingredient into my pantry
    public boolean addIngredient(String name, String unit, double quantity, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("unit", unit);
        values.put("quantity", quantity);
        values.put("expiryDate", expiryDate);

        //Inserts ingredient into the DB
        long result = db.insert("ingredients", null, values);
        return result != -1;
    }

    //Get all ingredients stored in the DB
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Gets all records in the ingredients table
        Cursor cursor = db.rawQuery("SELECT * FROM ingredients", null);
        //Goes through each record in the table
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
            double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow("quantity"));
            String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow("expiryDate"));

            //Creates the ingredient object with the information retrieved from the DB.
            Ingredient ingredient = new Ingredient(id, name, unit, quantity, expiryDate);
            //Adds the ingredients object in the arrayList
            ingredients.add(ingredient);
        }
        cursor.close();
        return ingredients;

    }

    //Gets all recipes from the DB
    public ArrayList<Recipes> getRecipes() {
        ArrayList<Recipes> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Get all the records from the recipes table
        Cursor cursor = db.rawQuery("SELECT * FROM recipes", null);
        //Go through each row in the recipe table
        //Store the recipe information in variables id, name and instructions
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String instructions = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));
            //Create recipe object with the information from each row
            Recipes recipe = new Recipes(id, name, instructions);
            //Add the recipe into the arrayList
            recipes.add(recipe);
        }
        cursor.close();
        return recipes;
    }
    //Queries DB and get ingredients about specific recipe
    public ArrayList<IngredientRecipe> getIngredientRecipe(int recipeId) {
        ArrayList<IngredientRecipe> ingredientRecipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Get a specific record that matches an specific recipeID
        Cursor cursor = db.rawQuery("SELECT * FROM ingredient_recipes WHERE recipeId = ?", new String[]{String.valueOf(recipeId)});
        //Go through each record
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("ingredientName"));
            double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow("quantityRequired"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("unitRequired"));

            //Creates the ingredientRecipe object using information from the DB
            IngredientRecipe ingredientRecipe = new IngredientRecipe(name, quantity, unit);
            //Adds the object into the arrayList
            ingredientRecipes.add(ingredientRecipe);

        }
        cursor.close();
        return ingredientRecipes;
    }

    //Delets an ingredient in the DB using an ID
    public boolean deleteIngredient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("ingredients", "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    //Allows for updating of an existing ingredient in the pantry
    public boolean editIngredient(int id, String name, String unit, double quantity, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //Stores the updated ingredient information
        values.put("name", name);
        values.put("unit", unit);
        values.put("quantity", quantity);
        values.put("expiryDate", expiryDate);

        //Updates the specific ingredient using the ID
        int result = db.update("ingredients", values, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

}

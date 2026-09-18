package com.example.smartpantry;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.*;
import android.database.Cursor;
import java.util.ArrayList;
public class PantryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SmartPantry.db";
    private static final int DB_VERSION = 2;

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

        //Creates table that links ingredients with recipes
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
        firstRecipeIngredient1.put("quantityRequired", "250");
        firstRecipeIngredient1.put("unitRequired", "g");

        //First Recipe Ingredient2
        ContentValues firstRecipeIngredient2 = new ContentValues();
        firstRecipeIngredient2.put("recipeId", firstRecipeId);
        firstRecipeIngredient2.put("ingredientName", "Chicken");
        firstRecipeIngredient2.put("quantityRequired", "500");
        firstRecipeIngredient2.put("unitRequired", "g");

        //First Recipe Ingredient3
        ContentValues firstRecipeIngredient3 = new ContentValues();
        firstRecipeIngredient2.put("recipeId", firstRecipeId);
        firstRecipeIngredient2.put("ingredientName", "BBQ Sauce");
        firstRecipeIngredient2.put("quantityRequired", "125");
        firstRecipeIngredient2.put("unitRequired", "ml");

        //First Recipe Ingredient4
        ContentValues firstRecipeIngredient4 = new ContentValues();
        firstRecipeIngredient4.put("recipeId", firstRecipeId);
        firstRecipeIngredient4.put("ingredientName", "Mayo");
        firstRecipeIngredient4.put("quantityRequired", "125");
        firstRecipeIngredient4.put("unitRequired", "ml");

        //First Recipe Ingredient2
        ContentValues firstRecipeIngredient5 = new ContentValues();
        firstRecipeIngredient5.put("recipeId", firstRecipeId);
        firstRecipeIngredient5.put("ingredientName", "Cheese");
        firstRecipeIngredient5.put("quantityRequired", "100");
        firstRecipeIngredient5.put("unitRequired", "g");

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
        secondRecipeIngredient1.put("quantityRequired", "2");
        secondRecipeIngredient1.put("unitRequired", "piece");

        //Second Recipe Ingredient2
        ContentValues secondRecipeIngredient2 = new ContentValues();
        secondRecipeIngredient2.put("recipeId", secondRecipeId);
        secondRecipeIngredient2.put("ingredientName", "Cheese");
        secondRecipeIngredient2.put("quantityRequired", "100");
        secondRecipeIngredient2.put("unitRequired", "g");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ingredients");
        onCreate(db);
    }

    public boolean addIngredient(String name, String unit, double quantity, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("unit", unit);
        values.put("quantity", quantity);
        values.put("expiryDate", expiryDate);

        long result = db.insert("ingredients", null, values);
        return result != -1;
    }

    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ingredients", null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
            double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow("quantity"));
            String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow("expiryDate"));

            Ingredient ingredient = new Ingredient(id, name, unit, quantity, expiryDate);
            ingredients.add(ingredient);
        }
        cursor.close();
        return ingredients;

    }

    public ArrayList<Recipes> getRecipies() {
        ArrayList<Recipes> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipes", null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String instructions = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));

            Recipes recipe = new Recipes(id, name, instructions);
            recipes.add(recipe);
        }
        cursor.close();
        return recipes;
    }

    public boolean deleteIngredient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("ingredients", "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean editIngredient(int id, String name, String unit, double quantity, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("unit", unit);
        values.put("quantity", quantity);
        values.put("expiryDate", expiryDate);

        int result = db.update("ingredients", values, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

}

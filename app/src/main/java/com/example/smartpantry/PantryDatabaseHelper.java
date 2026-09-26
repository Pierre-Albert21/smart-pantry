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

        //Third recipe
        ContentValues thirdRecipe = new ContentValues();
        thirdRecipe.put("name", "Salmon with sugar glaze");
        //Insert recipe and store the ID inside variable
        thirdRecipe.put("instructions", "Preheat the oven broiler and set an oven rack about 6 inches from the heat source. Grease the rack of a broiler pan with cooking spray.Whisk together brown sugar and mustard in a small bowl; spoon mixture evenly over salmon.Cook under the preheated broiler until fish flakes easily with a fork, 10 to 15 minutes.");
        long thirdRecipeId = db.insert("recipes", null, thirdRecipe);

        //Third recipe ingredient1
        ContentValues thirdRecipeIngredient1 = new ContentValues();
        thirdRecipeIngredient1.put("recipeId", thirdRecipeId);
        thirdRecipeIngredient1.put("ingredientName", "Salmon");
        thirdRecipeIngredient1.put("quantityRequired", 650);
        thirdRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, thirdRecipeIngredient1);

        //Third recipe ingredient2
        ContentValues thirdRecipeIngredient2 = new ContentValues();
        thirdRecipeIngredient2.put("recipeId", thirdRecipeId);
        thirdRecipeIngredient2.put("ingredientName", "Brown Sugar");
        thirdRecipeIngredient2.put("quantityRequired", 60);
        thirdRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, thirdRecipeIngredient2);

        //Third recipe ingredient3
        ContentValues thirdRecipeIngredient3 = new ContentValues();
        thirdRecipeIngredient3.put("recipeId", thirdRecipeId);
        thirdRecipeIngredient3.put("ingredientName", "Mustard");
        thirdRecipeIngredient3.put("quantityRequired", 8);
        thirdRecipeIngredient3.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, thirdRecipeIngredient3);

        //Fourth recipe
        ContentValues fourthRecipe = new ContentValues();
        fourthRecipe.put("name", "Pesto Chicken Rolls");
        //Insert recipe and store the ID inside variable
        fourthRecipe.put("instructions", "Spread 2 to 3 tablespoons of the pesto sauce onto each flattened chicken breast. Place cheese over the pesto. Roll up tightly, and secure with toothpicks. Place in a lightly greased baking dish.Bake uncovered for 45 to 50 minutes in the preheated oven, until chicken is nicely browned and juices run clear");
        long fourthRecipeId = db.insert("recipes", null, fourthRecipe);

        //Fourth recipe ingredient1
        ContentValues fourthRecipeIngredient1 = new ContentValues();
        fourthRecipeIngredient1.put("recipeId", fourthRecipeId);
        fourthRecipeIngredient1.put("ingredientName", "Chicken");
        fourthRecipeIngredient1.put("quantityRequired", 700);
        fourthRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, fourthRecipeIngredient1);

        //Fourth recipe ingredient2
        ContentValues fourthRecipeIngredient2 = new ContentValues();
        fourthRecipeIngredient2.put("recipeId", fourthRecipeId);
        fourthRecipeIngredient2.put("ingredientName", "Basil Pesto");
        fourthRecipeIngredient2.put("quantityRequired", 250);
        fourthRecipeIngredient2.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, fourthRecipeIngredient2);

        //Fourth recipe ingredient3
        ContentValues fourthRecipeIngredient3 = new ContentValues();
        fourthRecipeIngredient3.put("recipeId", fourthRecipeId);
        fourthRecipeIngredient3.put("ingredientName", "Cheese");
        fourthRecipeIngredient3.put("quantityRequired", 100);
        fourthRecipeIngredient3.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, fourthRecipeIngredient3);

        //Fifth recipe
        ContentValues fifthRecipe = new ContentValues();
        fifthRecipe.put("name", "Yummy Pork Chops");
        //Insert recipe and store the ID inside variable
        fifthRecipe.put("instructions", "In a bowl, mix the Italian-style salad dressing, soy sauce. lace the pork chops in a skillet over medium heat, and cover with the dressing mixture. Cover skillet, and cook pork chops 25 minutes, turning occasionally. Remove cover, reduce heat to low, and continue cooking to desired doneness");
        long fifthRecipeId = db.insert("recipes", null, fifthRecipe);

        //Fifth recipe ingredient1
        ContentValues fifthRecipeIngredient1 = new ContentValues();
        fifthRecipeIngredient1.put("recipeId", fifthRecipeId);
        fifthRecipeIngredient1.put("ingredientName", "Italian Salad Dressing");
        fifthRecipeIngredient1.put("quantityRequired", 500);
        fifthRecipeIngredient1.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, fifthRecipeIngredient1);

        //Fifth recipe ingredient2
        ContentValues fifthRecipeIngredient2 = new ContentValues();
        fifthRecipeIngredient2.put("recipeId", fifthRecipeId);
        fifthRecipeIngredient2.put("ingredientName", "Soy Sauce");
        fifthRecipeIngredient2.put("quantityRequired", 60);
        fifthRecipeIngredient2.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, fifthRecipeIngredient2);

        //Fifth recipe ingredient3
        ContentValues fifthRecipeIngredient3 = new ContentValues();
        fifthRecipeIngredient3.put("recipeId", fifthRecipeId);
        fifthRecipeIngredient3.put("ingredientName", "Pork Chops");
        fifthRecipeIngredient3.put("quantityRequired", 800);
        fifthRecipeIngredient3.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, fifthRecipeIngredient3);

        //Sixth recipe
        ContentValues sixthRecipe = new ContentValues();
        sixthRecipe.put("name", "Baked Chicken breast");
        //Insert recipe and store the ID inside variable
        sixthRecipe.put("instructions", "Gather all ingredients. Preheat the oven to 350 degrees F (175 degrees C). Lightly butter a baking dish.Stir together melted butter and salt in a bowl. Brush butter mixture onto chicken until thoroughly coated, pouring any extra over chicken.Bake in the preheated oven until no longer pink in the center and juices run clear, 30 to 45 minutes.");
        long sixthRecipeId = db.insert("recipes", null, sixthRecipe);

        //Sixth recipe ingredient1
        ContentValues sixthRecipeIngredient1 = new ContentValues();
        sixthRecipeIngredient1.put("recipeId", sixthRecipeId);
        sixthRecipeIngredient1.put("ingredientName", "Chicken breast");
        sixthRecipeIngredient1.put("quantityRequired", 500);
        sixthRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, sixthRecipeIngredient1);

        //Sixth recipe ingredient2
        ContentValues sixthRecipeIngredient2 = new ContentValues();
        sixthRecipeIngredient2.put("recipeId", sixthRecipeId);
        sixthRecipeIngredient2.put("ingredientName", "Butter");
        sixthRecipeIngredient2.put("quantityRequired", 125);
        sixthRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, sixthRecipeIngredient2);

        //Seventh recipe
        ContentValues seventhRecipe = new ContentValues();
        seventhRecipe.put("name", "Spicy lime shrimp");
        //Insert recipe and store the ID inside variable
        seventhRecipe.put("instructions", "Mix together Cajun seasoning, lime juice, and oil in a resealable plastic bag. Add shrimp, coat with marinade, squeeze out excess air, and seal the bag. Marinate in the refrigerator for 20 minutes.Remove shrimp from marinade; shake off excess.Cook shrimp on the preheated grill until bright pink on the outside and the meat is no longer transparent in the center, about 2 minutes per side.");
        long seventhRecipeId = db.insert("recipes", null, seventhRecipe);

        //Seventh recipe ingredient1
        ContentValues seventhRecipeIngredient1 = new ContentValues();
        seventhRecipeIngredient1.put("recipeId", seventhRecipeId);
        seventhRecipeIngredient1.put("ingredientName", "Shrimp");
        seventhRecipeIngredient1.put("quantityRequired", 600);
        seventhRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, seventhRecipeIngredient1);

        //Seventh recipe ingredient2
        ContentValues seventhRecipeIngredient2 = new ContentValues();
        seventhRecipeIngredient2.put("recipeId", seventhRecipeId);
        seventhRecipeIngredient2.put("ingredientName", "Cajun Seasoning");
        seventhRecipeIngredient2.put("quantityRequired", 10);
        seventhRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, seventhRecipeIngredient2);

        //Seventh recipe ingredient3
        ContentValues seventhRecipeIngredient3 = new ContentValues();
        seventhRecipeIngredient3.put("recipeId", seventhRecipeId);
        seventhRecipeIngredient3.put("ingredientName", "Lime");
        seventhRecipeIngredient3.put("quantityRequired", 1);
        seventhRecipeIngredient3.put("unitRequired", "piece");
        db.insert("ingredient_recipes", null, seventhRecipeIngredient3);

        //Eighth recipe
        ContentValues eighthRecipe = new ContentValues();
        eighthRecipe.put("name", "Cabbage and egg Noodle");
        //Insert recipe and store the ID inside variable
        eighthRecipe.put("instructions", "Bring a large pot of water to a boil. Add egg noodles and cook until the pasta is tender yet firm to the bite, about 5 minutes; drain. Meanwhile, melt butter in a large skillet over low heat and add the cabbage.Cover and cook until the cabbage begins to brown, 5 to 7 minutes. Add cooked noodles; cook and stir until the noodles begin to brown, about 5 minutes.");
        long eighthRecipeId = db.insert("recipes", null, eighthRecipe);

        //Eight recipe ingredient1
        ContentValues eightRecipeIngredient1 = new ContentValues();
        eightRecipeIngredient1.put("recipeId", eighthRecipeId);
        eightRecipeIngredient1.put("ingredientName", "Egg noodles");
        eightRecipeIngredient1.put("quantityRequired", 200);
        eightRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, eightRecipeIngredient1);

        //Eight recipe ingredient2
        ContentValues eightRecipeIngredient2 = new ContentValues();
        eightRecipeIngredient2.put("recipeId", eighthRecipeId);
        eightRecipeIngredient2.put("ingredientName", "Butter");
        eightRecipeIngredient2.put("quantityRequired", 50);
        eightRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, eightRecipeIngredient2);

        //Eight recipe ingredient3
        ContentValues eightRecipeIngredient3 = new ContentValues();
        eightRecipeIngredient3.put("recipeId", eighthRecipeId);
        eightRecipeIngredient3.put("ingredientName", "Green Cabbage");
        eightRecipeIngredient3.put("quantityRequired", 400);
        eightRecipeIngredient3.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, eightRecipeIngredient3);

        //ninth recipe
        ContentValues ninthRecipe = new ContentValues();
        ninthRecipe.put("name", "BBQ bacon and chicken bake");
        //Insert recipe and store the ID inside variable
        ninthRecipe.put("instructions", "Preheat oven to 350 degrees F (175 degrees C).Cook bacon in a skillet over medium heat until the edges begin to crisp, about 5 minutes; drain bacon on paper towels. Wrap each chicken breast with 2 slices of bacon in an x-shaped pattern and place into the prepared baking sheet with bacon ends underneath.Bake in the preheated oven for 30 minutes; spread barbeque sauce over chicken breasts and bake until the juices run clear,");
        long ninthRecipeId = db.insert("recipes", null, ninthRecipe);

        //ninth recipe ingredient1
        ContentValues ninthRecipeIngredient1 = new ContentValues();
        ninthRecipeIngredient1.put("recipeId", ninthRecipeId);
        ninthRecipeIngredient1.put("ingredientName", "Bacon");
        ninthRecipeIngredient1.put("quantityRequired", 200);
        ninthRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, ninthRecipeIngredient1);

        //ninth recipe ingredient2
        ContentValues ninthRecipeIngredient2 = new ContentValues();
        ninthRecipeIngredient2.put("recipeId", ninthRecipeId);
        ninthRecipeIngredient2.put("ingredientName", "Chicken");
        ninthRecipeIngredient2.put("quantityRequired", 500);
        ninthRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, ninthRecipeIngredient2);

        //ninth recipe ingredient3
        ContentValues ninthRecipeIngredient3 = new ContentValues();
        ninthRecipeIngredient3.put("recipeId", ninthRecipeId);
        ninthRecipeIngredient3.put("ingredientName", "BBQ Sauce");
        ninthRecipeIngredient3.put("quantityRequired", 180);
        ninthRecipeIngredient3.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, ninthRecipeIngredient3);

        //tenth recipe
        ContentValues tenthRecipe = new ContentValues();
        tenthRecipe.put("name", "Egg Salad");
        //Insert recipe and store the ID inside variable
        tenthRecipe.put("instructions", "Put eggs in boiling hot water for 7 mins. Take the eggs out and let it cool. Once cooled, peel the eggs and chop it. Mix the chopped eggs with mayo. Keep in fridge overnight");
        long tenthRecipeId = db.insert("recipes", null, tenthRecipe);

        //tenth recipe ingredient1
        ContentValues tenthRecipeIngredient1 = new ContentValues();
        tenthRecipeIngredient1.put("recipeId", tenthRecipeId);
        tenthRecipeIngredient1.put("ingredientName", "Eggs");
        tenthRecipeIngredient1.put("quantityRequired", 2);
        tenthRecipeIngredient1.put("unitRequired", "piece");
        db.insert("ingredient_recipes", null, tenthRecipeIngredient1);

        //tenth recipe ingredient2
        ContentValues tenthRecipeIngredient2 = new ContentValues();
        tenthRecipeIngredient2.put("recipeId", tenthRecipeId);
        tenthRecipeIngredient2.put("ingredientName", "Mayo");
        tenthRecipeIngredient2.put("quantityRequired", 100);
        tenthRecipeIngredient2.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, tenthRecipeIngredient2);

        //Eleventh recipe
        ContentValues eleventhRecipe = new ContentValues();
        eleventhRecipe.put("name", "Orange Chicken");
        //Insert recipe and store the ID inside variable
        eleventhRecipe.put("instructions", "Place the chicken inside an slow cooker. Add BBQ sauce, marmalade over the chicken. Add enough water to barely cover the chicken. Cook on low for 4 hours.");
        long eleventhRecipeId = db.insert("recipes", null, eleventhRecipe);

        //Eleventh recipe ingredient1
        ContentValues eleventhRecipeIngredient1 = new ContentValues();
        eleventhRecipeIngredient1.put("recipeId", eleventhRecipeId);
        eleventhRecipeIngredient1.put("ingredientName", "Chicken");
        eleventhRecipeIngredient1.put("quantityRequired", 500);
        eleventhRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, eleventhRecipeIngredient1);

        //Eleventh recipe ingredient2
        ContentValues eleventhRecipeIngredient2 = new ContentValues();
        eleventhRecipeIngredient2.put("recipeId", eleventhRecipeId);
        eleventhRecipeIngredient2.put("ingredientName", "BBQ Sauce");
        eleventhRecipeIngredient2.put("quantityRequired", 100);
        eleventhRecipeIngredient2.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, eleventhRecipeIngredient2);

        //Eleventh recipe ingredient3
        ContentValues eleventhRecipeIngredient3 = new ContentValues();
        eleventhRecipeIngredient3.put("recipeId", eleventhRecipeId);
        eleventhRecipeIngredient3.put("ingredientName", "Marmalade");
        eleventhRecipeIngredient3.put("quantityRequired", 100);
        eleventhRecipeIngredient3.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, eleventhRecipeIngredient3);

        //Twelfth recipe
        ContentValues twelfthRecipe = new ContentValues();
        twelfthRecipe.put("name", "French Dip Sandwich");
        //Insert recipe and store the ID inside variable
        twelfthRecipe.put("instructions", "In a pan grill the beef with onions and peppers. Add beef stock cube. Add the BBQ Sauce. Mix to combine.");
        long twelfthRecipeId = db.insert("recipes", null, twelfthRecipe);

        //Twelfth recipe ingredient1
        ContentValues twelfthRecipeIngredient1 = new ContentValues();
        twelfthRecipeIngredient1.put("recipeId", twelfthRecipeId);
        twelfthRecipeIngredient1.put("ingredientName", "Beef");
        twelfthRecipeIngredient1.put("quantityRequired", 500);
        twelfthRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, twelfthRecipeIngredient1);

        //Twelfth recipe ingredient2
        ContentValues twelfthRecipeIngredient2 = new ContentValues();
        twelfthRecipeIngredient2.put("recipeId", twelfthRecipeId);
        twelfthRecipeIngredient2.put("ingredientName", "Onions");
        twelfthRecipeIngredient2.put("quantityRequired", 1);
        twelfthRecipeIngredient2.put("unitRequired", "piece");
        db.insert("ingredient_recipes", null, twelfthRecipeIngredient2);

        //Twelfth recipe ingredient3
        ContentValues twelfthRecipeIngredient3 = new ContentValues();
        twelfthRecipeIngredient3.put("recipeId", twelfthRecipeId);
        twelfthRecipeIngredient3.put("ingredientName", "Peppers");
        twelfthRecipeIngredient3.put("quantityRequired", 1);
        twelfthRecipeIngredient3.put("unitRequired", "piece");
        db.insert("ingredient_recipes", null, twelfthRecipeIngredient3);

        //Thirteenth recipe
        ContentValues thirteenthRecipe = new ContentValues();
        thirteenthRecipe.put("name", "Peanut butter toast");
        //Insert recipe and store the ID inside variable
        thirteenthRecipe.put("instructions", "Toast the bread. Spread the peanut butter over the toasted bread slices.");
        long thirteenthRecipeId = db.insert("recipes", null, twelfthRecipe);

        //Thirteenth recipe ingredient1
        ContentValues thirteenthRecipeIngredient1 = new ContentValues();
        thirteenthRecipeIngredient1.put("recipeId", thirteenthRecipeId);
        thirteenthRecipeIngredient1.put("ingredientName", "Bread");
        thirteenthRecipeIngredient1.put("quantityRequired", 2);
        thirteenthRecipeIngredient1.put("unitRequired", "piece");
        db.insert("ingredient_recipes", null, thirteenthRecipeIngredient1);

        //Thirteenth recipe ingredient2
        ContentValues thirteenthRecipeIngredient2 = new ContentValues();
        thirteenthRecipeIngredient2.put("recipeId", thirteenthRecipeId);
        thirteenthRecipeIngredient2.put("ingredientName", "Peanut butter");
        thirteenthRecipeIngredient2.put("quantityRequired", 100);
        thirteenthRecipeIngredient2.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, thirteenthRecipeIngredient2);

        //fourteenth recipe
        ContentValues fourteenthRecipe = new ContentValues();
        fourteenthRecipe.put("name", "Korean Noodles");
        //Insert recipe and store the ID inside variable
        fourteenthRecipe.put("instructions", "Add the noodles to a pot of boiling hot water. Cook for 3 mins until medium to soft. In a pan combine soy sauce, oyster sauce and honey. Add the noodles into the sauce mixture and mix it well.");
        long fourteenthRecipeId = db.insert("recipes", null, fourteenthRecipe);

        //fourteenth recipe ingredient1
        ContentValues fourteenthRecipeIngredient1 = new ContentValues();
        fourteenthRecipeIngredient1.put("recipeId", fourteenthRecipeId);
        fourteenthRecipeIngredient1.put("ingredientName", "Noodles");
        fourteenthRecipeIngredient1.put("quantityRequired", 300);
        fourteenthRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, fourteenthRecipeIngredient1);

        //fourteenth recipe ingredient2
        ContentValues fourteenthRecipeIngredient2 = new ContentValues();
        fourteenthRecipeIngredient2.put("recipeId", fourteenthRecipeId);
        fourteenthRecipeIngredient2.put("ingredientName", "Soy sauce");
        fourteenthRecipeIngredient2.put("quantityRequired", 100);
        fourteenthRecipeIngredient2.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, fourteenthRecipeIngredient2);

        //fourteenth recipe ingredient3
        ContentValues fourteenthRecipeIngredient3 = new ContentValues();
        fourteenthRecipeIngredient3.put("recipeId", fourteenthRecipeId);
        fourteenthRecipeIngredient3.put("ingredientName", "Oyster sauce");
        fourteenthRecipeIngredient3.put("quantityRequired", 100);
        fourteenthRecipeIngredient3.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, fourteenthRecipeIngredient3);

        //fourteenth recipe ingredient4
        ContentValues fourteenthRecipeIngredient4 = new ContentValues();
        fourteenthRecipeIngredient4.put("recipeId", fourteenthRecipeId);
        fourteenthRecipeIngredient4.put("ingredientName", "Honey");
        fourteenthRecipeIngredient4.put("quantityRequired", 50);
        fourteenthRecipeIngredient4.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, fourteenthRecipeIngredient4);

        //Fifteenth recipe
        ContentValues fifteenthRecipe = new ContentValues();
        fifteenthRecipe.put("name", "Sweet potato bowl");
        //Insert recipe and store the ID inside variable
        fifteenthRecipe.put("instructions", "Cut sweet potato in small cubes. Place them in airfryer/oven for about 40mins until cooked. Grill the beef and cut into small piece. When serving, add the beef and sweet potato in one bowl. Drizzle some honey over the beef.");
        long fifteenthRecipeId = db.insert("recipes", null, fifteenthRecipe);

        //Fifteenth recipe ingredient1
        ContentValues fifteenthRecipeIngredient1 = new ContentValues();
        fifteenthRecipeIngredient1.put("recipeId", fifteenthRecipeId);
        fifteenthRecipeIngredient1.put("ingredientName", "Beef");
        fifteenthRecipeIngredient1.put("quantityRequired", 200);
        fifteenthRecipeIngredient1.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, fifteenthRecipeIngredient1);

        //Fifteenth recipe ingredient2
        ContentValues fifteenthRecipeIngredient2 = new ContentValues();
        fifteenthRecipeIngredient2.put("recipeId", fifteenthRecipeId);
        fifteenthRecipeIngredient2.put("ingredientName", "Sweet potato");
        fifteenthRecipeIngredient2.put("quantityRequired", 200);
        fifteenthRecipeIngredient2.put("unitRequired", "g");
        db.insert("ingredient_recipes", null, fifteenthRecipeIngredient2);

        //Fifteenth recipe ingredient3
        ContentValues fifteenthRecipeIngredient3 = new ContentValues();
        fifteenthRecipeIngredient3.put("recipeId", fifteenthRecipeId);
        fifteenthRecipeIngredient3.put("ingredientName", "Honey");
        fifteenthRecipeIngredient3.put("quantityRequired", 20);
        fifteenthRecipeIngredient3.put("unitRequired", "ml");
        db.insert("ingredient_recipes", null, fifteenthRecipeIngredient3);


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

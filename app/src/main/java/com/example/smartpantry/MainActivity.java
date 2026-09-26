package com.example.smartpantry;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View btnMenu;
    PantryDatabaseHelper dbHelper;
    TextView ingredients;
    TextView recipeDescribe;
    Button buttonViewPantry;
    Button buttonViewRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ingredients = findViewById(R.id.ingredients);
        recipeDescribe = findViewById(R.id.recipeDescription);
        buttonViewPantry = findViewById(R.id.buttViewPantry);
        buttonViewRecipes = findViewById(R.id.button2);
        dbHelper = new PantryDatabaseHelper(this);

        //Connecting the variables to xml elements
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> { drawerLayout.openDrawer(navigationView);});

        buttonViewPantry.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PantryActivity.class);
            startActivity(intent);
        });

        buttonViewRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivities.class);
            startActivity(intent);
        });

        int countIngredients = dbHelper.getIngredients().size();
        ingredients.setText(countIngredients + " Ingredients in pantry");
        //Link My Pantry to My Pantry screen
        navigationView.setNavigationItemSelectedListener(item -> {
            //Check if user clicks "My Pantry"
            if (item.getItemId() == R.id.nav_pantry) {
                Intent intent = new Intent(MainActivity.this, PantryActivity.class);
                startActivity(intent);
            }
            //Checks if user click add/edit ingredient in navigation menu
            if (item.getItemId() == R.id.nav_add_edit) {
                Intent intent = new Intent(MainActivity.this, EditAddIngredients.class);
                startActivity(intent);
            }
            //Checks if user clicked Suggested recipes in the navigation menu
            if (item.getItemId() == R.id.nav_recipes) {
                Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivities.class);
                startActivity(intent);
            }
            //Checks if user clicked Recipe details in the navigation menu
            if (item.getItemId() == R.id.nav_recipe_details) {
                Intent intent = new Intent(MainActivity.this, RecipeDetails.class);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.nav_settings) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
            drawerLayout.closeDrawer(navigationView);
            return true;
        });

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}
package com.example.smartpantry;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.ArrayList;

//An activity that displays and manages the ingredients in the pantry
public class PantryActivity extends AppCompatActivity {
    RecyclerView recyclePantry;
    PantryDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout of the screen
        setContentView(R.layout.activity_mypantry);
        //Connects the recyclerView from the xml file
        recyclePantry = findViewById(R.id.recyclerPantry);
        //Creates database helper
        dbHelper = new PantryDatabaseHelper(this);
        //Retrieves all the information about ingredients from the DB
        ArrayList<Ingredient> ingredients = dbHelper.getIngredients();
        //Creates the adapter
        IngredientAdapter adapt = new IngredientAdapter(ingredients, this);
        //Sets layout manager for recycler view
        recyclePantry.setLayoutManager(new LinearLayoutManager(this));
        //Connects recycler view and adapter
        recyclePantry.setAdapter(adapt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Ingredient> ingredients = dbHelper.getIngredients();
        IngredientAdapter adapt = new IngredientAdapter(ingredients, this);
        recyclePantry.setAdapter(adapt);
    }

    //Deletes an ingredient from the DB
    public void deleteIngredient(int id) {
        //Tries to delete the ingredient with the ID
        boolean delete = dbHelper.deleteIngredient(id);

        //Below executes if delete was successful
        if(delete) {
            //Retrieves updated list of ingredients
            ArrayList<Ingredient> ingredients = dbHelper.getIngredients();
            //New adapter using updated list
            IngredientAdapter adapt = new IngredientAdapter(ingredients, this);
            //Refreshes recyclerView
            recyclePantry.setAdapter(adapt);
        }
    }
}

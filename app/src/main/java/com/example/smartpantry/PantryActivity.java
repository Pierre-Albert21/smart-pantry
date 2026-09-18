package com.example.smartpantry;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.ArrayList;

public class PantryActivity extends AppCompatActivity {
    RecyclerView recyclePantry;
    PantryDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypantry);
        recyclePantry = findViewById(R.id.recyclerPantry);
        dbHelper = new PantryDatabaseHelper(this);
        ArrayList<Ingredient> ingredients = dbHelper.getIngredients();
        IngredientAdapter adapt = new IngredientAdapter(ingredients, this);
        recyclePantry.setLayoutManager(new LinearLayoutManager(this));
        recyclePantry.setAdapter(adapt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Ingredient> ingredients = dbHelper.getIngredients();
        IngredientAdapter adapt = new IngredientAdapter(ingredients, this);
        recyclePantry.setAdapter(adapt);
    }

    public void deleteIngredient(int id) {
        boolean delete = dbHelper.deleteIngredient(id);

        if(delete) {
            ArrayList<Ingredient> ingredients = dbHelper.getIngredients();
            IngredientAdapter adapt = new IngredientAdapter(ingredients, this);
            recyclePantry.setAdapter(adapt);
        }
    }
}

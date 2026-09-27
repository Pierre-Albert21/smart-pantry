package com.example.smartpantry;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditAddIngredients extends AppCompatActivity {
    EditText editIngredientName;
    EditText editQuanity;
    EditText editUnit;
    EditText editExpiryDate;
    Button btnSaveIngredient;
    PantryDatabaseHelper helper;
    boolean editing = false;
    int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_ingredients);

        //Linking variables to elements in XML
        editIngredientName = findViewById(R.id.editIngredientName);
        editQuanity = findViewById(R.id.editQuantity);
        editUnit = findViewById(R.id.editUnit);
        editExpiryDate = findViewById(R.id.editExpiryDate);
        helper = new PantryDatabaseHelper(this);
        btnSaveIngredient = findViewById(R.id.btnSaveIngredient);
        //Clicking save button calls saveIngredient function
        btnSaveIngredient.setOnClickListener(v -> saveIngredient());

        if(getIntent().hasExtra("ingredientId")) {
            editing = true;
            ingredientId = getIntent().getIntExtra("ingredientId", -1);
            editIngredientName.setText(getIntent().getStringExtra("ingredientName"));
            editUnit.setText(getIntent().getStringExtra("ingredientUnit"));
            editQuanity.setText(String.valueOf(getIntent().getDoubleExtra("ingredientQuantity", 0)));
            editExpiryDate.setText(getIntent().getStringExtra("ingredientExpiry"));
            btnSaveIngredient.setText("Update Ingredient");
        }

    }

    private void saveIngredient() {

        //Get the entered values from the fields
        String ingredientName = editIngredientName.getText().toString().trim();
        String ingredientQuantity = editQuanity.getText().toString().trim();
        String ingredientUnit = editUnit.getText().toString().trim();
        String ingredientExpiry = editExpiryDate.getText().toString().trim();

        //Checks that ingredient name contains only letters and spaces
        if(!ingredientName.matches("[a-zA-Z ]+")) {
            Toast.makeText(this, "Ingredient name can only contain letters and spaces", Toast.LENGTH_SHORT).show();
            return;
        }
        //Checks that the input fields are not empty
        if(ingredientName.isEmpty() || ingredientQuantity.isEmpty() || ingredientUnit.isEmpty() || ingredientExpiry.isEmpty()) {
            Toast.makeText(this, "Please ensure no fields are empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Ensures that valid unit measurements are used.
        if(!ingredientUnit.equalsIgnoreCase("g") && !ingredientUnit.equalsIgnoreCase("kg") && !ingredientUnit.equalsIgnoreCase("ml") && !ingredientUnit.equalsIgnoreCase("l") && !ingredientUnit.equalsIgnoreCase("piece")) {
            Toast.makeText(this, "Units accepted are only (kg, g, ml, l and piece)", Toast.LENGTH_SHORT).show();
            return;
        }
        double ingredientQuantityDouble;

        //Error handling to convert String to double
        //Also checks that quantity is valid
        try {
            ingredientQuantityDouble = Double.parseDouble(ingredientQuantity);
        } catch (NumberFormatException er){
            Toast.makeText(this, "Please enter valid quantity", Toast.LENGTH_SHORT).show();
            return;
        }
        //Checks that quantity is more than 0
        if (ingredientQuantityDouble <= 0) {
            Toast.makeText(this, "Quantity must be higher than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        //Checks that expiry date is actually valid
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-mm-dd");
        formatDate.setLenient(false);
        try {
            Date date = formatDate.parse(ingredientExpiry);
        } catch (Exception er) {
            Toast.makeText(this, "Please enter a valid expiry date", Toast.LENGTH_SHORT).show();
            return;
        }


        boolean saved;
        //Below is to clearly separate add from Edit, otherwise editing an ingredient adds a new ingredient
        //If user edits an ingredient it calls the editIngredient function
        if (editing) {
            saved = helper.editIngredient(ingredientId, ingredientName, ingredientUnit, ingredientQuantityDouble, ingredientExpiry);
        }
        //If user adds a new ingredient the addIngredient function is called
        else {
            saved = helper.addIngredient(ingredientName, ingredientUnit, ingredientQuantityDouble, ingredientExpiry);
        }
        //Display a Message that the ingredient was saved. Takes user back to My Pantry screen
        if(saved) {
            Toast.makeText(this, "Ingredient saved!", Toast.LENGTH_SHORT).show();
            //Resets the text in the text fields after ingredient was added.
            editIngredientName.setText("");
            editUnit.setText("");
            editQuanity.setText("");
            editExpiryDate.setText("");
        }
        //Displays appropriate message if the ingredient was not saved.
        else {
            Toast.makeText(this, "Ingredient not saved!", Toast.LENGTH_SHORT).show();
        }
        //Resets the text in the text fields after ingredient was added.
        editIngredientName.setText("");
        editUnit.setText("");
        editQuanity.setText("");
        editExpiryDate.setText("");
    }



}

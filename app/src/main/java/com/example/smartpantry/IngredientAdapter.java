package com.example.smartpantry;

import android.view.*;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import java.util.ArrayList;
//Adapter for displaying ingredients inside the recycler view
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    //ArrayList containing the ingredients
    private ArrayList<Ingredient> ingredients;
    private PantryActivity pantryActivity;
    //Constructor taking the ingredient list and pantryActivity as arguments
    public IngredientAdapter(ArrayList<Ingredient> ingredients, PantryActivity pantryActivity) {
        this.ingredients = ingredients;
        this.pantryActivity = pantryActivity;
    }
    //Creates a viewHolder for ingredient
    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(view);
    }
    //Inserts the ingredient data inside the item layout
    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        //Get the ingredient at current position
        Ingredient ingredient = ingredients.get(position);
        //Displays the quantity, unit and name of the ingredient
        holder.txtIngredientName.setText(ingredient.getQuantity() + " " + ingredient.getUnit() + " " + ingredient.getName());
        //Displays expiry date
        holder.txtIngredientExpiry.setText("Expires: " + ingredient.getExpiryDate());
        //Deletes ingredient when button is clicked
        holder.btnDeleteIngredient.setOnClickListener(v -> pantryActivity.deleteIngredient(ingredient.getId()));
        //Opens the Add/Edit screen when "Edit" button is clicked
        holder.btnEditIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), EditAddIngredients.class);
            //Send ingredient details to the Edit screen
            intent.putExtra("ingredientId", ingredient.getId());
            intent.putExtra("ingredientName", ingredient.getName());
            intent.putExtra("ingredientUnit", ingredient.getUnit());
            intent.putExtra("ingredientQuantity", ingredient.getQuantity());
            intent.putExtra("ingredientExpiry", ingredient.getExpiryDate());
            holder.itemView.getContext().startActivity(intent);
        });
    }
    //Returns count of ingredients.
    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView txtIngredientName;
        TextView txtIngredientExpiry;
        Button btnDeleteIngredient;
        Button btnEditIngredient;
        //Constructor that connects variables to the XML views
        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIngredientName = itemView.findViewById(R.id.txtIngredientName);
            txtIngredientExpiry = itemView.findViewById(R.id.txtIngredientExpiry);
            btnDeleteIngredient = itemView.findViewById(R.id.btnDeleteIngredient);
            btnEditIngredient = itemView.findViewById(R.id.btnEditIngredient);
        }
    }
}

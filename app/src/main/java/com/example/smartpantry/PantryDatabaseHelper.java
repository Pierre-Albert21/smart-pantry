package com.example.smartpantry;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.*;
import android.database.Cursor;
import java.util.ArrayList;
public class PantryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SmartPantry.db";
    private static final int DB_VERSION = 1;

    public PantryDatabaseHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQueryCreate = "CREATE TABLE ingredients (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "unit TEXT, " +
                "quantity REAL," +
                "expiryDate TEXT)";
        db.execSQL(sqlQueryCreate);
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

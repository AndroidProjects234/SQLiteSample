package com.example.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    //This class is responsible for performing all the operations with the database
    private static final int DATABASE_VERSION=1;    //update structure of the database
    private static final String DATABASE_NAME="products.db";  //We save it into file
    public static final String TABLE_PRODUCTS="products";   //name of the table
    public static final String COLUMN_ID="_id";                     //columns of table
    public static final String COLUMN_PRODUCTNAME="productname";

    public MyDBHelper( Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);//Creating database for the first time
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +         //Creating table for the first time
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_PRODUCTNAME + " TEXT " +
                ");";
            db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//Upgrade the version of database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);//Delete the table
        onCreate(db);//Creating a new one

    }

    //Add a new row to a database
    public void addProduct(Products product){
        ContentValues values=new ContentValues();//Allows to set different values to different columns
        values.put(COLUMN_PRODUCTNAME,product.get_productname());
        SQLiteDatabase db=getWritableDatabase();    //db is now equalto database we are going to write
        db.insert(TABLE_PRODUCTS,null,values);  //Insert the product into a table
        db.close();//Close the database


    }
    //Delete a product from a database
    public void deleteProduct(String productName){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    //Print out the database as string

    public String databaseToString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
       String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1 ";    // * means all coloumns 1 means all rows
        //Cursor points to a location in your results
        Cursor c= db.rawQuery(query,null);  //pointer to a results
        //Move to the first row in your results
        c.moveToFirst();
         while (!c.isAfterLast()) {   //Resultset having data
             if (c.getString(c.getColumnIndex("productname")) != null) {
                 dbString += c.getString(c.getColumnIndex("productname"));
                 dbString += "\n";
                 c.moveToNext();
             }
         }

        db.close();
        return dbString;
    }
}

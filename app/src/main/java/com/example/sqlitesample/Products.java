package com.example.sqlitesample;

public class Products {
    private int _id;
    private String _productname;            //Properties

    Products(){

    }

    Products(String productname){
        this._productname=productname;  //call the constructor with productname
    }

    public void set_id(int _id) {            //Setters allow to give an object one of these properties
        this._id = _id;
    }


    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    public int get_id() {                       //Getter return the value of the properties
        return _id;
    }

    public String get_productname() {
        return _productname;
    }
}

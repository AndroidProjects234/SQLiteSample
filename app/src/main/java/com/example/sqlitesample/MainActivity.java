package com.example.sqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText buckysInput;
    TextView buckysText;
    MyDBHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       buckysInput=(EditText)findViewById(R.id.buckysInput);
       buckysText=(TextView) findViewById(R.id.buckysText);
        dbHandler=new MyDBHelper(this,null,null,1);
        printDatabase();
    }

    //Add a product to the database
    public void addButtonClicked(View view){
        Products product=new Products(buckysInput.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    //Delete an items
    public void deleteButtonClicked(View view){
        String InputText=buckysInput.getText().toString();
        dbHandler.deleteProduct(InputText);
        printDatabase();
    }
     public void printDatabase(){   //Print the productname
        String dbString=dbHandler.databaseToString();
        buckysText.setText(dbString);
        buckysInput.setText("");
    }
}

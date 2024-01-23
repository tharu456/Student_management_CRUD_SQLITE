package com.example.nibmcrudsqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3, ed4,ed5, ed6;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.regno);
        ed3 = findViewById(R.id.age);
        ed4 = findViewById(R.id.gender);
        ed5 = findViewById(R.id.contactNo);
        ed6 = findViewById(R.id.parentNo);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    public void insert()
    {
        try
        {
            String name = ed1.getText().toString();
            String regNo = ed2.getText().toString();
            String age = ed3.getText().toString();
            String gender = ed4.getText().toString();
            String contactNo = ed5.getText().toString();
            String parentNo = ed6.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,regNo VARCHAR,age VARCHAR, gender VARCHAR, contactNo VARCHAR, parentNo VARCHAR)");

            String sql = "insert into records(name,regNo,age,gender,contactNo,parentNo)values(?,?,?,?,?,?)";
            SQLiteStatement statement = ((SQLiteDatabase) db).compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,regNo);
            statement.bindString(3,age);
            statement.bindString(4,gender);
            statement.bindString(5,contactNo);
            statement.bindString(6,parentNo);
            statement.execute();
            Toast.makeText(this,"Record added",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");

            ed1.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error occured",Toast.LENGTH_LONG).show();
        }
    }
}
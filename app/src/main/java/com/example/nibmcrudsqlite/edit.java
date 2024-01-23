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

public class edit extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        ed1 = findViewById(R.id.id);
        ed2 = findViewById(R.id.name);
        ed3 = findViewById(R.id.regno);
        ed4 = findViewById(R.id.age);
        ed5 = findViewById(R.id.gender);
        ed6 = findViewById(R.id.contactNo);
        ed7 = findViewById(R.id.parentNo);


        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        b3 = findViewById(R.id.bt3);


        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("regNo").toString();
        String t4 = i.getStringExtra("age").toString();
        String t5 = i.getStringExtra("gender").toString();
        String t6 = i.getStringExtra("contactNo").toString();
        String t7 = i.getStringExtra("parentNo").toString();

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);
        ed4.setText(t4);
        ed5.setText(t5);
        ed6.setText(t6);
        ed7.setText(t7);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);

            }
        });




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });

    }

    public void Delete()
    {
        try
        {
            String id = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);


            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed7.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error occurred in deleting information",Toast.LENGTH_LONG).show();
        }
    }
    public void Edit()
    {
        try
        {
            String id = ed1.getText().toString();
            String name = ed2.getText().toString();
            String regNo = ed3.getText().toString();
            String age = ed4.getText().toString();
            String gender = ed5.getText().toString();
            String contactNo = ed6.getText().toString();
            String parentNo = ed7.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);


            String sql = "update records set name = ?,regNo=?, age=?, gender=?, contactNo=?, parentNo=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,regNo);
            statement.bindString(3,age);
            statement.bindString(4,gender);
            statement.bindString(5,contactNo);
            statement.bindString(6,parentNo);
            statement.bindString(7,id);
            statement.execute();
            Toast.makeText(this,"Record Updated",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed7.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error occurred in updating information ",Toast.LENGTH_LONG).show();
        }

    }

}
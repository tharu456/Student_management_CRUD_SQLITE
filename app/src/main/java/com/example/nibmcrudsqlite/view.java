package com.example.nibmcrudsqlite;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class view extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int regNo = c.getColumnIndex("regNo");
        int age = c.getColumnIndex("age");
        int gender = c.getColumnIndex("gender");
        int contactNo = c.getColumnIndex("contactNo");
        int parentNo = c.getColumnIndex("parentNo");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this, R.layout.list_item_layout, titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<student> stud = new ArrayList<student>();


        if(c.moveToFirst())
        {
            do{
                student stu = new student();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.regNo = c.getString(regNo);
                stu.age = c.getString(age);
                stu.gender = c.getString(gender);
                stu.contactNo = c.getString(contactNo);
                stu.parentNo = c.getString(parentNo);
                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(name) + " \t "  + c.getString(regNo) + " \t "  + c.getString(age) + " \t " + c.getString(gender) + " \t " + c.getString(contactNo) + " \t " + c.getString(parentNo));

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();



        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                student stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(),edit.class);
                i.putExtra("id",stu.id);
                i.putExtra("name",stu.name);
                i.putExtra("regNo",stu.regNo);
                i.putExtra("age",stu.age);
                i.putExtra("gender",stu.gender);
                i.putExtra("contactNo",stu.contactNo);
                i.putExtra("parentNo",stu.parentNo);
                startActivity(i);

            }
        });

    }
}
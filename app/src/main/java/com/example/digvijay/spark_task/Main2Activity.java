package com.example.digvijay.spark_task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DBHelper db;
    ListView lv;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = findViewById(R.id.listView1);

        db = new DBHelper(this);
        showUsers();

        lv.setOnItemClickListener(this);

    }

    public void showUsers(){

        ArrayList<String> users = db.getAllUsers();

        aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        lv.setAdapter(aa);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent in = new Intent(getApplicationContext(),Main3Activity.class);
        in.putExtra("name",lv.getItemAtPosition(i).toString());
        startActivity(in);
    }
}

package com.example.digvijay.spark_task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DBHelper db;
    ArrayAdapter aa;
    ListView lv;
    int credits;
    String to ,from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        db = new DBHelper(this);

        lv = findViewById(R.id.listView1);
        lv.setOnItemClickListener(this);


        Bundle b = getIntent().getExtras();
        credits = b.getInt("credit");
        from = b.getString("name");
        showUsers();
    }

    public void showUsers() {

        ArrayList<String> users = db.getAllToSend(from);
        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        lv.setAdapter(aa);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent ic = new Intent(getApplicationContext(), Main2Activity.class);
        to = lv.getItemAtPosition(i).toString();
        updateCredits();
        startActivity(ic);
    }

    private void updateCredits() {
        db.updateUser(to,from,credits);
        Toast.makeText(getApplicationContext(),"Credits Successfully Transferred",Toast.LENGTH_SHORT).show();
    }
}

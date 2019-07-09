package com.example.digvijay.spark_task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button b;
    DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button1);
        db = new DBHelper(this);

    }

    public void showUsers(View view) {
        Intent i = new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}

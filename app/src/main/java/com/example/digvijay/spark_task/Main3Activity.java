package com.example.digvijay.spark_task;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    DBHelper db;
    User user;
    TextView tname,temail,tcredit;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db = new DBHelper(this);

        tname = findViewById(R.id.text_name);
        temail = findViewById(R.id.text_email);
        tcredit = findViewById(R.id.text_credit);

        Bundle b = getIntent().getExtras();
        String s  = b.getString("name");

        showData(s);
    }

    private void showData(String s) {
        user = db.getData(s);
        tname.setText(tname.getText()+user.getName());
        temail.setText(temail.getText()+user.getEmail());
        tcredit.setText(tcredit.getText()+String.valueOf(user.getCredits()));
    }

    public void transfer(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter The Credit");
        et = new EditText(this);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(et);
        builder.setIcon(R.drawable.credit);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(user.getCredits()< Integer.parseInt(et.getText().toString())){
                    Toast.makeText(getApplicationContext(),"You Dont Have Enough Credits",Toast.LENGTH_SHORT).show();
                }else{
                    Intent ic = new Intent(getApplicationContext(),Main4Activity.class);
                    ic.putExtra("name",user.getName());
                    ic.putExtra("credit",Integer.parseInt(et.getText().toString()));
                    startActivity(ic);
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Request Cancel",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}

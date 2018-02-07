package com.example.lokhandmanav.remind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {
DBHandler dbHandler;
TextView text;
String res[];
String r;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dbHandler = new DBHandler(START.appContext,null,null,1);
        text = (TextView)findViewById(R.id.data);
        //res = dbHandler.databaseToString(2,1);
   // Toast.makeText(START.appContext,Integer.toString(res.length),Toast.LENGTH_SHORT).show();


       // select.setText(bundle.getString("activity"));
    }
}

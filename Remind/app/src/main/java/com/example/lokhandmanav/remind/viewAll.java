package com.example.lokhandmanav.remind;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class viewAll extends AppCompatActivity {
DBHandler dbHandler;
String result[][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all2);
        dbHandler = new DBHandler(START.appContext,null,null,1);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout parent = (LinearLayout) inflater.inflate(R.layout.activity_view_all2,null);
        LinearLayout scrL =findViewById(R.id.scrL);
        //ScrollView scr = findViewById(R.id.scr);
        // View customView = findViewById(R.id.scrL);

         // customView = inflater.inflate(R.layout.activity_view_all2,null);

         result = dbHandler.databaseToString();

         if(result.length>0) {
            for (int i = 0; i < result.length; i++) {

                View custom = inflater.inflate(R.layout.custom, null);
                TextView tv = custom.findViewById(R.id.text);
                tv.setText(result[i][0]);
                scrL.addView(custom);

            }


        }
        else
        {
            View custom = inflater.inflate(R.layout.custom, null);
            TextView tv = (TextView) custom.findViewById(R.id.text);
            tv.setText("No Activities");
            scrL.addView(custom);
        }
        //setContentView(parent);
        }
}

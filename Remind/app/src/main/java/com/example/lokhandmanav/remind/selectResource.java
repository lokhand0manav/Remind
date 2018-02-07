package com.example.lokhandmanav.remind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class selectResource extends AppCompatActivity {

    public RadioButton wifi,bluetooth, hspa, lte;
    public static String resrc;
    activityJumper aJumper;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_resource);
        aJumper = new activityJumper();
        dbHandler = new DBHandler(START.appContext,null,null,1);
        wifi = (RadioButton) findViewById(R.id.wifi);
        bluetooth = (RadioButton) findViewById(R.id.bluetooth);
        hspa = (RadioButton) findViewById(R.id.hspa);
        lte = (RadioButton) findViewById(R.id.lte);
        //gesture = new GestureDetectorCompat(this, new activityJumper(this, START.class , selectTimer.class));

        wifi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wifi.setChecked(true);
                bluetooth.setChecked(false);
                hspa.setChecked(false);
                lte.setChecked(false);
            }
        });

        bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wifi.setChecked(false);
                bluetooth.setChecked(true);
                hspa.setChecked(false);
                lte.setChecked(false);
            }
        });

         hspa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wifi.setChecked(false);
                bluetooth.setChecked(false);
                hspa.setChecked(true);
                lte.setChecked(false);
            }
        });

          lte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wifi.setChecked(false);
                bluetooth.setChecked(false);
                hspa.setChecked(false);
                lte.setChecked(true);
            }
        });
    }

    public void onGo(View v)
    {
        if(wifi.isChecked())
        {
            resrc = "1";
        }
        if(bluetooth.isChecked())
        {
            resrc = "2";
        }
        if(hspa.isChecked())
        {
            resrc = "3";
        }
        if(lte.isChecked())
        {
            resrc = "4";
        }

        int check = dbHandler.addValues(START.act,new String[]{"2",resrc} ,"0");
        new activityJumper().new Jump(this, viewAll.class);
       // Toast.makeText(this,Integer.toString(check),Toast.LENGTH_SHORT).show();
        /*Bundle bundle = getIntent().getExtras();
        aJumper.addBundle("activity",bundle.getString("activity"));
        aJumper.addBundle("trigger",resrc);
        aJumper.new Jump(this, TestActivity.class);
        */

    }

}

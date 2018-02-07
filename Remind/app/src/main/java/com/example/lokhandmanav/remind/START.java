package com.example.lokhandmanav.remind;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class START extends AppCompatActivity
{

    public GestureDetectorCompat gesture;
    public RadioButton gps, resource, time;
    EditText activity ;
    DBHandler dbHandler;
    public static String act;
    public static Context appContext;
    activityJumper aJumper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        dbHandler = new DBHandler(this, null, null, 1);
        appContext = getApplicationContext();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

        aJumper = new activityJumper();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            registerReceiver(new TriggerReceiver() , new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        /*IntentFilter filter3 = new IntentFilter();
        filter3.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter3.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        registerReceiver(new TriggerReceiver() , filter3);
*/
        //Toast.makeText(this,"Created",Toast.LENGTH_SHORT).show();

        gps = (RadioButton) findViewById(R.id.gps);
        resource = (RadioButton) findViewById(R.id.resource);
        time = (RadioButton) findViewById(R.id.time);
        //gesture = new GestureDetectorCompat(this, new activityJumper(this, START.class , selectTimer.class));
        activity = (EditText) findViewById(R.id.activity);



        gps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gps.setChecked(true);
                resource.setChecked(false);
                time.setChecked(false);
            }
        });

        resource.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gps.setChecked(false);
                jumping(selectResource.class);
                resource.setChecked(true);
                time.setChecked(false);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gps.setChecked(false);
                jumping(selectTimer.class);
                resource.setChecked(false);
                 time.setChecked(true);
            }
        });


    }


    /*

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);

    }
*/

    public void jumping(Class c)
    {
        if(!activity.getText().toString().matches("\\s*")) {
            //a.addActivity(activity.getText().toString());
            //aJumper.addBundle("activity", activity.getText().toString());
            act = activity.getText().toString();
            aJumper.new Jump(this, c);

        }
            else
            Toast.makeText(this,"Enter The Activity",Toast.LENGTH_SHORT).show();
    }

 }


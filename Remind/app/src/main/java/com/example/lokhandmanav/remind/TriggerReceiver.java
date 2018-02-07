package com.example.lokhandmanav.remind;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class TriggerReceiver extends BroadcastReceiver {

     String bl;
     String internet;
     DBHandler dbHandler;
     String result[][];
     String alarm ;
     Context context;
     Intent intent;
    @Override

    public void onReceive(Context context, Intent intent)
    {
        bl = "android.bluetooth.device.action.ACL_CONNECTED";
        internet = "android.net.conn.CONNECTIVITY_CHANGE";
        alarm= "com.Reminder.AlarmReceived";
        this.context = context;
        this.intent = intent;

        if(intent.getAction().equals(alarm))
        {
            Toast.makeText(context,"alarmReceived",Toast.LENGTH_SHORT).show();
            Bundle bundle = intent.getExtras();
            Toast.makeText(context, bundle.getString("Activity"),Toast.LENGTH_SHORT).show();
            if (bundle != null)
            {

                new NotificationPush(bundle.getString("Activity"),1001);
                Toast.makeText(context, bundle.getString("Activity"),Toast.LENGTH_SHORT).show();

            }
            Toast.makeText(context,intent.getExtras().getString("activity"),Toast.LENGTH_SHORT).show();
        }
       // new NotificationPush(intent.getAction(),2);

        /*
        if(intent.getAction().equals(alarm))
        {
            Toast.makeText(context,"alarm",Toast.LENGTH_SHORT).show();
            result = dbHandler.forTime(intent.getExtras().getString("activity"),
                        Integer.parseInt(intent.getExtras().getString("freq")));
            if(!result[0][0].equals("0"))
            {
                for(int j = 0; j<result.length; j++)
                {

                    new NotificationPush(result[j][0],Integer.parseInt(result[j][1]));
                    dbHandler.delete(result[j][1]);
                }

                //Toast.makeText(context,result[0],Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(context,"received Broadcast",Toast.LENGTH_SHORT).show();
            /// /new NotificationPush("alarm",1);
        }*/

        if(intent.getAction().equals(bl))
        {
         checkBluetooth();
        }

        if(intent.getAction().equals(internet))
        {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if(wifi.isConnected())
            {
             checkWifi();
            }
            if(data.isConnected())
            {
               checkData();
            }

        }
        //Toast.makeText(context,"received Broadcast",Toast.LENGTH_SHORT).show();

    }
    public void checkBluetooth()
    {
        dbHandler = new DBHandler(START.appContext,null,null,1);
        int n;
        result = dbHandler.databaseToString(2,2);

        if(!result[0][0].equals("0"))
        {
            for(int j = 0; j<result.length; j++)
            {

                new NotificationPush(result[j][0],Integer.parseInt(result[j][1]));
                dbHandler.delete(result[j][1]);
            }

            //Toast.makeText(context,result[0],Toast.LENGTH_SHORT).show();
        }
    }
    public void checkWifi()
    {
        dbHandler = new DBHandler(START.appContext,null,null,1);

        result =  dbHandler.databaseToString(2,1);


        if(!result[0][0].equals("0"))
        {
            for(int j = 0; j<result.length; j++)
            {
                new NotificationPush(result[j][0],Integer.parseInt(result[j][1]));
                dbHandler.delete(result[j][1]);
            }
            //Toast.makeText(context,result[0],Toast.LENGTH_SHORT).show();
        }
    }


    public void checkData()
    {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

       // if(ContextCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE)==0)
       // {
            int netType = telephonyManager.getDataNetworkType();

            if( netType== 8 || netType== 10 ||netType== 15||netType== 9 )
            {
                dbHandler = new DBHandler(START.appContext,null,null,1);

                result =  dbHandler.databaseToString(2,3);


                if(!result[0][0].equals("0"))
                {
                    for(int j = 0; j<result.length; j++)
                    {
                        new NotificationPush(result[j][0],Integer.parseInt(result[j][1]));
                        dbHandler.delete(result[j][1]);
                    }
                    //Toast.makeText(context,result[0],Toast.LENGTH_SHORT).show();
                }
            }
            if(netType == 13)
            {
                dbHandler = new DBHandler(START.appContext,null,null,1);
                int n;
                result = dbHandler.databaseToString(2,4);


                if(!result[0][0].equals("0"))
                {
                    for(int j = 0; j<result.length; j++)
                    {
                        new NotificationPush(result[j][0],Integer.parseInt(result[j][1]));
                        dbHandler.delete(result[j][1]);
                    }
                    //Toast.makeText(context,result[0],Toast.LENGTH_SHORT).show();
                }
            }
       // }


    }


}

package com.example.lokhandmanav.remind;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.Calendar;

public class selectFrequency extends AppCompatActivity {
//DBHandler dbHandler;
RadioButton daily;
RadioButton weekly;
RadioButton monthly;
DBHandler dbHandler;
static String freq;
public Calendar c;

Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_frequency);
        dbHandler = new DBHandler(this,null,null,1);

        daily = (RadioButton)findViewById(R.id.daily);
        weekly = (RadioButton)findViewById(R.id.weekly);
        monthly = (RadioButton)findViewById(R.id.monthly);

        daily.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                daily.setChecked(true);
                weekly.setChecked(false);
                monthly.setChecked(false);
            }
        });

        weekly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                daily.setChecked(false);
                weekly.setChecked(true);
                monthly.setChecked(false);
            }
        });

        monthly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                daily.setChecked(false);
                weekly.setChecked(false);
                monthly.setChecked(true);
            }
        });
       // dbHandler.addValues(START.act,new String[]{"2",resrc} ,"0");
    }

    public void clickGo(View view)
    {

        if(daily.isChecked())
        {
            freq = "1";
        }
        else if(weekly.isChecked())
        {
            freq = "2";
        }
        else if(monthly.isChecked())
        {
            freq = "3";
        }
        else
            {
                freq = "0";
            }
        int check = dbHandler.addValues(START.act,new String[]{"3","0"} ,freq);

        c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,selectTimer.timeHour);
        c.set(Calendar.MINUTE,selectTimer.timeMinute);

        Intent intent = new Intent(this, TriggerReceiver.class);
        intent.setAction("com.Reminder.AlarmReceived");
        intent.putExtra("Activity",START.act);
        intent.putExtra("freq",freq);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)START.appContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, c.getTimeInMillis(), pendingIntent);

        new activityJumper().new Jump(this, viewAll.class);
    }
}

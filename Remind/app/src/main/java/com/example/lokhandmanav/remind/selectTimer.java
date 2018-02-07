package com.example.lokhandmanav.remind;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class selectTimer extends AppCompatActivity {

    private GestureDetectorCompat gesture;
    TextView select;
    TimePicker timer;
    public static int timeHour;
    public static int timeMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_timer);
        select = (TextView)findViewById(R.id.select);
        timer = (TimePicker)findViewById(R.id.pickTime);

        //gesture = new GestureDetectorCompat(this, new activityJumper(this, START.class,selectTimer.class));

    }
/*
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);

    }
*/
    public void clickGo(View view)
    {
        /*Bundle bundle = getIntent().getExtras();
        select.setText(bundle.getString("activity"));
        */
        if(Build.VERSION.SDK_INT < 23)
            {
                timeHour = timer.getCurrentHour();
                timeMinute = timer.getCurrentMinute();
            }

        else
            {
            timeMinute = timer.getMinute();
            timeHour = timer.getHour();
            }
            //Toast.makeText(this,Integer.toString(timeHour)+"  "+Integer.toString(timeMinute),Toast.LENGTH_SHORT).show();
        new activityJumper().new Jump(this, selectFrequency.class);
    }
}

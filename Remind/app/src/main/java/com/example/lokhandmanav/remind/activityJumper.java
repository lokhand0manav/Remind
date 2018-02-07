package com.example.lokhandmanav.remind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by LokhandManav on 04-11-2017.
 */



    public class activityJumper extends GestureDetector.SimpleOnGestureListener {
        Class nextActivity;
        Class previousActivity;
        Context currentActivity;
        Bundle bundle;

        activityJumper()
        {

        }
        public void addBundle(String key , String value)
        {
            Bundle bundle = new Bundle();
            bundle.putString(key,value);
        }
        activityJumper(Context context, Class c1, Class c2)
        {
            nextActivity = c2;
            previousActivity = c1;
            currentActivity = context;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() > e2.getX()) {
                //right to left
                new Jump(currentActivity,nextActivity);
            } else if (e1.getX() < e2.getX()) {
                //left to right
                new Jump(currentActivity,previousActivity);
            }
            return true;
        }


        public class Jump
        {
               Jump(Context context, Class c)
            {
                Intent intent = new Intent(context, c);
                //((Activity) context).finish();
                //intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(intent);

            }
        }

    }


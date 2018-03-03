package com.example.lokhandmanav.remind;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class View extends AppWidgetProvider {
    private static final String ACTION_GET_ACTIVITY = "ACTION_BROADCASTWIDGETSAMPLE";
    DBHandler dbHandler;
    static String result[][];
    static int index = 0; //index for result[][]

    static final String Resource = "ACTION_GET_RESOURCE"; // for resource
    static final String Time= "ACTION_GET_TIME";
    static final String GPS = "ACTION_GET_GPS";
    static final String Next = "ACTION_GET_NEXT";
    static final String Previous = "ACTION_GET_PREVIOUS";
    static final String Add = "ACTION_GET_START";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.view);
        // Construct an Intent which is pointing this class.

        views.setOnClickPendingIntent(R.id.Resources, pendingIntent(context,Resource));
        views.setOnClickPendingIntent(R.id.GPS, pendingIntent(context,GPS));
        views.setOnClickPendingIntent(R.id.Time, pendingIntent(context,Time));
        views.setOnClickPendingIntent(R.id.Next, pendingIntent(context,Next));
        views.setOnClickPendingIntent(R.id.Previous, pendingIntent(context,Previous));
        //views.setOnClickPendingIntent(R.id.add, pendingIntent(context,Add));
        // Instruct the widget manager to update the widget

        Intent configIntent = new Intent(context, START.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        views.setOnClickPendingIntent(R.id.add, configPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static PendingIntent pendingIntent(Context context,String button)
    {
        Intent intent = new Intent(context, View.class);
        intent.setAction(button);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.view);

        if (Resource.equals(intent.getAction())) {
            index = 0;
            dbHandler = new DBHandler(START.appContext,null,null,1);
            result = dbHandler.databaseToString(2,999); // 2 for resource 999 for all trigger value
            views.setTextViewText(R.id.act, result[0][0]);
        }
        if (GPS.equals(intent.getAction())) {
            index = 0;
            dbHandler = new DBHandler(START.appContext,null,null,1);
            result = dbHandler.databaseToString(1,999); // 2 for resource 999 for all trigger value
            views.setTextViewText(R.id.act, result[0][0]);
        }
        if (Time.equals(intent.getAction())) {
            index = 0;
            dbHandler = new DBHandler(START.appContext,null,null,1);
            result = dbHandler.databaseToString(3,999); // 2 for resource 999 for all trigger value
            views.setTextViewText(R.id.act, result[0][0]);
        }
        if(Next.equals(intent.getAction()))
        {
            //dbHandler = new DBHandler(START.appContext,null,null,1);
            //result = dbHandler.databaseToString(2,999); // 2 for resource 999 for all trigger value
            index = (index + 1)%result.length; // result[contains all activit][contains id]
            views.setTextViewText(R.id.act, result[index][0]);
        }
        if(Previous.equals(intent.getAction()))
        {
            //dbHandler = new DBHandler(START.appContext,null,null,1);
            //result = dbHandler.databaseToString(2,999); // 2 for resource 999 for all trigger value
            if(index>0)
            index = (index - 1);
            views.setTextViewText(R.id.act, result[index][0]);
        }

        ComponentName appWidget = new ComponentName(context, View.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(appWidget, views);
    }
}


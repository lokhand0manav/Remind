package com.example.lokhandmanav.remind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by LokhandManav on 05-11-2017.
 */


public class DBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;

    //database
    private static final String DATABASE_NAME = "Remind.db";


    //finalTables

    public static final String TABLE_Activity = "activity";
        public static final String COLUMN_Activity_ID = "actNo"; //use this primary key with others primary to identify
        public static final String COLUMN_Activity = "Activity";
        //public static final String COLUMN_Activity_TriggerFK = "TriggerFK";
        //public static final String COLUMN_Activity_FreqFK = "FreFK";
    public static final String TABLE_Trig = "trig";
        public static final String COLUMN_trigger_ID = "triggerNo";
        public static final String COLUMN_trig = "Trig";
        public static final String COLUMN_trigger_Value = "triggerValue";
    public static final String TABLE_Frequency = "frequency";
        public static final String COLUMN_Freq_ID = "freqNo";
        public static final String COLUMN_Freq = "freq";

    //temperoryTables
/*
    public static final String TABLE_Temp_Activity = "TempActivity";
        public static final String COLUMN_Temp_Activity = "activity";
    public static final String TABLE_Temp_Trigger = "TempTrigger";
        public static final String COLUMN_Temp_trigger = "trigger";
        public static final String COLUMN_Temp_triggerValue = "triggerValue";
    public static final String TABLE_Temp_Freq = "TempFreq";
        public static final String COLUMN_Temp_Freq = "freq";
*/

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
    super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryAct = "CREATE TABLE " + TABLE_Activity + "(" +
                        COLUMN_Activity_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_Activity +" TEXT "+
                        //COLUMN_Activity_TriggerFK +" INTEGER AUTOINCREMENT, "+
                        //COLUMN_Activity_FreqFK +" INTEGER AUTOINCREMENT "+
                        ");";

        String queryTrig = "CREATE TABLE " + TABLE_Trig + "(" +
                COLUMN_trigger_ID+" INTEGER, " +
                COLUMN_trig +" TEXT, "+
                COLUMN_trigger_Value +" INTEGER "+
                ");";

        String queryFreq = "CREATE TABLE " + TABLE_Frequency + "(" +
                COLUMN_Freq_ID+" INTEGER, " +
                COLUMN_Freq +" TEXT "+
                ");";

        db.execSQL(queryAct);
        db.execSQL(queryFreq);
        db.execSQL(queryTrig);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_Activity);
        db.execSQL("DROP TABLE " + TABLE_Frequency);
        db.execSQL("Drop table " + TABLE_Trig);
        onCreate(db);
    }
    public int addValues(String activity,String Trigger[],String frequency)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues actVal = new ContentValues();
        //value Inserting to Activity;
        actVal.put(COLUMN_Activity,activity);
        long act = db.insert(TABLE_Activity,null,actVal);

        //String query = "SELECT "+COLUMN_Activity_ID+" from "+TABLE_Activity+" where "+COLUMN_Activity+" = "+activity;

        //value Inseting to Trigger
        ContentValues trigVal = new ContentValues();

        trigVal.put(COLUMN_trigger_ID, (int)act);
        trigVal.put(COLUMN_trig,Trigger[0]);
        trigVal.put(COLUMN_trigger_Value, Trigger[1]);

        int trig = (int)db.insert(TABLE_Trig,null,trigVal);

        //value Inserting to Trigger
        ContentValues freqVal = new ContentValues();
        freqVal.put(COLUMN_Freq_ID,(int)act);
        freqVal.put(COLUMN_Freq,frequency);
        int freq = (int)db.insert(TABLE_Frequency,null,freqVal);

        return trig;
    }



/*
    public int addStudent(Students student)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.get_roll());
        values.put(COLUMN_NAME, student.get_studentname());
        values.put(COLUMN_CONTACT, student.get_contact());
        SQLiteDatabase db = getWritableDatabase();
        int in = (int)db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return in;
    }
*/

    public String[][] databaseToString(int trig,int trigValue)
    {
        String dbString[][];
        String nullString[][] = new String[1][1];
        nullString[0][0] = "0";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT " + COLUMN_Activity +","+COLUMN_Activity_ID+ " FROM " + TABLE_Trig + "," + TABLE_Activity + " WHERE " +
                COLUMN_trig + " = " + trig + " and " + COLUMN_trigger_Value + " = " + trigValue +" and "+ COLUMN_trigger_ID +" = "+COLUMN_Activity_ID;

        Cursor recordSet = db.rawQuery(query, null);

        recordSet.moveToFirst();
        int count = recordSet.getCount();
        //Toast.makeText(START.appContext,Integer.toString(count),Toast.LENGTH_SHORT).show();
        if (count > 0)
        {
            dbString = new String[count][2];
            for (int j = 0; j < count; j++)
            {

                dbString[j][0] = recordSet.getString(recordSet.getColumnIndex(COLUMN_Activity));
                dbString[j][1] = recordSet.getString(recordSet.getColumnIndex(COLUMN_Activity_ID));
                recordSet.moveToNext();
            }
            db.close();
            recordSet.close();
            //Toast.makeText(START.appContext,dbString[2],Toast.LENGTH_SHORT).show();
            return dbString;
        }
        else
        {
            return nullString;
        }
    }
    public String[][] databaseToString()
    {
        String dbString[][];
        String nullString[][] = new String[1][1];
        nullString[0][0] = "0";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT " + COLUMN_Activity +","+COLUMN_Activity_ID+ " FROM "
                        + TABLE_Trig + ","
                        + TABLE_Activity +","
                        +TABLE_Frequency +" WHERE " +
                        COLUMN_trigger_ID +" = "+COLUMN_Activity_ID+" and "+
                        COLUMN_Freq_ID +" = "+COLUMN_Activity_ID;

        Cursor recordSet = db.rawQuery(query, null);

        recordSet.moveToFirst();
        int count = recordSet.getCount();
        //Toast.makeText(START.appContext,Integer.toString(count),Toast.LENGTH_SHORT).show();
        if (count > 0)
        {
            dbString = new String[count][2];
            for (int j = 0; j < count; j++)
            {

                dbString[j][0] = recordSet.getString(recordSet.getColumnIndex(COLUMN_Activity));
                dbString[j][1] = recordSet.getString(recordSet.getColumnIndex(COLUMN_Activity_ID));
                recordSet.moveToNext();
            }
            db.close();
            recordSet.close();
            //Toast.makeText(START.appContext,dbString[2],Toast.LENGTH_SHORT).show();
            return dbString;
        }
        else
        {
            return nullString;
        }
    }
    public String[][] forTime(String activity, int freq)
    {
        String dbString[][];
        String nullString[][] = new String[1][1];
        nullString[0][0] = "0";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT " + COLUMN_Activity +","+COLUMN_Activity_ID+ " FROM " + TABLE_Trig + "," + TABLE_Activity +"," + TABLE_Activity +
                " WHERE " + COLUMN_trig + " = 3 and " + COLUMN_Freq + " = " + freq +
                " and "+ COLUMN_Activity+" = "+activity+
                " and "+ COLUMN_trigger_ID +" = "+COLUMN_Activity_ID+
                " and "+ COLUMN_Freq_ID+" = "+COLUMN_Activity_ID+
                " and "+ COLUMN_Freq_ID+ " = "+COLUMN_trigger_ID;

        Cursor recordSet = db.rawQuery(query, null);

        recordSet.moveToFirst();
        int count = recordSet.getCount();
        //Toast.makeText(START.appContext,Integer.toString(count),Toast.LENGTH_SHORT).show();
        if (count > 0)
        {
            dbString = new String[count][2];
            for (int j = 0; j < count; j++)
            {

                dbString[j][0] = recordSet.getString(recordSet.getColumnIndex(COLUMN_Activity));
                dbString[j][1] = recordSet.getString(recordSet.getColumnIndex(COLUMN_Activity_ID));
                recordSet.moveToNext();
            }
            db.close();
            recordSet.close();
            //Toast.makeText(START.appContext,dbString[2],Toast.LENGTH_SHORT).show();
            return dbString;
        }
        else
        {
            return nullString;
        }
    }


    public int delete(String id)
    {

        SQLiteDatabase db = getWritableDatabase();

        int flag = db.delete(TABLE_Activity,COLUMN_Activity_ID +"= ? " , new String []{id});
         flag = db.delete(TABLE_Frequency,COLUMN_Freq_ID +"= ? " , new String []{id});
         flag = db.delete(TABLE_Trig,COLUMN_trigger_ID +"= ? " , new String []{id});
        //db.execSQL(query);
        db.close();
        return flag;
    }
 /*
    public int deleteByID(Students student)
    {

        SQLiteDatabase db = getWritableDatabase();

        int flag = db.delete(TABLE_STUDENTS,COLUMN_ID +"= ?", new String []{Integer.toString(student.get_roll())});

        //db.execSQL(query);
        db.close();
        return flag;
    }
    public int deleteByName(Students student)
    {

        SQLiteDatabase db = getWritableDatabase();

        int flag = db.delete(TABLE_STUDENTS, COLUMN_NAME +"=?" , new String []{student.get_studentname()});

        db.close();
        return flag;
    }
*/
}

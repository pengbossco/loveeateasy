package com.example.bosscopeng.eat_myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Bossco.Peng on 2017/12/26.
 */

public class eat_db extends SQLiteOpenHelper{
    private static final String database="mydata.db";
    private static final int version=1;
    public eat_db(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    public eat_db(Context context){
        this(context,database,null,version);
    }
    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL("CREATE TABLE breakfastTable(_id integer primary key autoincrement," + "breakfast_name text no null," + "breakfast_description  text no null," + "breakfast_price real no null)");

        db.execSQL("CREATE TABLE lunchTable(_id integer primary key autoincrement," + "lunch_name text no null," + "lunch_description  text no null," + "lunch_price real no null)");

        db.execSQL("CREATE TABLE dinnerTable(_id integer primary key autoincrement,"+"dinner_name text no null,"+"dinner_description text no null,"+"dinner_price real no null)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS breakfastTable");
        db.execSQL("DROP TABLE IF EXISTS lunchTable");
        db.execSQL("DROP TABLE IF EXISTS dinnerTable");
        onCreate(db);
    }
}

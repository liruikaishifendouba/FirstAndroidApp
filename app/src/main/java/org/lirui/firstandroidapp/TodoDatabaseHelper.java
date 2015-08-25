package org.lirui.firstandroidapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String name = "todo";
    public TodoDatabaseHelper(Context context){
        super(context,name,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        String CREATE_TABLE_STUDENT="CREATE TABLE "+ Schedule.TABLE+"("
                +Schedule.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Schedule.KEY_TIME +" TIMESTAMP, "
                +Schedule.KEY_CONTENT +" TEXT, "
                +Schedule.KEY_IS_DONE +" INTEGER)";
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+ Schedule.TABLE);

        //再次创建表
        onCreate(db);
    }
}
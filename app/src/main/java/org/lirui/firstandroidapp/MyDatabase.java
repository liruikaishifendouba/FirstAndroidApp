package org.lirui.firstandroidapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int VERSION = 4;
    private static final String name = "plan";
    public MyDatabase(Context context){
        super(context,name,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        String CREATE_TABLE_STUDENT="CREATE TABLE "+ Week.TABLE+"("
                +Week.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Week.KEY_DAY_INDEX +" INTEGER, "
                +Week.KEY_DAY +" TEXT, "
                +Week.KEY_CONTENT +" TEXT)";
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+ Week.TABLE);

        //再次创建表
        onCreate(db);
    }
}





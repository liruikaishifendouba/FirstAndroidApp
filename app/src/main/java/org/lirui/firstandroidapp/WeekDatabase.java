package org.lirui.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class WeekDatabase {
    private MyDatabase myDatabase;
    public WeekDatabase(Context context){
        myDatabase= new MyDatabase(context);
    }
    public int insert(Week week){
        //打开连接，写入数据
        SQLiteDatabase db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Week.KEY_DAY,week.day);
        values.put(Week.KEY_DAY_INDEX,week.indexId);
        values.put(Week.KEY_CONTENT, week.content);
        //
        long week_Id=db.insert(Week.TABLE,null,values);
        db.close();
        return (int)week_Id;
    }

    public void delete(int week_Id){
        SQLiteDatabase db=myDatabase.getWritableDatabase();
        db.delete(Week.TABLE,Week.KEY_DAY_INDEX +"=?", new String[]{String.valueOf(week_Id)});
        db.close();
    }
    public void insertOrUpdate(Week week){
        SQLiteDatabase db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Week.KEY_DAY,week.day);
        values.put(Week.KEY_DAY_INDEX,week.indexId);

        values.put(Week.KEY_CONTENT, week.content);
        if(db.update(Week.TABLE, values, Week.KEY_DAY_INDEX + "=?", new String[]{String.valueOf(week.indexId)})<=0){
            insert(week);
            // update table set day="周六"，content="就是觉得" where KEY_ID=6;
        }
        db.close();
    }

    public ArrayList<Week> getWeekList(){
        SQLiteDatabase db=myDatabase.getReadableDatabase();
        String selectQuery="SELECT "+
                Week.KEY_ID+","+
                Week.KEY_DAY +","+
                Week.KEY_DAY_INDEX+","+
                Week.KEY_CONTENT +" FROM "+Week.TABLE;
        Cursor cursor=db.rawQuery(selectQuery,null);
        ArrayList<Week> weekList = new ArrayList<>();
        Week week;
        if(cursor.moveToFirst()){
            do{
                week = new Week();
                week.Id = cursor.getInt(cursor.getColumnIndex(Week.KEY_ID));
                week.indexId = cursor.getInt(cursor.getColumnIndex(Week.KEY_DAY_INDEX));
                week.day = cursor.getString(cursor.getColumnIndex(Week.KEY_DAY));
                week.content = cursor.getString(cursor.getColumnIndex(Week.KEY_CONTENT));
                weekList.add(week);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return weekList;
    }

    public Week getWeekById(int Id){
        SQLiteDatabase db=myDatabase.getReadableDatabase();
        String selectQuery="SELECT "+
                Week.KEY_ID+","+
                Week.KEY_DAY_INDEX +","+
                Week.KEY_DAY +","+
                Week.KEY_CONTENT +
                " FROM "+Week.TABLE+
                " WHERE " +
                Week.KEY_DAY_INDEX + "=?";
        int iCount=0;
        Week week=new Week();
        Cursor cursor=db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                week.Id=cursor.getInt(cursor.getColumnIndex(Week.KEY_ID));
                week.indexId =cursor.getInt(cursor.getColumnIndex(Week.KEY_DAY_INDEX));
                week.day =cursor.getString(cursor.getColumnIndex(Week.KEY_DAY));
                week.content =cursor.getString(cursor.getColumnIndex(Week.KEY_CONTENT));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return week;
    }

}

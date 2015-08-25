package org.lirui.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TodoDatabase {
    private TodoDatabaseHelper todoDatabaseHelper;
    public TodoDatabase(Context context){
        todoDatabaseHelper = new TodoDatabaseHelper(context);
    }
    public int insert(Schedule schedule){
        //打开连接，写入数据
        SQLiteDatabase db= todoDatabaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put(Schedule.KEY_ID,schedule.Id);
        values.put(Schedule.KEY_TIME,schedule.time);
        values.put(Schedule.KEY_CONTENT,schedule.content);
        values.put(Schedule.KEY_IS_DONE, schedule.is_done);
        long schedule_Id=db.insert(Schedule.TABLE,null,values);
        db.close();
        return (int)schedule_Id;
    }

    public void delete(int schedule_Id){
        SQLiteDatabase db= todoDatabaseHelper.getWritableDatabase();
        db.delete(Schedule.TABLE, Schedule.KEY_ID + "=?", new String[]{String.valueOf(schedule_Id)});
        db.close();
    }
    public int insertOrUpdate(Schedule schedule){
            SQLiteDatabase db= todoDatabaseHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
           // values.put(Schedule.KEY_ID,schedule.Id);
            values.put(Schedule.KEY_TIME,schedule.time);
            values.put(Schedule.KEY_CONTENT,schedule.content);
            values.put(Schedule.KEY_IS_DONE, schedule.is_done);

        if(db.update(Schedule.TABLE, values, Schedule.KEY_ID + "=?", new String[]{String.valueOf(schedule.Id)})<=0){
            insert(schedule);
            // update table set day="周六"，content="就是觉得" where KEY_ID=6;
        }
        db.close();
        return 1;
    }

    public ArrayList<Schedule> getScheduleList(){
        SQLiteDatabase db= todoDatabaseHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Schedule.KEY_ID+","+
                Schedule.KEY_TIME+","+
                Schedule.KEY_CONTENT+","+
                Schedule.KEY_IS_DONE +" FROM "+Schedule.TABLE;
        Cursor cursor=db.rawQuery(selectQuery,null);
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        Schedule schedule;
        if(cursor.moveToFirst()){
            do{
                schedule = new Schedule();
                schedule.Id = cursor.getInt(cursor.getColumnIndex(Schedule.KEY_ID));
                schedule.time= cursor.getLong(cursor.getColumnIndex(Schedule.KEY_TIME));
                schedule.content = cursor.getString(cursor.getColumnIndex(Schedule.KEY_CONTENT));
                schedule.is_done = cursor.getInt(cursor.getColumnIndex(Schedule.KEY_IS_DONE));
                scheduleList.add(schedule);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scheduleList;
    }

    public Schedule getScheduleById(int Id){
        SQLiteDatabase db= todoDatabaseHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Schedule.KEY_ID+","+
                Schedule.KEY_TIME +","+
                Schedule.KEY_CONTENT+","+
                Schedule.KEY_IS_DONE +" FROM "+Schedule.TABLE+
                " WHERE " +
                Schedule.KEY_ID + "=?";
        int iCount=0;
        Schedule schedule=new Schedule();
        Cursor cursor=db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                schedule.Id = cursor.getInt(cursor.getColumnIndex(Schedule.KEY_ID));
                schedule.time= cursor.getLong(cursor.getColumnIndex(Schedule.KEY_TIME));
                schedule.content = cursor.getString(cursor.getColumnIndex(Schedule.KEY_CONTENT));
                schedule.is_done = cursor.getInt(cursor.getColumnIndex(Schedule.KEY_IS_DONE));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return schedule;
    }

}

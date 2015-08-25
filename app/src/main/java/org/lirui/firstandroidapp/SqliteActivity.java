package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SqliteActivity extends Activity {
    private Button createDatebase;
    private Button updateDatebase;
    private Button insert;
    private Button update;
    private Button query;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
        createDatebase=(Button)findViewById(R.id.createDatebase);
        updateDatebase=(Button)findViewById(R.id.updateDatebase);
        insert=(Button)findViewById(R.id.insert);
        update=(Button)findViewById(R.id.update);
        query=(Button)findViewById(R.id.query);
        createDatebase.setOnClickListener(new CreateListener());
        updateDatebase.setOnClickListener(new UpdateListener());
        insert.setOnClickListener(new InsertListener());
        update.setOnClickListener(new UpdateRecordListener());
        query.setOnClickListener(new QueryListener());
    }
    class CreateListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,"test_db");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }
    class UpdateListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,"test_db",2);
            SQLiteDatabase db =dbHelper.getReadableDatabase();
        }
    }
    class InsertListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ContentValues values = new ContentValues();
            values.put("id",1);
            values.put("name","zhangsan");
            DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,"test_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert("user",null,values);
        }
    }
    class UpdateRecordListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ContentValues values =new ContentValues();
            values.put("name", "zhangsshang");
            DatabaseHelper dbHelper= new DatabaseHelper(SqliteActivity.this,"test_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.update("user", values, "id=?", new String[]{"1"});
        }
    }
    class QueryListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(SqliteActivity.this,"test_db");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"},null,null,null,null);
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                System.out.println("query--->"+id+name);
            }
        }

    }
}

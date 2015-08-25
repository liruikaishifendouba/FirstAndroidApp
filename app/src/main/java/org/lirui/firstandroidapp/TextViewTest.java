package org.lirui.firstandroidapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class TextViewTest extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map1 = new HashMap<String,String>();
        HashMap<String,String> map2 = new HashMap<String,String>();
        HashMap<String,String> map3 = new HashMap<String,String>();
        map1.put("user_name","zhangsan");
        map1.put("user_ip","192.168.0.1");
        map2.put("user_name","lisi");
        map2.put("user_ip","192.168.0.2");
        map3.put("user_name","wangwu");
        map3.put("user_ip", "192.168.0.3");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        SimpleAdapter listAdapter  = new SimpleAdapter(this,list,
                R.layout.user,new String[]{"user_name","user_ip"},
                new int[]{R.id.user_name,R.id.user_ip});
        setListAdapter(listAdapter);
    }
    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l,v,position,id);
        System.out.println("id-----------------------"+id);
        System.out.println("position-----------------"+position);

    }


}
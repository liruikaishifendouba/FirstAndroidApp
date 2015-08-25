package org.lirui.firstandroidapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Todo extends Activity {
    private ListView lv;
    private Button add;
    private SimpleDateFormat mytime;
    private MyAdapter adapter;
    private MyHandler handler;
    private  ArrayList<Schedule> datas;
    private BroadcastReceiver myReceiver;
    private Notification notification ;
    // 声明NotificationManager
    private NotificationManager mNotification;
    // Notification标示ID
    private static final int ID = 1;
    private static final String TODO_ACTION = "org.lirui.firstandroidapp.TODO_ACTION";
    public static final String TodoService_ACTION = "org.lirui.firstandroidapp.TodoService";
    TodoDatabase db = new TodoDatabase(Todo.this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo);
        bindService(new Intent(TodoService.ACTTION), conn, BIND_AUTO_CREATE);
        init();
        Intent result = getIntent();
        String key = result.getStringExtra("key");
        if(key!=null){
            runNotification();
        }
        myReceiver = new BroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(TODO_ACTION);
        registerReceiver(myReceiver, filter);
    }
    private void init(){
        add = (Button) findViewById(R.id.add);
        lv = (ListView) findViewById(R.id.lv);
        add.setOnClickListener(new AddListener());
        lv.setOnItemClickListener(new ListViewListener());
        lv.setOnItemLongClickListener(new delete());
        handler = new MyHandler();
        getTodoList();

    }
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println( "onServiceDisconnected");
        }
    };
    @Override
    public  void onDestroy(){
        System.out.println("------执行了---------onDestroy");

        unbindService(conn);
        super.onDestroy();
    }
    public void sendBroadcast()
    {
        Intent intent = new Intent();
        intent.setAction(TodoService_ACTION);
        sendBroadcast(intent);}
    public void runNotification() {
            mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

            notification = new Notification();
            int icon = notification.icon = android.R.drawable.stat_notify_chat;
            String tickerText = "Test Notification";
            long when = System.currentTimeMillis();
            notification.icon = icon;
            notification.tickerText = tickerText;
            notification.when = when;

            // 实例化Intent
        Intent intent = new Intent();
        intent.setAction(TodoService_ACTION);
        sendBroadcast(intent);
            // 获得PendingIntent
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
            // 设置事件信息
            notification.setLatestEventInfo(this, "消息", "点击停止", pi);
            // 发出通知
            mNotification.notify(ID, notification);
    }

    public class TodoServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

            notification = new Notification();
            int icon = notification.icon = android.R.drawable.stat_notify_chat;
            String tickerText = "Test Notification";
            long when = System.currentTimeMillis();
            notification.icon = icon;
            notification.tickerText = tickerText;
            notification.when = when;

            // 实例化Intent
            Intent i = new Intent();
            intent.setAction(TodoService_ACTION);
            sendBroadcast(i);
            // 获得PendingIntent
            PendingIntent pi = PendingIntent.getBroadcast(Todo.this, 0, intent, 0);
            // 设置事件信息
            notification.setLatestEventInfo(Todo.this, "消息", "点击停止", pi);
            // 发出通知
            mNotification.notify(ID, notification);

        }


    }
    class AddListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("key", "1");
            intent.setClass(Todo.this, TodoEditActivity.class);
            startActivityForResult(intent, 1);

        }
    }

    private void getTodoList() {
        new Thread() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_LOADING);

                datas = db.getScheduleList();
                if (datas != null && datas.size() > 0) {
                    Message msg = handler.obtainMessage();
                    msg.what = MSG_LOAD_SUCCESS;
                    msg.obj = datas;
                    handler.sendMessage(msg);
                } else {
                    handler.sendEmptyMessage(MSG_LOAD_EMPTY);
                }

            }
        }.start();
    }
    class ListViewListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
            Intent intent = new Intent();
            intent.putExtra("content", datas.get(position).content);
            intent.putExtra("time", datas.get(position).time);
            intent.putExtra("finish", datas.get(position).is_done);
            intent.putExtra("id", datas.get(position).Id);
            intent.setClass(Todo.this, TodoEditActivity.class);
            startActivityForResult(intent, 2);
        }
    }
    private void dialog(final int position)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Todo.this);
        builder.setTitle("提示");
        builder.setMessage("是否确认删除?");
        builder.setIcon(R.mipmap.ic_launcher);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                TodoDatabase db = new TodoDatabase(Todo.this);
                db.delete(adapter.getItem(position).Id);
                adapter.dataList.remove(position);
                Message msg = handler.obtainMessage();
                msg.what = DELETE;
//                        msg.arg1 = position;
                handler.sendMessage(msg);
                System.out.println("----------打印position-------------------"+position+"-------------");

                //Toast.makeText(Todo.this, "确认" , Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                //Toast.makeText(Todo.this, "取消" , Toast.LENGTH_SHORT).show();

            }
        });


        builder.create().show();
    }
    class delete implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView < ? > parent, View view, final int position, long id) {
            dialog(position);

            return true;

        }
    }

    public static final int MSG_LOADING = 1;
    public static final int MSG_LOAD_SUCCESS = 2;
    public static final int MSG_LOAD_EMPTY = 3;
    public static final int UPDATE = 4;
    public static final int DELETE = 5;


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LOADING:
                    Toast.makeText(Todo.this, "正在读取数据...", Toast.LENGTH_SHORT).show();
                    break;
                case MSG_LOAD_SUCCESS:
                    ArrayList<Schedule> datas = (ArrayList<Schedule>) msg.obj;
                    if (adapter == null) {
                        adapter = new MyAdapter(Todo.this);
                        adapter.setData(datas);
                        lv.setAdapter(adapter);
                    } else {
                        // 第二次调用getTodoList方法时，adapter已经不为null了，重新设置新的数据源，然后通知listView刷新，用adapter.notifyDataSetChanged()方法
                        adapter.setData(datas);
                        adapter.notifyDataSetChanged();
                    }
                    Toast.makeText(Todo.this, "数据载入成功...", Toast.LENGTH_SHORT).show();
                    break;
                case MSG_LOAD_EMPTY:
                    // 数据库没有查到数据，提示用户
                    Toast.makeText(Todo.this, "没有todo数据", Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE:
                    adapter.notifyDataSetChanged();
                case DELETE:
                    adapter.notifyDataSetChanged();
            }
        }
    }

    class Holder {
        TextView content;
        TextView time;
        CheckBox finish;
    }






    public class MyAdapter extends BaseAdapter {
        private ArrayList<Schedule> dataList = new ArrayList<Schedule>();
        private Holder holder;
        private LayoutInflater inflater;

        private MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }
        public void setData(ArrayList<Schedule> dataList) {
            this.dataList.clear();
            this.dataList.addAll(dataList);
        }
        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Schedule getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new Holder();
                convertView = inflater.inflate(R.layout.todo_item, null);
                holder.content = (TextView) convertView.findViewById(R.id.content);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.finish = (CheckBox) convertView.findViewById(R.id.finish);
                convertView.setTag(holder);

            } else {
                holder = (Holder) convertView.getTag();
            }
            mytime = new SimpleDateFormat("MM/dd HH:mm");

            final Schedule schedule  = dataList.get(position);
            holder.content.setText(schedule.content);
            holder.time.setText(mytime.format((schedule.time)));

            if (schedule.is_done == 1)

            {
                holder.finish.setChecked(true);
            } else

            {
                holder.finish.setChecked(false);

            }
            holder.finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            if (schedule.is_done == 1) {
                                schedule.is_done =0;
                                TodoDatabase db = new TodoDatabase(Todo.this);
                                db.insertOrUpdate(schedule);
                                if (db.insertOrUpdate(schedule) == 1) {
                                    Message msg = handler.obtainMessage();
                                    msg.what = UPDATE;
                                    handler.sendMessage(msg);
                                    //System.out.println("------更新成功，ischecked是" + "---------true------");
                                } else {
                                    schedule.is_done = 1;
                                    //System.out.println("------更新失败，ischecked是" + "---------true------");
                                }
                            }else {
                                schedule.is_done =1;
                                TodoDatabase db = new TodoDatabase(Todo.this);
                                db.insertOrUpdate(schedule);
                                if (db.insertOrUpdate(schedule) == 1) {
                                    Message msg = handler.obtainMessage();
                                    msg.what = UPDATE;
                                    handler.sendMessage(msg);
                                    //System.out.println("------更新成功，ischecked是" + "---------true------");
                                } else {
                                    schedule.is_done = 0;
                                    //System.out.println("------更新失败，ischecked是" + "---------true------");
                                }
                            }
                        }
                        }.start();

                    }

            });


            return convertView;
        }

    }

         @Override
         protected void onActivityResult(int requestCode, int resultCode, Intent data) {

             super.onActivityResult(requestCode, resultCode, data);
             switch (requestCode) {
                 case 1:
                     if (resultCode == RESULT_OK) {
                         new Thread() {
                             @Override
                             public void run() {
                                 TodoDatabase db = new TodoDatabase(Todo.this);
                                 datas = db.getScheduleList();
                                 Message msg = handler.obtainMessage();
                                 msg.what = MSG_LOAD_SUCCESS;
                                 msg.obj = datas;
                                 handler.sendMessage(msg);
                             }
                         }.start();
                     }
                     break;
                 case 2:
                     if (resultCode == RESULT_OK) {
                         new Thread() {
                             @Override
                             public void run() {
                                 TodoDatabase db = new TodoDatabase(Todo.this);
                                 datas = db.getScheduleList();
                                 Message msg = handler.obtainMessage();
                                 msg.what = MSG_LOAD_SUCCESS;
                                 msg.obj = datas;
                                 handler.sendMessage(msg);
                             }
                         }.start();
                     }
                     break;
             }

         }

}
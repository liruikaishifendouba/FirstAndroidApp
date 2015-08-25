//package org.lirui.firstandroidapp;
//
//
//        import android.app.Activity;
//        import android.content.Context;
//        import android.os.Bundle;
//        import android.os.Handler;
//        import android.os.Message;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.AdapterView;
//        import android.widget.BaseAdapter;
//        import android.widget.CheckBox;
//        import android.widget.CompoundButton;
//        import android.widget.ListView;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        //import org.sojex.finance.R;
//
//        import java.util.ArrayList;
//
//
///**
// * Created by hehuajia on 15/8/19.
// */
//public class TodoActivity extends Activity implements AdapterView.OnItemClickListener{
//    private ListView  listView;
//    private MyHandler handler;
//    private MyAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.todo);
//        init();
//    }
//
//    /** 初始化操作，包含三部分：1、控件初始化。2、工具等类初始化。3、数据初始化*/
//    private void init() {
//        // 控件初始化，绑定监听事件
//        listView = (ListView) findViewById(R.id.lv);
//        listView.setOnItemClickListener(this);
//
//        // 工具类初始化，看具体情况而定，当前类需要用到的类，如果类全局需要用，就在这初始化，如果只是某个方法需要用，在那方法里声明初始化就好
//        handler = new MyHandler();
//
//        // 数据初始化
//        getTodoList();
//    }
//
//    /** 数据初始化，开线程，异步读数据库*/
//    private void getTodoList() {
//        // 开启子线程读数据库里的数据，匿名内部类写法，只用一次，可以不声明Thread类引用，直接用匿名内部类的方式
//        new Thread(){
//            @Override
//            public void run() {
//                // 线程执行第一步，发一个handler，通知UI线程，显示一个loading对话框，告诉用户在做耗时操作，数据不会那么快展现出来
//                // handler.sendEmptyMessage(what)  这个方法意义是：发一个只有msg.what的消息，没有使用msg.arg1, msg.arg2, msg.obj
//                handler.sendEmptyMessage(MSG_LOADING);
//                // 查询数据库操作，数据多，耗时会比较长，
//                TodoDatabase dataBase = new TodoDatabase(TodoActivity.this);
//                ArrayList<Schedule> datas = dataBase.getScheduleList();
//                // 判断是否查到了数据
//                if (datas != null && datas.size() > 0) {
//                    // 查到数据了，通过handler把消息发送给UI线程，数据用msg.obj带出去，这里是用handler的主要目的，因为Android规定子线程不能直接操作UI，只有UI线程（主线程）可以操作UI
//                    // 所以要用主线程与子线程交互的桥梁----handler，把消息带出去，这个handler在init()方法中初始化的，init()在onCreate中调用的，所以这个handler，就是主线程的handler
//                    Message msg = handler.obtainMessage();// handler.obtainMessage()方法可以获得一个Message，不要new Message(), obtainMessage会从消息池拿用过的消息，节省开销
//                    msg.what = MSG_LOAD_SUCCESS;
//                    msg.obj = datas;
//                    handler.sendMessage(msg);
//                } else {
//                    // 没有查到数据，handler发消息告诉UI，提示用户没有数据，怎么提示看怎么产品设计，可以直接弹Toast提示，也可以背景显示一张图，告诉用户没有todo数据
//                    handler.sendEmptyMessage(MSG_LOAD_EMPTY);
//                }
//            }
//        }.start();
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        // listView item点击事件
//
//    }
//
//    // 消息的what，标示什么消息，一下常量顾名思义
//    public static final int MSG_LOADING = 1;
//    public static final int MSG_LOAD_SUCCESS = 2;
//    public static final int MSG_LOAD_EMPTY = 3;
//    class MyHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            // 根据msg的what，来判断发的是哪一条message
//            switch (msg.what) {
//                case MSG_LOADING:
//                    // 子线程第一句代码发送的消息，告诉用户，开始读数据库了，这里可以用对话框显示正在读取
//                    // 简单起见，也可以暂时Toast提示
//                    Toast.makeText(TodoActivity.this, "正在读取数据...", Toast.LENGTH_SHORT).show();
//                    break;
//                case MSG_LOAD_SUCCESS:
//                    // 数据库查到todo数据，通过msg.obj拿到数据源
//                    ArrayList<Schedule> datas = (ArrayList<Schedule>) msg.obj;
//                    if (adapter == null) {
//                        // 刚进activity，第一次执行到这，adapter肯定为null，所以这里构造一个MyAdapter，让listView绑定Adapter
//                        adapter = new MyAdapter(TodoActivity.this);
//                        adapter.setData(datas);
//                        listView.setAdapter(adapter);
//                    } else {
//                        // 第二次调用getTodoList方法时，adapter已经不为null了，重新设置新的数据源，然后通知listView刷新，用adapter.notifyDataSetChanged()方法
//                        adapter.setData(datas);
//                        adapter.notifyDataSetChanged();
//                    }
//                    break;
//                case MSG_LOAD_EMPTY:
//                    // 数据库没有查到数据，提示用户
//                    Toast.makeText(TodoActivity.this, "没有todo数据", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    }
//
//
//    class MyAdapter extends BaseAdapter {
//
//        private ArrayList<Schedule> dataList = new ArrayList<Schedule>();
//        private Holder holder;
//        private LayoutInflater inflater;
//
//        public MyAdapter(Context context) {
//            inflater = LayoutInflater.from(context);
//        }
//        /** 给Adapter设置数据源，这里不要用赋值的方式，赋值是引用传递，导致Adapter中的数据源与activity中的数据源是同一份，这里不明白另外问我，主要为了规避listview一个容易出现的bug*/
//        public void setData(ArrayList<Schedule> dataList) {
//            this.dataList.clear();
//            this.dataList.addAll(dataList);
//        }
//
//        @Override
//        public int getCount() {
//            return dataList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return dataList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.adapter_todo_item);
//                holder = new Holder();
//                holder.tv_content = (TextView) convertView.findViewById(R.id.textView_content);
//                // 其他控件初始化，我就不写了
//
//                convertView.setTag(holder);
//            } else {
//                holder = (Holder) convertView.getTag();
//            }
//            final Schedule schedule = dataList.get(position);
//            // 设置控件显示的东西，我就不写了
//
//            holder.cb_isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        schedule.isDone = 1;
//                    } else {
//                        schedule.isDone = 0;
//                    }
//                    // 这里要更新数据库，也是异步操作，封装一个方法，方法里启动子线程，去更新数据库（与查询部分类似），更新完了，用handler发消息到UI线程，
//                    // 然后刷新ListView（此时adapter中数据源的目标schedule已变，所以直接adapter.notifyDataSetChanged即可），
//                    // 如果更新失败，得恢复之前的状态，比如之前isDone = 1，点击后，isDone变为0，更新失败的话，isDone还是要变回1，checkbox还是要变回选中状态
//
//                }
//            });
//            return convertView;
//        }
//    }
//
//    class Holder {
//        TextView tv_content;
//        TextView tv_time;
//        CheckBox cb_isDone;
//    }
//
//
//}

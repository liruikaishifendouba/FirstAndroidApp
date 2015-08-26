
package org.lirui.firstandroidapp;
        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.app.TimePickerDialog;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.CompoundButton.OnCheckedChangeListener;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import java.util.Calendar;

public class TodoEditActivity extends Activity {
    private TextView textView;
    private EditText et1;
    protected Button cancel;
    protected Button finish1;
    protected Button back;
    protected Button button;
    private String string;
    public Schedule schedule = new Schedule();

    private Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todoedit);
        et1 = (EditText)findViewById(R.id.et1);
        textView = (TextView)findViewById(R.id.textView1);
        CheckBox finish = (CheckBox) findViewById(R.id.finish);
        cancel = (Button) findViewById(R.id.cancel);
        finish1 = (Button) findViewById(R.id.finish1);
        back = (Button) findViewById(R.id.back);
        button = (Button)findViewById(R.id.bt_time);
        TodoDatabase todo = new TodoDatabase(this);
        schedule = todo.getScheduleById(schedule.Id);

        Intent intent = getIntent();
        String content1=intent.getStringExtra("content");
        long time1 = intent.getLongExtra("time",0);
        int finish2 = intent.getIntExtra("finish",0);
        schedule.Id = intent.getIntExtra("id",0);
        if(content1!=null){
            schedule.content = content1;
            schedule.is_done = finish2;
            schedule.time =time1;
        }
        et1.setText(schedule.content);
        calendar.setTimeInMillis(schedule.time);
        String time_result =(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH )+ 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
        textView.setText("您设置的时间为:" + time_result);
        if(schedule.is_done==1){
            finish.setChecked(true);
        }else {
            finish.setChecked(false);
        }
        finish1.setOnClickListener(new finish1Listener());
        cancel.setOnClickListener(new cancelListener());
        back.setOnClickListener(new backListener());
        finish.setOnCheckedChangeListener(new finishListener(schedule));
        button.setOnClickListener(new buttonListener());

    }

    class buttonListener implements View.OnClickListener{

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            Dialog dateDialog=new DatePickerDialog(TodoEditActivity.this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    string=(arg1 + "-" + (arg2 + 1) + "-" + arg3 + " ");
                    calendar.set(arg1,arg2,arg3);
                    System.out.println("--------------------回调onDateSet----------");
                }


            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dateDialog.setTitle("请选择日期");
            dateDialog.show();
            dateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    Dialog timeDialog = new TimePickerDialog(TodoEditActivity.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // TODO Auto-generated method stub
                            System.out.println("--------------------回调onTimeSet----------");
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calendar.set(Calendar.MINUTE, minute);
                            textView.setText("您设置的时间为:" + string + hourOfDay + ":" + minute);
                            schedule.time = calendar.getTimeInMillis();
                            Intent intent = new Intent();
                            intent.setAction("org.lirui.firstandroidapp.AlarmReceiver");
                            PendingIntent pi = PendingIntent.getBroadcast(TodoEditActivity.this, 0, intent, 0);
//设置一个PendingIntent对象，发送广播
                            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//获取AlarmManager对象
                            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    System.out.println("-------------这个是calendar------------" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
                    timeDialog.setTitle("请选择时间");
                    timeDialog.show();

                }
            });


        }
    }
//点击完成
    class finish1Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TodoDatabase tdb = new TodoDatabase(TodoEditActivity.this);
            schedule.content= et1.getText().toString();
            //给content赋值后，判断值是否为空，为空跳toast，不为空就插入或更新数据库
            if(schedule.content.equals("")){
                Toast.makeText(TodoEditActivity.this, "请添加数据...", Toast.LENGTH_SHORT).show();
            }else {


                tdb.insertOrUpdate(schedule);
                Intent intent = new Intent();
                String result = intent.getStringExtra("key");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", result);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        }
    }
//设置Checkbox监听器
     class finishListener implements OnCheckedChangeListener{
         Schedule schedule1 = new Schedule();
         finishListener(Schedule schdule){
             schedule1 = schdule;
         }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                schedule1.is_done=1;

            }else {schedule1.is_done=0;
            }
        }
    }
//返回按钮和退出按钮
    class cancelListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            finish();
        }
    }class backListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            finish();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("-----------------进行了stop---------");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("------------------进行了destroy--------");
    }

}

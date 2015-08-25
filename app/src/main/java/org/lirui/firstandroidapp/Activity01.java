package org.lirui.firstandroidapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity01 extends Activity {
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textView;
    private EditText editText;
    private String tian1;
    private int tianint =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity01);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);

        final Intent intent = getIntent();
        final String tian = intent.getStringExtra("day");
        tianint = 0;
        //final MyPreference myPre =MyPreference.getInstance(this);
        final WeekDatabase wdb = new WeekDatabase(this);
        Week week = new Week();

        tianint=Integer.parseInt(tian);

        week = wdb.getWeekById(tianint);
        textView.setText(week.day);
        editText.setText(week.content);
        String content = week.content;
/*
        if (!TextUtils.isEmpty(content)) {
            editText.setText(content);
        }
  */
        switch (tianint) {
            case 1:
              //
                textView.setText("周一");

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     //   if (editText.getText().toString()==null){
                        textView.setText("周一");
                        WeekDatabase wdb = new WeekDatabase(Activity01.this);
                        Week week = new Week();
                        week.day=textView.getText().toString();
                        week.content= editText.getText().toString();
                        week.indexId = tianint;
                        wdb.insert(week);
                    //}
                       // else{
                        //    WeekDatabase wdb = new WeekDatabase(Activity01.this);
                       //     Week week = new Week();
                     //       week.day=textView.getText().toString();
                     //       week.content= editText.getText().toString();
                     //       week.indexId = tianint;
                     //       wdb.update(week);
                      //  }
                        finish();

                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", "已完成");
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case 2:
                textView.setText("周二");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //if (editText.getText().toString()==""){
                            textView.setText("周二");
                            WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            Week week = new Week();
                            week.day=textView.getText().toString();
                            week.content= editText.getText().toString();
                            week.indexId = tianint;
                            wdb.insert(week);
                        //    }else {
                          //  WeekDatabase wdb = new WeekDatabase(Activity01.this);
                          //  Week week = new Week();
                          //  week.day=textView.getText().toString();
                          //  week.content= editText.getText().toString();
                          //  week.indexId = tianint;
                          //  wdb.update(week);
                          //  }

                        finish();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", "已完成");
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case 3:
                textView.setText("周三");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //textView.setText("周三");
                        //if (editText.getText().toString()==null){
                            WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            Week week = new Week();
                            week.day=textView.getText().toString();
                            week.content= editText.getText().toString();
                            week.indexId = tianint;
                            wdb.insert(week);
                    //}
                        //else {
                          //  WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            //Week week = new Week();
                            //week.day=textView.getText().toString();
                            //week.content= editText.getText().toString();
                            //week.indexId = tianint;
                            //wdb.update(week);
                        //}
                        finish();
                        }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", "已完成");
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case 4:
                textView.setText("周四");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("周四");
                      //  if (editText.getText().toString()==null){
                            WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            Week week = new Week();
                            week.day=textView.getText().toString();
                            week.content= editText.getText().toString();
                            week.indexId = tianint;
                            wdb.insert(week);
                        //}else {
                        //    WeekDatabase wdb = new WeekDatabase(Activity01.this);
                          //  Week week = new Week();
                           // week.day=textView.getText().toString();
                            //week.content= editText.getText().toString();
                            //week.indexId = tianint;
                            //wdb.update(week);
                        //}
                        finish();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", "已完成");
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case 5:
                textView.setText("周五");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("周五");
                     //   if (editText.getText().toString()==null){
                            WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            Week week = new Week();
                            week.day=textView.getText().toString();
                            week.content= editText.getText().toString();
                            week.indexId = tianint;
                            wdb.insert(week);
                       // }else {
                         //   WeekDatabase wdb = new WeekDatabase(Activity01.this);
                           // Week week = new Week();
                            //week.day=textView.getText().toString();
                            //week.content= editText.getText().toString();
                            //week.indexId = tianint;
                            //wdb.update(week);
                       // }
                        finish();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", "已完成");
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case 6:
                textView.setText("周六");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("周六");
                      //  if (editText.getText().toString()==null){
                            WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            Week week = new Week();
                            week.day=textView.getText().toString();
                            week.content= editText.getText().toString();
                            week.indexId = tianint;
                            wdb.insertOrUpdate(week);

                            //WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            //Week week = new Week();
                            //week.day=textView.getText().toString();
                            //week.content= editText.getText().toString();
                            //week.indexId = tianint;
                            //wdb.update(week);
                       // }
                        finish();

                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", "已完成");
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case 7:
                textView.setText("周日");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("周日");
                        //if (editText.getText().toString()==null){

                            WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            Week week = new Week();
                            week.day=textView.getText().toString();
                            week.content= editText.getText().toString();
                            week.indexId = tianint;
                            wdb.insert(week);

                        //}else {
                          //  WeekDatabase wdb = new WeekDatabase(Activity01.this);
                            //Week week = new Week();
                            //week.day=textView.getText().toString();
                            //week.content= editText.getText().toString();
                            //week.indexId = tianint;
                            //wdb.update(week);
                       // }
                        finish();

                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", "已完成");
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();
                    }
                });
                break;
        }
    }
}

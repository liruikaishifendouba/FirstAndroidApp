package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity00 extends Activity{
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private TextView textView1;
    private TextView textView2;
    private EditText editText01;
    private EditText editText02;
    private EditText editText03;
    private EditText editText04;
    private EditText editText05;
    private EditText editText06;
    private EditText editText07;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity00);
        textView1 = (TextView)findViewById(R.id.textView1);
        button = (Button)findViewById(R.id.button);
        button.setText("退出");
        button1 = (Button)findViewById(R.id.button1);
        button1.setText("周一");
        button2 = (Button)findViewById(R.id.button2);
        button2.setText("周二");
        button3 = (Button)findViewById(R.id.button3);
        button3.setText("周三");
        button4 = (Button)findViewById(R.id.button4);
        button4.setText("周四");
        button5 = (Button)findViewById(R.id.button5);
        button5.setText("周五");
        button6 = (Button)findViewById(R.id.button6);
        button6.setText("周六");
        button7 = (Button)findViewById(R.id.button7);
        button7.setText("周日");
        editText01 = (EditText)findViewById(R.id.editText01);
        editText02 = (EditText)findViewById(R.id.editText02);
        editText03 = (EditText)findViewById(R.id.editText03);
        editText04 = (EditText)findViewById(R.id.editText04);
        editText05 = (EditText)findViewById(R.id.editText05);
        editText06 = (EditText)findViewById(R.id.editText06);
        editText07 = (EditText)findViewById(R.id.editText07);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // testCallBack();
            }
        });

        button1.setOnClickListener(new setOnClickListener1());
        button2.setOnClickListener(new setOnClickListener2());
        button3.setOnClickListener(new setOnClickListener3());
        button4.setOnClickListener(new setOnClickListener4());
        button5.setOnClickListener(new setOnClickListener5());
        button6.setOnClickListener(new setOnClickListener6());
        button7.setOnClickListener(new setOnClickListener7());

    }
    class setOnClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Activity00.this, Activity01.class);
            intent.putExtra("day", "1");
            startActivityForResult(intent, 1);


        }

    }
    class setOnClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Activity00.this, Activity01.class);
            intent.putExtra("day", "2");
            startActivityForResult(intent, 2);


        }

    }
    class setOnClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Activity00.this, Activity01.class);
            intent.putExtra("day", "3");
            startActivityForResult(intent, 3);


        }

    }
    class setOnClickListener4 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Activity00.this, Activity01.class);
            intent.putExtra("day", "4");
            startActivityForResult(intent, 4);


        }

    }
    class setOnClickListener5 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Activity00.this, Activity01.class);
            intent.putExtra("day", "5");
            startActivityForResult(intent, 5);


        }

    }
    class setOnClickListener6 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Activity00.this, Activity01.class);
            intent.putExtra("day", "6");
            startActivityForResult(intent, 6);


        }

    }
    class setOnClickListener7 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Activity00.this, Activity01.class);
            intent.putExtra("day", "7");
            startActivityForResult(intent, 7);


        }

    }
    /*
   private void testCallBack(){
        Download call =new Download();
        call.setCallfuc(new CallListen());
       call.call("lirui");

    }
    class CallListen implements DownloadCallback{
        @Override
        public void call(boolean isSuccess){
            System.out.println("call has:"+isSuccess);
        }
    }
/*
    private void testCallBack() {
        Download download = new Download();
        DownloadListener listener = new DownloadListener();
        download.setDownloadCallback(listener);
        download.downloadFile("http://www.baidu.com/usa.jpg");
//        System.out.println("Lirui DownLoad complete: " + isSuccess);

    }

    class DownloadListener implements DownloadCallback {
        @Override
        public void onDownloadComplete(boolean isSuccess) {
            System.out.println("Lirui DownLoad complete: " + isSuccess);
        }

    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String result1 = data.getStringExtra("result");
                    editText01.setText(result1);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    String result2 = data.getStringExtra("result");
                    editText02.setText(result2);
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    String result3 = data.getStringExtra("result");
                    editText03.setText(result3);
                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    String result4 = data.getStringExtra("result");
                    editText04.setText(result4);
                }
                break;
            case 5:
                if (resultCode == RESULT_OK) {
                    String result5 = data.getStringExtra("result");
                    editText05.setText(result5);
                }
                break;
            case 6:
                if (resultCode == RESULT_OK) {
                    String result6 = data.getStringExtra("result");
                    editText06.setText(result6);
                }
                break;
            case 7:
                if (resultCode == RESULT_OK) {
                    String result7 = data.getStringExtra("result");
                    editText07.setText(result7);
                }
                break;

        }
    }

}

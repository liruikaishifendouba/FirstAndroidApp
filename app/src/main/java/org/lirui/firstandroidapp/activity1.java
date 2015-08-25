package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity1 extends Activity {
    private EditText factorOne;
    private EditText factorTwo;
    private TextView symbol;
    private Button calculate;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        factorOne=(EditText)findViewById(R.id.factorOne);
        factorTwo=(EditText)findViewById(R.id.factorTwo);
        symbol=(TextView)findViewById(R.id.symbol);
        calculate=(Button)findViewById(R.id.calculate);
        symbol.setText(R.string.symbol);
        result=(TextView)findViewById(R.id.result);
        calculate.setText(R.string.calculate);

        calculate.setOnClickListener(new CalculateListener());


        /*
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String factorOneStr = factorOne.getText().toString();
                String factorTwoStr = factorTwo.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("one",factorOneStr);
                intent.putExtra("two",factorTwoStr);
                intent.setClass(activity1.this,resultactivity.class);
                activity1.this.startActivity(intent);


            }
        });
        */


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, 1, 1, R.string.exit);
        menu.add(0, 2, 2, R.string.about);


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==1){
            finish();
        }
        else {

        }
        return super.onOptionsItemSelected(item);
    }
    class CalculateListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            String factorOneStr = factorOne.getText().toString();
            String factorTwoStr = factorTwo.getText().toString();
            Intent intent = new Intent();

            intent.putExtra("one",factorOneStr);
            intent.putExtra("two",factorTwoStr);
            intent.setClass(activity1.this, resultactivity.class);
            activity1.this.startActivityForResult(intent,2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    int result1 = data.getIntExtra("result", -1);
                    result.setText(result1+"");
                }
                break;
            case 3:

                break;
        }
    }
}
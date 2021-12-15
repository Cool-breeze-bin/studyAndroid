package com.fbb.serviceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_ctxt,btn_cbgc;
    private TextView textView;

    public static final int UPDATE_TEXT = 1;
    public static final int UPDATE_BGC = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        btn_ctxt = findViewById(R.id.change_text);
        btn_ctxt.setOnClickListener(this);

        btn_cbgc = findViewById(R.id.change_bgc);
        btn_cbgc.setOnClickListener(this);

        textView = findViewById(R.id.textview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.change_bgc:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_BGC;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("房彬彬41905225");
                    break;
                case UPDATE_BGC:
                    textView.setBackground(getDrawable(R.drawable.app_bg));
                    break;
                default:
                    break;
            }
            return false;
        }
    });
}
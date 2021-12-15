package com.fbb.serviceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_ctxt,btn_cbgc;
    private TextView textView;
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
                        textView.setText("房彬彬切换了");
                    }
                }).start();
                break;
            case R.id.change_bgc:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setBackground(getDrawable(R.drawable.app_bg));
                    }
                }).start();
                break;
        }
    }
}
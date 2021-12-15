package com.fbb.serviceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_bind,btn_unbind,btn_call;
    private MyService.DownloadBinder downloadBinder;
    private MyConnection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btn_bind = findViewById(R.id.bind_service);
        btn_bind.setOnClickListener(this);

        btn_call = findViewById(R.id.call_methods);
        btn_call.setOnClickListener(this);

        btn_unbind = findViewById(R.id.unbind_service);
        btn_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind_service:
                if(connection == null){
                    connection = new MyConnection();
                }
                Intent intent = new Intent(ServiceActivity.this,MyService.class);
                intent.putExtra("step",2);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.call_methods:
                downloadBinder.startDownload();
                int data = downloadBinder.getProgress();
                Log.d("MyService","MyService中反馈的数据:"+data);
                break;
            case R.id.unbind_service:
                if(connection != null){
                    unbindService(connection);
                    connection = null;
                }
                break;
        }
    }

    public class MyConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
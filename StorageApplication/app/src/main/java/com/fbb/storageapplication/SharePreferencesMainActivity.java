package com.fbb.storageapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;

public class SharePreferencesMainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_username,edit_password;
    private Button btn_login,btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);

        edit_username = findViewById(R.id.username);
        edit_password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                String username_rgs = edit_username.getText().toString();
                String password_rgs = edit_password.getText().toString();

                SharedPreferences preferences1 = getSharedPreferences("userInfo",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();

                editor.putStringSet("username", Collections.singleton(username_rgs));
                editor.putStringSet("password", Collections.singleton(password_rgs));
                editor.apply();
                Toast.makeText(SharePreferencesMainActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login:
                String username_lg = edit_username.getText().toString();
                String password_lg = edit_password.getText().toString();

                SharedPreferences preferences2 = getSharedPreferences("userInfo",MODE_PRIVATE);
                if(username_lg.equals(preferences2.getString("username",""))&&password_lg.equals(preferences2.getString("password",""))){
                    Toast.makeText(SharePreferencesMainActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(SharePreferencesMainActivity.this,"登录失败！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
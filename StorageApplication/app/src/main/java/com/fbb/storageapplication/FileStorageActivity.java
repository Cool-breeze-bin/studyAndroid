package com.fbb.storageapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileStorageActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_write,edit_read;
    private Button btn_save_inner,btn_save_outer,btn_load_inner,btn_load_outer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filestorage);

        edit_write = findViewById(R.id.edit_write);
        btn_save_inner = findViewById(R.id.save_inner);
        btn_save_inner.setOnClickListener(this);
        btn_save_outer = findViewById(R.id.save_outer);
        btn_save_outer.setOnClickListener(this);

        edit_read = findViewById(R.id.edit_read);
        btn_load_inner = findViewById(R.id.load_inner);
        btn_load_inner.setOnClickListener(this);
        btn_load_outer = findViewById(R.id.load_outer);
        btn_load_outer.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_inner:
                String data_w_i = edit_write.getText().toString();
                if(TextUtils.isEmpty(data_w_i)){
                    Toast.makeText(FileStorageActivity.this,"请输入需要写入的内容！",Toast.LENGTH_SHORT).show();
                    return;
                }
                saveInnerFiles(data_w_i);
                Toast.makeText(FileStorageActivity.this,"数据已保存至内部存储文件！",Toast.LENGTH_SHORT).show();
                break;

            case R.id.load_inner:
                String data_r_i = readInnerFile("fbb.txt");
                if(!TextUtils.isEmpty(data_r_i)){
                    edit_read.setText(data_r_i);
                }
            default:
                break;
        }
    }

    public void saveInnerFiles(String data){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("fbb.txt", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fos!=null){
                    fos.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String readInnerFile(String filename){
        String content = "";
        FileInputStream fis = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = openFileInput(filename);
            InputStreamReader insReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(insReader);
            String line = reader.readLine();
            while(line != null){
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
            if (fis != null){
                fis.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            content = stringBuilder.toString();
        }
        return content;
    }
}
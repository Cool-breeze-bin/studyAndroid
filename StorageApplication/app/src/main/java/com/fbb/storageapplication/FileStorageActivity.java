package com.fbb.storageapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    }
}
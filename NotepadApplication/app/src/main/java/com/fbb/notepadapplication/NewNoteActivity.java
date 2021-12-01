package com.fbb.notepadapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {
    private FloatingActionButton addNew;
    private EditText edit_Title,edit_Content;
    private String title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
//
        edit_Title = findViewById(R.id.edit_title);
        edit_Content = findViewById(R.id.edit_content);
        addNew = findViewById(R.id.add_new);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = edit_Title.getText().toString();
                content = edit_Content.getText().toString();
                if (title != null){
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Bundle bundle = new Bundle();
                    bundle.putString("title",title);
                    bundle.putString("date",sdf.format(date));
                    bundle.putString("content",content);
                    Intent intent = new Intent();
                    intent.putExtra("noteInfo",bundle);
                    setResult(RESULT_OK,intent);
                }
                finish();
            }
        });
    }
}

package com.fbb.notepadapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoteAdapter extends AppCompatActivity {
    private TextView date,title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_item);

        date = findViewById(R.id.note_date);
        date.setText("");

        title = findViewById(R.id.note_title);
        title.setText("");
        
        content = findViewById(R.id.note_content);
        content.setText("");

    }
}

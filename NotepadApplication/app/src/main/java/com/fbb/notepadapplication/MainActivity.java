package com.fbb.notepadapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private FloatingActionButton btn_add;
    private List<Note> noteList = new ArrayList<>();
    private ListView listView;
    private NoteHelper noteHelper;
    private SQLiteDatabase noteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteHelper = new NoteHelper(MainActivity.this,"Notes.db",null,1);

        listView = findViewById(R.id.listview);
        initeNotes();


        btn_add = findViewById(R.id.new_note);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this,NewNoteActivity.class);
                startActivityForResult(add,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (requestCode == RESULT_OK){
                    Bundle bundle = data.getBundleExtra("noteInfo");
                    noteDB = noteHelper.getReadableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("title",bundle.getString("title"));
                    values.put("date",bundle.getString("date"));
                    values.put("content",bundle.getString("content"));
                    noteDB.insert("Notes",null,values);
                }
                initeNotes();
                break;
        }
    }

    private void initeNotes() {
        
    }
}
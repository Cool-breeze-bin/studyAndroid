package com.fbb.notepadapplication;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = noteList.get(position);
                Integer note_id = note.getId();

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setIcon(R.drawable.newnote_icon);
                alertDialog.setTitle("删除此事务");

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteDB = noteHelper.getReadableDatabase();
                        noteDB.delete("Notes","id=?",new String[]{String.valueOf(note_id)});
                        initeNotes();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
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
        noteList.clear();
        noteDB = noteHelper.getReadableDatabase();
        Cursor cursor = noteDB.query("Notes",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setDate(cursor.getString(cursor.getColumnIndex("date")));
                note.setContent(cursor.getString(cursor.getColumnIndex("content")));
                noteList.add(note);
            }while (cursor.moveToNext());
        }
        cursor.close();
        NoteAdapter noteAdapter = new NoteAdapter(MainActivity.this, R.layout.note_item, noteList);
        listView.setAdapter(noteAdapter);

    }
}
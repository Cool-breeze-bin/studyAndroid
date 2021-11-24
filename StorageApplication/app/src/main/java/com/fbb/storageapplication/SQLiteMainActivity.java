package com.fbb.storageapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SQLiteMainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_create,btn_insert,btn_delete,btn_update,btn_query;
    private MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        btn_create = findViewById(R.id.create_sqlite);
        btn_create.setOnClickListener(this);

        btn_insert = findViewById(R.id.insert_sqlite);
        btn_insert.setOnClickListener(this);

        btn_delete = findViewById(R.id.delete_sqlite);
        btn_delete.setOnClickListener(this);

        btn_update = findViewById(R.id.update_sqlite);
        btn_update.setOnClickListener(this);

        btn_query = findViewById(R.id.query_sqlite);
        btn_query.setOnClickListener(this);

        myHelper = new MyHelper(SQLiteMainActivity.this,"Student.db",null,1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_sqlite:
                myHelper.getWritableDatabase();
                Toast.makeText(SQLiteMainActivity.this,"数据库创建成功!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.insert_sqlite:
                break;
            case R.id.delete_sqlite:
                break;
            case R.id.update_sqlite:
                break;
            case R.id.query_sqlite:
                break;
        }
    }

    class MyHelper extends SQLiteOpenHelper {
        public static final String CREAT_ClASS01 = "create table Class01("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "gender text,"
                + "age integer,"
                + "major text)";

        public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREAT_ClASS01);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists Class01");
            onCreate(sqLiteDatabase);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHelper.close();
    }
}
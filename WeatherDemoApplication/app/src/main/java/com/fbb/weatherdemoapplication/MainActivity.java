package com.fbb.weatherdemoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_choose_area);
//        SQLiteDatabase db = LitePal.getDatabase();
//        LitePal.getDatabase();
    }
}
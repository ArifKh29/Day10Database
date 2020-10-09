package com.example.day10_database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nama;
    TextView tampil;
    Button simpan, getAll;
    DatabaseHelper databaseHelper;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        nama = findViewById(R.id.input);
        tampil = findViewById(R.id.tmpnama);
        simpan = findViewById(R.id.btnSimpan);
        getAll = findViewById(R.id.btnGetAll);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.insertData(nama.getText().toString());
                nama.setText("");
                Toast.makeText(MainActivity.this,"Data tersimpan", Toast.LENGTH_LONG).show();
            }
        });

        getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = databaseHelper.getAllData();
                tampil.setText("");
                for(int i = 0;i < arrayList.size(); i++){
                    tampil.setText(tampil.getText().toString()+ " ," +arrayList.get(i));
                }
            }
        });
    }
}

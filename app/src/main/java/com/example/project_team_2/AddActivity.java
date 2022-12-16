package com.example.project_team_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_license);


    }
    public void back(View view) {
        Intent intent = new Intent(AddActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
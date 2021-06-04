package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        TextView commentOne = findViewById(R.id.text_view_one);
        TextView commentTwo = findViewById(R.id.text_view_two);

        commentOne.setText("You're now inside the app");
        commentTwo.setText("What do you want to do next??");
    }
}
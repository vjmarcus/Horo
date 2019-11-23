package com.freshappbooks.horo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static com.freshappbooks.horo.MainActivity.horo;

public class ResultActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.tv_result);
        if (horo != null) {
            textView.setText(horo);
        }
    }
}

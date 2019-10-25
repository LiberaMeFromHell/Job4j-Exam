package ru.job4j.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textView = findViewById(R.id.textView);
        int[] answers= getIntent().getIntArrayExtra(ExamActivity.ANSWERS);
        StringBuilder str = new StringBuilder("Question 1: " + answers[0] + "\n").
                append("Question 2: " + answers[1] + "\n").
                append("Question 3: " + answers[2]);
        textView.setText(str);
    }
}

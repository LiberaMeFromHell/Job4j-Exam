/**
 * The app demonstrates screen rotation event in Android.
 * @author Rustam Galimov
 * @since 30.10.2019
 * @version 1.0
 */
package ru.job4j.exam.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import ru.job4j.exam.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textView = findViewById(R.id.textView);
        int[] answers = getIntent().getIntArrayExtra(QuestionFragment.ANSWERS);
        StringBuilder str = new StringBuilder("Question 1: " + answers[0] + "\n").
                append("Question 2: " + answers[1] + "\n").
                append("Question 3: " + answers[2]);
        textView.setText(str);
    }
}

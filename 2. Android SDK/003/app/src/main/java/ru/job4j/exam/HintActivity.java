package ru.job4j.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class HintActivity extends AppCompatActivity {

    private final Map<Integer, String> answers = new HashMap<Integer, String>();

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.hint_activity);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                }
        );

        TextView text = findViewById(R.id.hint);
        int question = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        text.setText(this.answers.get(question));
    }

    public HintActivity() {
        this.answers.put(0, "Hint 1");
        this.answers.put(1, "Hint 2");
        this.answers.put(2, "Hint 3");
    }
}
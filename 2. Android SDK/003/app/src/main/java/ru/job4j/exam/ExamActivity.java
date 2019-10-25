package ru.job4j.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private int position = 0;

    private Button next;
    private Button previous;
    public static final String HINT_FOR = "hint_for";
    public static final String ANSWERS = "Answers";

    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
        fillForm();

        next = findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);

        previous = findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);

        RadioGroup variants = findViewById(R.id.variants);
        variants.setOnCheckedChangeListener(this::radioButtonChecked);

        Button hint = findViewById(R.id.hint);
        hint.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ExamActivity.this, HintActivity.class);
                        intent.putExtra(HINT_FOR, position);
                        startActivity(intent);
                    }
                }
        );
    }

    private void fillForm() {

        final TextView text = findViewById(R.id.question);

        Question question = this.questions.get(this.position);

        text.setText(question.getText());
        RadioGroup variants = findViewById(R.id.variants);

        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
        variants.clearCheck();

        if (question.getChoose() != -1) {
            variants.check(question.getChoose());
        }
        else {

            findViewById(R.id.previous).setEnabled(false);
            findViewById(R.id.next).setEnabled(false);
        }
    }

    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
        saveChoice(id);
    }

    private void saveChoice(int choice) {
        this.questions.get(this.position).setChoose(choice);
    }

    private void nextBtn(View view){
        showAnswer();
        int[] b;

        if (position == 2) {
            b = new int[3];
            for (int i = 0; i < questions.size(); i++) {
                b[i] = questions.get(i).getChoose();
            }
            Intent intent = new Intent(ExamActivity.this,ResultActivity.class);
            intent.putExtra(ExamActivity.ANSWERS,b);
            startActivity(intent);
        }

        else
            position++;
            fillForm();
    }

    private void previousBtn(View view){
        showAnswer();
        position--;
        fillForm();
    }

    private void radioButtonChecked(RadioGroup radioGroup, int i){
        next.setEnabled(true);
        previous.setEnabled(position != 0);
    }
}

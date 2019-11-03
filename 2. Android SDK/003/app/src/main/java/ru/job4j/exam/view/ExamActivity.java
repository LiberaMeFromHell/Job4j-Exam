/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ru.job4j.exam.R;
import ru.job4j.exam.model.MyStore;
import ru.job4j.exam.model.Store;

public class ExamActivity extends AppCompatActivity {

    private int position = 0;

    private Button next;
    private Button previous;
    private Store store;
    private RadioGroup variants;
    private TextView text;

    public static final String HINT_FOR = "hint_for";
    public static final String ANSWERS = "Answers";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);

        store = MyStore.getInstance();

        text = findViewById(R.id.question);

        variants = findViewById(R.id.variants);
        variants.setOnCheckedChangeListener(this::radioButtonChecked);

        next = findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);

        previous = findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);

        fillForm();

        Button hint = findViewById(R.id.hint);
        hint.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        Intent intent = new Intent(
                                ExamActivity.this, HintActivity.class);
                        intent.putExtra(HINT_FOR, position);
                        startActivity(intent);
                    }
                }
        );
    }

    private void fillForm() {

        text.setText(store.getQuestionText(this.position));

        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            button.setId(store.getOptionsID(position, index));
            button.setText(store.getOptionText(position, index));
        }
        variants.clearCheck();

        if (store.getAnswer(position) != -1) {
            variants.check(store.getChoice(position));
        } else {

            findViewById(R.id.previous).setEnabled(false);
            findViewById(R.id.next).setEnabled(false);
        }
    }

    private void showAnswer() {
        variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Toast.makeText(
                this,
                "Your answer is " + id + ", correct is " + store.getAnswer(position),
                Toast.LENGTH_SHORT
        ).show();
        saveChoice(id);
    }

    private void saveChoice(final int choice) {
        store.setChoose(position, choice);
    }

    private void nextBtn(final View view) {
        showAnswer();
        int[] b;

        if (position == 2) {
            final int questionNumber = 3;
            b = new int[questionNumber];
            for (int i = 0; i < store.getSize(); i++) {
                b[i] = store.getAnswer(i);
            }
            Intent intent = new Intent(ExamActivity.this, ResultActivity.class);
            intent.putExtra(ExamActivity.ANSWERS, b);
            startActivity(intent);
        } else {
            position++;
        }
        fillForm();
    }

    private void previousBtn(final View view) {
        showAnswer();
        position--;
        fillForm();
    }

    private void radioButtonChecked(final RadioGroup radioGroup, final int i) {
        next.setEnabled(true);
        previous.setEnabled(position != 0);
    }
}

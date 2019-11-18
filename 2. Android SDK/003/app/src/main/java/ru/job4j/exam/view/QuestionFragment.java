/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import ru.job4j.exam.R;
import ru.job4j.exam.model.MyStore;
import ru.job4j.exam.model.Store;

public class QuestionFragment extends Fragment implements ConfirmHintDialogFragment.ConfirmHintDialogListener{
    private int position = 0;

    private Button next;
    private Button previous;
    private Store store;
    private RadioGroup variants;
    private TextView text;

    public static final String HINT_FOR = "hint_for";
    public static final String ANSWERS = "Answers";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.question_fragment, container, false);
        store = MyStore.getInstance();

        text = view.findViewById(R.id.question);

        variants = view.findViewById(R.id.variants);
        variants.setOnCheckedChangeListener(this::radioButtonChecked);

        next = view.findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);

        previous = view.findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);

        fillForm();

        Button hint = view.findViewById(R.id.hint);
        hint.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {

                        DialogFragment dialog = new ConfirmHintDialogFragment(QuestionFragment.this);

                        dialog.show(getActivity().getSupportFragmentManager(), "dialog_tag");
                    }
                }
        );
        return view;
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
            previous.setEnabled(false);
            next.setEnabled(false);
        }
    }

    private void showAnswer() {
        int id = variants.getCheckedRadioButtonId();
        Toast.makeText(
                getActivity(),
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
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra(QuestionFragment.ANSWERS, b);
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

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        Intent intent = new Intent(
                getActivity(), HintActivity.class);
        intent.putExtra(HINT_FOR, position);
        startActivity(intent);
    }

    @Override
    public void onNegativeDialogClick(DialogFragment dialog) {
        Toast.makeText(getActivity(), "Молодец!!!", Toast.LENGTH_SHORT).show();
    }
}

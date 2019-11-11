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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.job4j.exam.R;
import ru.job4j.exam.model.Exam;

public class ExamsActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable final Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        updateUI();
    }

    public class ExamHolder extends RecyclerView.ViewHolder {

        private View view;

        public ExamHolder(@NonNull final View view) {
            super(view);
            this.view = itemView;
        }
    }

    public class ExamAdapter extends RecyclerView.Adapter<ExamHolder> {

        private final List<Exam> exams;

        public ExamAdapter(final List<Exam> exams) {
            this.exams = exams;
        }

        @NonNull
        @Override
        public ExamHolder onCreateViewHolder(final @NonNull ViewGroup parent, final int i) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.info_exam, parent, false);
            return new ExamHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ExamHolder holder, final int i) {
            final Exam exam = this.exams.get(i);
            TextView text = holder.view.findViewById(R.id.info);
            TextView result = holder.view.findViewById(R.id.result);
            TextView date = holder.view.findViewById(R.id.date);
            text.setText(exam.getName());
            result.setText("" + exam.getResult());
            date.setText("" + new Date(exam.getTime()));
            text.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(final View view) {
                            Toast.makeText(
                                    getApplicationContext(), "You select " + exam,
                                    Toast.LENGTH_SHORT
                            ).show();
                            Intent intent = new Intent(ExamsActivity.this, ExamActivity.class);
                            startActivity(intent);
                        }
                    }
            );
        }

        @Override
        public int getItemCount() {
            return this.exams.size();
        }
    }

    private void updateUI() {
        List<Exam> exams = new ArrayList<Exam>();
        for (int index = 0; index != 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
        this.recycler.setAdapter(new ExamAdapter(exams));
    }
}
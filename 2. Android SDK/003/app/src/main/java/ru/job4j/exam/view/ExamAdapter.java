package ru.job4j.exam.view;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import ru.job4j.exam.R;
import ru.job4j.exam.model.Exam;


public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamHolder> {

    private final List<Exam> exams;
    private Activity activity;

    public ExamAdapter(final List<Exam> exams, Activity activity) {
        this.exams = exams;
        this.activity = activity;
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
                                activity.getApplicationContext(), "You select " + exam,
                                Toast.LENGTH_SHORT
                        ).show();
                        Intent intent = new Intent(activity, ExamActivity.class);
                        activity.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return this.exams.size();
    }

    public void addExam(Exam exam) {
        exams.add(exam);
        this.notifyDataSetChanged();
    }

    public void deleteAll(){
        exams.clear();
        this.notifyDataSetChanged();
    }

    class ExamHolder extends RecyclerView.ViewHolder {

        private View view;

        public ExamHolder(@NonNull final View view) {
            super(view);
            this.view = itemView;
        }
    }
}
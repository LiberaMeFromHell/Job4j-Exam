package ru.job4j.exam.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import ru.job4j.exam.R;
import ru.job4j.exam.model.Exam;
import ru.job4j.exam.model.database.ExamDbSchema;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamHolder> {

    private SQLiteDatabase store;
    private final List<Exam> exams;
    private AppCompatActivity activity;

    public ExamAdapter(final List<Exam> exams, AppCompatActivity activity, SQLiteDatabase store) {
        this.exams = exams;
        this.activity = activity;
        this.store = store;
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

        if ((i % 2) == 0) {
            holder.view.setBackgroundColor(Color.parseColor("#d8d8d8"));
        }

        text.setText(exam.getName());
        result.setText("" + exam.getResult());
        date.setText("" + new Date(exam.getTime()));

        text.setOnClickListener(
                btn -> {
                    Toast.makeText(
                            activity.getApplicationContext(), "You select " + exam,
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent intent = new Intent(activity, ExamActivity.class);
                    activity.startActivity(intent);
                }

        );

        holder.view.findViewById(R.id.edit)
                .setOnClickListener(
                        btn -> {
                            FragmentManager fm = activity.getSupportFragmentManager();
                            Fragment fragment = new ExamUpdateFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", exam.getId());
                            bundle.putString("name", exam.getName());
                            fragment.setArguments(bundle);
                            fm.beginTransaction()
                                    .replace(R.id.content, fragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                );

        holder.view.findViewById(R.id.delete).setOnClickListener(
                btn -> {
                    store.delete(ExamDbSchema.ExamTable.NAME, "id = ?", new String[]{String.valueOf(exam.getId())});
                    exams.remove(exam);
                    notifyItemRemoved(i);
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

    public void deleteAll() {
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

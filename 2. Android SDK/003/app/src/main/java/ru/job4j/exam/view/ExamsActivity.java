/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.exam.R;
import ru.job4j.exam.model.Exam;

public class ExamsActivity extends AppCompatActivity implements ConfirmDeletionDialogFragment.ConfirmDeletionDialogListener {

    private RecyclerView recycler;
    private ExamAdapter examAdapter;

    @Override
    protected void onCreate(@Nullable final Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        updateUI();
    }

    private void updateUI() {
        List<Exam> exams = new ArrayList<Exam>();
        for (int index = 0; index != 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
        examAdapter = new ExamAdapter(exams, this);
        this.recycler.setAdapter(examAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_exam, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                examAdapter.addExam(new Exam(
                        examAdapter.getItemCount(),
                        "Exam " + examAdapter.getItemCount(),
                        System.currentTimeMillis(),
                        examAdapter.getItemCount()
                ));
                return true;

            case R.id.delete_item:
                DialogFragment dialogFragment = new ConfirmDeletionDialogFragment(this);
                dialogFragment.show(ExamsActivity.this.getSupportFragmentManager(),"dialog");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        examAdapter.deleteAll();
    }
}

package ru.job4j.exam.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.exam.R;
import ru.job4j.exam.model.Exam;
import ru.job4j.exam.model.database.ExamBaseHelper;
import ru.job4j.exam.model.database.ExamDbSchema;

public class ExamListFragment extends Fragment {

    private RecyclerView recycler;
    private SQLiteDatabase store;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exams_fragment, container, false);
        this.recycler = view.findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.store = new ExamBaseHelper(this.getContext()).getWritableDatabase();
        updateUI();
        return view;
    }

    private void updateUI() {
        List<Exam> exams = new ArrayList<Exam>();
        Cursor cursor = this.store.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exams.add(new Exam(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    System.currentTimeMillis(),
                    100
            ));
            cursor.moveToNext();
        }
        cursor.close();
        this.recycler.setAdapter(new ExamAdapter(exams, (AppCompatActivity) getActivity(), store));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_exam, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_item:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.content, new ExamAddFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

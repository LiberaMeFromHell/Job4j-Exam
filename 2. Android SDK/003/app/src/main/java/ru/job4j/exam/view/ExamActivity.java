/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import ru.job4j.exam.R;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_frg);

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .add(R.id.content, loadFrg())
                    .commit();
        }
    }

    public Fragment loadFrg() {
        return new QuestionFragment();
    }
}

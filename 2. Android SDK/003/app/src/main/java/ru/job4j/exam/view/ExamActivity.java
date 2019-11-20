/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import ru.job4j.exam.R;

public class ExamActivity extends AppCompatActivity {

    private FragmentManager fm;
    private Fragment examFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_frg);

        fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            examFragment = loadFrg();
        } else if ((examFragment = fm.getFragment(savedInstanceState, "ExamFragment")) == null) {
            examFragment = loadFrg();
        }

        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .add(R.id.content, examFragment)
                    .commit();
        }
    }

    public Fragment loadFrg() {
        return new QuestionFragment();
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        if (examFragment != null) {
            fm.putFragment(outState, "ExamFragment", examFragment);
        }
    }

}

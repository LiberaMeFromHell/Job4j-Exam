/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.job4j.exam.R;

public class ExamsActivity extends AppCompatActivity /*implements ConfirmDeletionDialogFragment.ConfirmDeletionDialogListener*/ {

    private FragmentManager fm;
    private Fragment examListFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_frg);

        fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            examListFragment = loadFrg();
        } else if ((examListFragment = fm.getFragment(savedInstanceState, "ExamFragment")) == null) {
            examListFragment = loadFrg();
        }

        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .add(R.id.content, examListFragment)
                    .commit();
        }
    }

    public Fragment loadFrg() {
        return new ExamListFragment();
    }
}

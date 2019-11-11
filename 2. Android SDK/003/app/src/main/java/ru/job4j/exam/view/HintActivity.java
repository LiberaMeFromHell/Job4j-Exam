/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.view;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.job4j.exam.R;

public class HintActivity extends AppCompatActivity {

    private FragmentManager fm;
    private Fragment hintFragment;

    @Override
    protected void onCreate(@Nullable final Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.host_frg);

        fm = getSupportFragmentManager();

        if (state == null) {
            hintFragment = loadFrg();
        } else if ((hintFragment = fm.getFragment(state, "HintFragment")) == null) {
            hintFragment = loadFrg();
        }

        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .add(R.id.content, loadFrg())
                    .commit();
        }
    }

    public Fragment loadFrg() {
        return new HintFragment();
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState, @NonNull final PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (hintFragment != null) {
            fm.putFragment(outState, "HintFragment", hintFragment);
        }
    }
}

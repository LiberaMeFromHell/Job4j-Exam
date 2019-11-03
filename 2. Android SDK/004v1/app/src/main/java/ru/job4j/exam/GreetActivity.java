package ru.job4j.exam;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class GreetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment loadFrg() {
        return new GreetFragment();
    }
}

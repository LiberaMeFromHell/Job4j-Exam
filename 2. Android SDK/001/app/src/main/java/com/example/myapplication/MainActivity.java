/**
 * The app demonstrates screen rotation event in Android.
 * @author Rustam Galimov
 * @since 30.10.2019
 * @version 1.0
 */
package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public final class MainActivity extends AppCompatActivity {

    /**
     * The variable is incremented each time a screen was rotated.
     */
    private int count = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
        }
        Log.d("onCreate", "count = " + count);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        ++count;
        outState.putInt("count", count);
    }
}

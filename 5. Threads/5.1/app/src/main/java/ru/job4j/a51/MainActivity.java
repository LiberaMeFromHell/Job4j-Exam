package ru.job4j.a51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private volatile boolean stopThread = false;
    private TestThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view) {
        thread = new TestThread(10);
        thread.start();
    }

    public void stopThread(View view) {

        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
        thread.interrupt();
    }

    @Override
    protected void onDestroy() {
        thread.interrupt();
        super.onDestroy();
    }
}

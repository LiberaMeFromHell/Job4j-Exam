package ru.job4j.a51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private volatile boolean stopThread = false;
    private TestThread thread;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
    }

    public void startThread(View view) {
        thread = new TestThread(10,textView,imageView);
        thread.start();
        thread.loadImage(new String[]{"storage/0/emulated/1.jpg"});
    }

    public void stopThread(View view) {

        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
        thread.interrupt();
    }

    @Override
    protected void onDestroy() {
        thread.interrupt();
        thread.disposeImageLoader();
        super.onDestroy();
    }
}

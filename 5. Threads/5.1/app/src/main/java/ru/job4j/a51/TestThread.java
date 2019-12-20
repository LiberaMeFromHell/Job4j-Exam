package ru.job4j.a51;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TestThread extends Thread {
    private int times;
    private Handler mainHandler = new Handler();
    private TextView textView;
    private ImageView imageView;

    public TestThread(int times, TextView textView, ImageView imageView) {
        this.times = times;
        this.textView = textView;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        int count = 0;

        editImageView(imageView);

        while (count != times) {
            Log.d("TAG", "run: " + count);
            count++;

            if (count == 5) {
                editTextView();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void editTextView() {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("50%");
            }
        });
    }

    private void editImageView(final ImageView imageView) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = BitmapFactory
                        .decodeResource(imageView.getResources(), R.drawable.temp1);
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}

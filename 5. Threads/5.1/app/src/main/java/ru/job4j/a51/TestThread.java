package ru.job4j.a51;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URL;

public class TestThread extends Thread {

    private static ImageView imageView;

    private ImageLoader imageLoader;
    private int times;
    private Handler mainHandler = new Handler();
    private TextView textView;

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

    public void loadImage(String[] urls) {
        ReferenceQueue rq = new ReferenceQueue();
        imageLoader = new ImageLoader();
        imageLoader.doInBackground(urls);
        WeakReference weakReference = new WeakReference(imageLoader,rq);
    }

    private static class ImageLoader extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(urls[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void disposeImageLoader() {
        imageLoader = null;
    }
}

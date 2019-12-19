package ru.job4j.a51;

import android.util.Log;

public class TestThread extends Thread {
    private int times;

    public TestThread(int times) {
        this.times = times;
    }

    @Override
    public void run() {
        int count = 0;
        while (count != times) {
            Log.d("TAG", "run: " + count);
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

package ru.job4j.a033;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyDatePicker.OnDatePickedListener, MyTimePicker.OnTimePickedListener{

    private String textDate;
    private  TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new MyDatePicker();
                dialogFragment.show(getSupportFragmentManager(),"tag");
            }
        });
    }

    @Override
    public void datePicked(String date) {
        DialogFragment dialogFragment = new MyTimePicker();
        dialogFragment.show(getSupportFragmentManager(),"tag");
        textDate = date;
    }

    @Override
    public void timePicked(String time) {
        textView.setText(textDate + " " + time);
    }
}

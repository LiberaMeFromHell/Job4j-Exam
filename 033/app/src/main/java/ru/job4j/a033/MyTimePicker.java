package ru.job4j.a033;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private OnTimePickedListener timePicked;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timePicked.timePicked("" + hourOfDay + ":" + minute);
    }

    public interface OnTimePickedListener {
        void timePicked(String time);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            timePicked = (OnTimePickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement OnDatePickedListener", context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        timePicked = null;
    }
}

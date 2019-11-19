package ru.job4j.a033;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    OnDatePickedListener datePicked;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        datePicked.datePicked("" + day + "/" + month + "/" + year );
    }

    public interface OnDatePickedListener {
        void datePicked(String date);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            datePicked = (OnDatePickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement OnDatePickedListener", context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        datePicked = null;
    }
}

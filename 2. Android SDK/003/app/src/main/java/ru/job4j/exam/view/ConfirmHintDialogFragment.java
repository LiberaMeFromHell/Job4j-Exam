package ru.job4j.exam.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmHintDialogFragment extends DialogFragment {

    private ConfirmHintDialogListener callback;

    public ConfirmHintDialogFragment(ConfirmHintDialogListener callback) {
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("Показать подсказку?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onPositiveDialogClick(ConfirmHintDialogFragment.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onNegativeDialogClick(ConfirmHintDialogFragment.this);
                    }
                }) // кнопка Отмена
                .create();
        return dialog;
    }

    public interface ConfirmHintDialogListener {

        void onPositiveDialogClick(DialogFragment dialog);
        void onNegativeDialogClick(DialogFragment dialog);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}

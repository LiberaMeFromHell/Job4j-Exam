package ru.job4j.exam.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmDeletionDialogFragment extends DialogFragment {

    private ConfirmDeletionDialogListener callback;

    public ConfirmDeletionDialogFragment(ConfirmDeletionDialogListener callback) {
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("Удалить все записи?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onPositiveDialogClick(ConfirmDeletionDialogFragment.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        return dialog;
    }

    public interface ConfirmDeletionDialogListener {
        void onPositiveDialogClick(DialogFragment dialog);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}

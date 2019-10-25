package ru.job4j.exam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    private OnNextButtonClickListener callback;
    private Button nextFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        nextFragment = view.findViewById(R.id.next_fragment);
        nextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstFragment.this.onClick(view);
            }
        });
        return view;
    }

    public interface OnNextButtonClickListener {
        void onNextButtonClicked(String message);
    }

    public void onClick(View view) {
        callback.onNextButtonClicked("Next button clicked");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnNextButtonClickListener) context; // назначаем активити при присоединении фрагмента к активити
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // обнуляем ссылку при отсоединении фрагмента от активити
    }


}

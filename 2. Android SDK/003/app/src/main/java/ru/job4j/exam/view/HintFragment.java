package ru.job4j.exam.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import ru.job4j.exam.R;

public class HintFragment extends Fragment {

    private final Map<Integer, String> answers = new HashMap<>();

    public HintFragment() {
        this.answers.put(0, "Hint 1");
        this.answers.put(1, "Hint 2");
        this.answers.put(2, "Hint 3");
    }

    public static HintFragment of(int index) {
        HintFragment hint = new HintFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(QuestionFragment.HINT_FOR, index);
        hint.setArguments(bundle);
        return hint;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hint_fragment, container, false);
        Button back = view.findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        getActivity().onBackPressed();
                    }
                }
        );

        TextView text = view.findViewById(R.id.hint);
        int question = getActivity().getIntent().getIntExtra(QuestionFragment.HINT_FOR, 0);
        text.setText(this.answers.get(question));
        return view;
    }
}

package ru.job4j.daggerjob4j;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

    private final List<String> items;

    public MyRecyclerAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final String item = items.get(position);
        TextView textView = holder.view.findViewById(R.id.itemText);
        textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;

        public MyViewHolder(@NonNull final View view) {
            super(view);
            this.view = itemView;
        }
    }


}

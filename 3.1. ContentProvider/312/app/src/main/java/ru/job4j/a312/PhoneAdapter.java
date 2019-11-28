package ru.job4j.a312;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> phones;

    public PhoneAdapter(List<String> phones) {
        this.phones = phones;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.phone, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView text  = holder.itemView.findViewById(R.id.name);
        text.setText(phones.get(position));
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}

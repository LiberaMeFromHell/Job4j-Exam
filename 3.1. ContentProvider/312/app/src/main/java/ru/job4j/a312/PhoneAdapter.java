package ru.job4j.a312;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Store phones;

    public PhoneAdapter(Store phones) {
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
        text.setText(phones.getItem(position));
    }

    @Override
    public int getItemCount() {
        return phones.getPhones().size();
    }

    public void setPhones(Store phones) {
        this.phones = phones;
    }
}

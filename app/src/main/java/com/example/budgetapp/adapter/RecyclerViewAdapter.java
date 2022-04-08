package com.example.budgetapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView labelDate, labelDescription, labelAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            labelDate = itemView.findViewById(R.id.labelDate);
            labelDescription = itemView.findViewById(R.id.labelDescription);
            labelAmount = itemView.findViewById(R.id.labelAmount);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.recycler_view, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        TextView _labelDate = holder.labelDate;
        TextView _labelDescription = holder.labelDescription;
        TextView _labelAmount = holder.labelAmount;

        // TODO: Use Data Model
        String date = "March 12, 2022", description = "HOD", amount = "₹ 160.00";

        _labelDate.setText(date);
        _labelDescription.setText(description);
        _labelAmount.setText(amount);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

}

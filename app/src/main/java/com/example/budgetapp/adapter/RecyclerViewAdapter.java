package com.example.budgetapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetapp.R;
import com.example.budgetapp.models.Budget;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public List<Budget> _listBudget;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView labelDate, labelDescription, labelAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            labelDate = itemView.findViewById(R.id.labelDate);
            labelDescription = itemView.findViewById(R.id.labelDescription);
            labelAmount = itemView.findViewById(R.id.labelAmount);
        }
    }

    public RecyclerViewAdapter(List<Budget> listBudget) {
        _listBudget = listBudget;
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
        Budget cardBudget = _listBudget.get(position);

        TextView _labelDate = holder.labelDate;
        TextView _labelDescription = holder.labelDescription;
        TextView _labelAmount = holder.labelAmount;

        _labelDate.setText(cardBudget.getDate());
        _labelDescription.setText(cardBudget.getDescription());
        _labelAmount.setText(cardBudget.getPrice());
    }

    @Override
    public int getItemCount() {
        return _listBudget.size();
    }

}

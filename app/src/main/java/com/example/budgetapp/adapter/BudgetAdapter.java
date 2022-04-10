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

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {

    public List<Budget> _listBudget;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView labelDate, labelTitle, labelAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            labelDate = itemView.findViewById(R.id.labelDate);
            labelTitle = itemView.findViewById(R.id.labelTitle);
            labelAmount = itemView.findViewById(R.id.labelAmount);
        }
    }

    public BudgetAdapter(List<Budget> listBudget) { _listBudget = listBudget; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.budget_card, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Budget newBudget = _listBudget.get(position);

        TextView _labelDate = holder.labelDate;
        TextView _labelTitle = holder.labelTitle;
        TextView _labelAmount = holder.labelAmount;

        _labelDate.setText(newBudget.getDate());
        _labelTitle.setText(newBudget.getTitle());
        _labelAmount.setText(newBudget.getAmount());
    }

    @Override
    public int getItemCount() { return _listBudget.size(); }
}

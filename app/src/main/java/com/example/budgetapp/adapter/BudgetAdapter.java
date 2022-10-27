package com.example.budgetapp.adapter;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetapp.HomeActivity;
import com.example.budgetapp.R;
import com.example.budgetapp.models.Budget;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "<DEBUG>";

    private final int SHOW_MENU = 1;
    private final int HIDE_MENU = 2;

    public List<Budget> _listBudget;

    FirebaseFirestore myDB = FirebaseFirestore.getInstance();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView labelDate, labelTitle, labelAmount;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            labelDate = itemView.findViewById(R.id.labelDate);
            labelTitle = itemView.findViewById(R.id.labelTitle);
            labelAmount = itemView.findViewById(R.id.labelAmount);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        public TextView labelDate;
        public EditText labelTitle, labelAmount;
        public Button btnEdit, btnDelete;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            labelDate = itemView.findViewById(R.id.labelDate);
            labelTitle = itemView.findViewById(R.id.labelTitle);
            labelAmount = itemView.findViewById(R.id.labelAmount);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public BudgetAdapter(List<Budget> listBudget) { _listBudget = listBudget; }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView;
        if (viewType == SHOW_MENU) {
            contactView = inflater.inflate(R.layout.budget_card_menu, parent, false);
            return new MenuViewHolder(contactView);
        } else {
            contactView = inflater.inflate(R.layout.budget_card, parent, false);
            return new ViewHolder(contactView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Budget newBudget = _listBudget.get(position);

        if (holder instanceof ViewHolder) {
            TextView _labelDate = ((ViewHolder) holder).labelDate;
            TextView _labelTitle = ((ViewHolder) holder).labelTitle;
            TextView _labelAmount = ((ViewHolder) holder).labelAmount;
            CardView _cardView = ((ViewHolder) holder).cardView;

            _labelDate.setText(newBudget.getDate());
            _labelTitle.setText(newBudget.getTitle());
            _labelAmount.setText(String.format("₹%s", newBudget.getAmount()));

            _cardView.setOnLongClickListener(view -> {
                showMenu(position);
                return true;
            });
        } else {
            TextView _labelDate = ((MenuViewHolder) holder).labelDate;
            EditText _labelTitle = ((MenuViewHolder) holder).labelTitle;
            EditText _labelAmount = ((MenuViewHolder) holder).labelAmount;
            Button _btnEdit = ((MenuViewHolder) holder).btnEdit;
            Button _btnDelete = ((MenuViewHolder) holder).btnDelete;

            _labelDate.setText(newBudget.getDate());
            _labelTitle.setText(newBudget.getTitle());
            _labelAmount.setText(String.format("₹%s", newBudget.getAmount()));

            _btnEdit.setOnClickListener(view -> {
                Map<String, Object> data = new HashMap<>();
                data.put("date", newBudget.getDate());
                data.put("title", _labelTitle.getText().toString().trim());
                data.put("amount", _labelAmount.getText().toString().trim());

                newBudget.setTitle(_labelTitle.getText().toString().trim());
                newBudget.setAmount(_labelAmount.getText().toString().trim());

                myDB.collection("budgets").document(newBudget.getId())
                        .update(data)
                        .addOnCompleteListener(documentReference -> Log.d(TAG, "Data updated successfully"))
                        .addOnFailureListener(e -> Log.d(TAG, "Error while updating the data : " + e.getMessage()));
            });

            _btnDelete.setOnClickListener(view -> {
                myDB.collection("budgets").document(newBudget.getId())
                        .delete()
                        .addOnCompleteListener(documentReference -> Log.d(TAG, "Data deleted successfully"))
                        .addOnFailureListener(e -> Log.d(TAG, "Error while deleting the data : " + e.getMessage()));
            });
        }
    }

    @Override
    public int getItemCount() { return _listBudget.size(); }

    @Override
    public int getItemViewType(int position) {
        if (_listBudget.get(position).isShowMenu()) {
            return SHOW_MENU;
        } else {
            return HIDE_MENU;
        }
    }

    public void showMenu(int position) {
        for (int i = 0; i < _listBudget.size(); i++)
            _listBudget.get(i).setShowMenu(false);
        _listBudget.get(position).setShowMenu(true);
        notifyItemChanged(position);
    }

    public void closeMenu(int position) {
        for (int i = 0; i < _listBudget.size(); i++)
            _listBudget.get(i).setShowMenu(false);
        notifyItemChanged(position);
    }

    public boolean isMenuShown() {
        for (int i = 0; i < _listBudget.size(); i++)
            if (_listBudget.get(i).isShowMenu()) return true;
        return false;
    }

}
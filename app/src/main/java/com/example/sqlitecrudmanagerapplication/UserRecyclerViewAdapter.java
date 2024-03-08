package com.example.sqlitecrudmanagerapplication;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sqlitecrudmanagerapplication.databinding.FragmentItemBinding;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<String[]> users;

    public UserRecyclerViewAdapter(Context context, List<String[]> users) {
        this.users = users;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.nameTV.setText(users.get(position)[0]);
        holder.passwordTV.setText(users.get(position)[1]);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameTV;
        public final TextView passwordTV;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            nameTV = binding.nameTV;
            passwordTV = binding.passwordTV;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameTV.getText() + "' " + passwordTV.getText() + "'";
        }
    }
}
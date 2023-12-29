package com.example.sqlitecrudmanagerapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class UsersFragment extends Fragment {

    private TableLayout usersTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_users, container, false);

        usersTable = view.findViewById(R.id.usersTable);
        updated();

        return view;
    }

    public void updated()
    {
        usersTable.removeAllViews();
        MyDatabaseHelper db = new MyDatabaseHelper(requireContext());
        List<String[]> users = db.getAllUsers();
        for (int i = 0, n = users.size(); i < n; i++) {
            TableRow tr = new TableRow(requireContext());
            tr.setGravity(Gravity.CENTER);
            for (int j = 0; j < 2; j++) {
                TextView tv = new TextView(requireContext());
                tv.setGravity(Gravity.CENTER);
                tv.setText(users.get(i)[j]);
                tr.addView(tv);
            }
            usersTable.addView(tr);
        }
    }
}
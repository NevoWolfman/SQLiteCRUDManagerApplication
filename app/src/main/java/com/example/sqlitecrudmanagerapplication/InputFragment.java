package com.example.sqlitecrudmanagerapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputFragment extends Fragment implements View.OnClickListener {

    private EditText nameET, passwordET, idET;
    private Button addBTN, updateBTN, deleteBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        nameET = view.findViewById(R.id.nameET);
        passwordET = view.findViewById(R.id.passwordET);
        idET = view.findViewById(R.id.idET);
        addBTN = view.findViewById(R.id.addBTN);
        updateBTN = view.findViewById(R.id.updateBTN);
        deleteBTN = view.findViewById(R.id.deleteBTN);

        addBTN.setOnClickListener(this);
        updateBTN.setOnClickListener(this);
        deleteBTN.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String name = nameET.getText().toString();
        String password = passwordET.getText().toString();
        String id = idET.getText().toString();

        MyDatabaseHelper db = new MyDatabaseHelper(requireContext());
        UsersFragment usersFragment = (UsersFragment) getParentFragmentManager().findFragmentById(R.id.usersFrag);

        if(view == addBTN)
        {
            if(password.isEmpty() || name.isEmpty())
            {
                Toast.makeText(requireContext(), "Fill inputs", Toast.LENGTH_SHORT).show();
            }
            else if(db.addUser(name, password) == -1)
            {
                Toast.makeText(requireContext(), "Adding failed", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view == updateBTN)
        {
            if(password.isEmpty() || name.isEmpty() || id.isEmpty())
            {
                Toast.makeText(requireContext(), "Fill inputs", Toast.LENGTH_SHORT).show();
            }
            else if(db.updateUser(Integer.parseInt(id), name, password) == -1)
            {
                Toast.makeText(requireContext(), "Updating failed", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view == deleteBTN)
        {
            if(id.isEmpty())
            {
                Toast.makeText(requireContext(), "Fill inputs", Toast.LENGTH_SHORT).show();
            }
            else if (db.deleteUser(Integer.parseInt(id)) == -1)
            {
                Toast.makeText(requireContext(), "Deleting failed", Toast.LENGTH_SHORT).show();
            }
        }
        usersFragment.updated();
    }
}
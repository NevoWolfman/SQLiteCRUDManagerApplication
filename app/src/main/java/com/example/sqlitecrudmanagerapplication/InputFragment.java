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

public class InputFragment extends Fragment {

    private EditText nameET, passwordET;
    private Button addBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        nameET = view.findViewById(R.id.nameET);
        passwordET = view.findViewById(R.id.passwordET);
        addBTN = view.findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String password = passwordET.getText().toString();

                if(password.isEmpty() || name.isEmpty())
                {
                    Toast.makeText(requireContext(), "Fill inputs", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyDatabaseHelper db = new MyDatabaseHelper(requireContext());
                    db.addUser(name, password);
                    UsersFragment usersFragment = (UsersFragment) getParentFragmentManager().findFragmentById(R.id.usersFrag);
                    usersFragment.updated();
                }
            }
        });

        return view;
    }
}
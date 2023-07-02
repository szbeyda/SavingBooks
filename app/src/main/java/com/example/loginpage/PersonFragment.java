package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.loginpage.database.ApplicationDatabase;
import com.example.loginpage.database.user.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {
    private Button btnexit;
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView bookCountTextView;


    private ApplicationDatabase appDatabase;
    private BookListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        btnexit = view.findViewById(R.id.btnexit);

        firstNameTextView = view.findViewById(R.id.firstNameTextView);
        lastNameTextView = view.findViewById(R.id.lastNameTextView);
        usernameTextView = view.findViewById(R.id.usernameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        passwordTextView = view.findViewById(R.id.passwordTextView);

        bookCountTextView = view.findViewById(R.id.bookCountTextView);
        adapter = new BookListAdapter(new ArrayList<>());
        ApplicationDatabase db = ApplicationDatabase.getDbInstance(getContext());

        int bookCount = db.bookDao().getUserBooks(((MainActivity2) getActivity()).incUId).size();

        bookCountTextView.setText("Book Count: " + bookCount);



        db.userDao().getUser(((MainActivity2) getActivity()).incUId).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                firstNameTextView.setText("First Name: " + user.getFirstName());
                lastNameTextView.setText("Last Name: " + user.getLastName());
                usernameTextView.setText("Username: " + user.getUserName());
                emailTextView.setText("Email: " + user.getEmail());
                passwordTextView.setText("Password: " + user.getPassword());
            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivityLogin.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

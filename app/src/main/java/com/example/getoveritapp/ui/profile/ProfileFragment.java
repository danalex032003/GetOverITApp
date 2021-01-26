package com.example.getoveritapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.getoveritapp.HomeActivity;
import com.example.getoveritapp.R;
import com.example.getoveritapp.messages.MessageDisplayer;
import com.example.getoveritapp.user.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private TextView nameTextView, usernameTextView;

    private EditText nameEditText, usernameEditText, emailEditText;

    private Button updateButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        nameTextView = rootView.findViewById(R.id.nameProfileTextView);
        usernameTextView = rootView.findViewById(R.id.usernameProfileTextView);

        nameEditText = rootView.findViewById(R.id.nameProfileEditText);
        usernameEditText = rootView.findViewById(R.id.usernameProfileEditText);

        updateButton = rootView.findViewById(R.id.updateProfileButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                nameTextView.setText(documentSnapshot.getString("name"));
                usernameTextView.setText(documentSnapshot.getString("username"));

                nameEditText.setText(documentSnapshot.getString("name"));
                usernameEditText.setText(documentSnapshot.getString("username"));
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameEditText.getText().toString().trim();
                final String name = nameEditText.getText().toString();

                documentReference.update("name", name);
                documentReference.update("username", username);

                MessageDisplayer.message(getContext(), "User updated!");
            }
        });
        return rootView;
    }
}
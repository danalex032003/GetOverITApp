package com.example.getoveritapp.ui.hiring;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.getoveritapp.HomeActivity;
import com.example.getoveritapp.R;
import com.example.getoveritapp.SetupProfileForHiringActivity;
import com.example.getoveritapp.messages.MessageDisplayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetupProfileForHiringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetupProfileForHiringFragment extends Fragment {

    private EditText descriptionEditText, phoneEditText;
    private RadioGroup radioGroup;
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

    public SetupProfileForHiringFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HiringFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetupProfileForHiringFragment newInstance(String param1, String param2) {
        SetupProfileForHiringFragment fragment = new SetupProfileForHiringFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_setup_profile_for_hiring, container, false);

        descriptionEditText = rootView.findViewById(R.id.descriptionEditText);
        phoneEditText = rootView.findViewById(R.id.phoneEditText);
        radioGroup = rootView.findViewById(R.id.radioGroupSetupProfileHiring);
        updateButton = rootView.findViewById(R.id.updateSetupUserProfileButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                descriptionEditText.setText(documentSnapshot.getString("description"));
                phoneEditText.setText(documentSnapshot.getString("phone"));
                if (documentSnapshot.getBoolean("isVisible") == true) {
                    radioGroup.check(R.id.yesRadioButton);
                }
                else {
                    radioGroup.check(R.id.noRadioButton);
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionEditText.getText().toString();
                final String phone = phoneEditText.getText().toString().trim();
                final int checkedID = radioGroup.getCheckedRadioButtonId();

                documentReference.update("description", description);
                documentReference.update("phone", phone);
                switch (checkedID) {
                    case R.id.yesRadioButton:
                        documentReference.update("isVisible", true);
                        break;
                    case R.id.noRadioButton:
                        documentReference.update("isVisible", false);
                }


                MessageDisplayer.message(getContext(), "Information updated!");
            }
        });
        return rootView;
    }
}
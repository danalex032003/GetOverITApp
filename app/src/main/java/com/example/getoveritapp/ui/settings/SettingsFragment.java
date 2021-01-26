package com.example.getoveritapp.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.getoveritapp.CardActivity;
import com.example.getoveritapp.R;
import com.example.getoveritapp.SettingsActivity;
import com.example.getoveritapp.user.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    private Button cardInfoButton, termsAndConditions, signOut;

    private ImageButton facebookImageButton, instagarmImageButton;

    private FirebaseAuth firebaseAuth;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        cardInfoButton = rootView.findViewById(R.id.cardSettingsButton);
        termsAndConditions = rootView.findViewById(R.id.termsAndConditionsSettingsButton);
        signOut = rootView.findViewById(R.id.signOutSettingsButton);

        facebookImageButton = rootView.findViewById(R.id.facebookSettingsImageButton);
        instagarmImageButton = rootView.findViewById(R.id.instagramSettingsImageButton);

        firebaseAuth = FirebaseAuth.getInstance();

        cardInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CardActivity.class);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        facebookImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cyclingo.official/?modal=admin_todo_tour"));
                startActivity(intent);
            }
        });

        instagarmImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/cyclin.go/"));
                startActivity(intent);
            }
        });

        return rootView;
    }
}
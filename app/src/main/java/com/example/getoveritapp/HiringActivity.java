package com.example.getoveritapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getoveritapp.user.entities.UserEntity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HiringActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;

    private FirestoreRecyclerAdapter userAdapter, clickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiring);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);

        //query
        Query query = firebaseFirestore.collection("users")
                .whereEqualTo("type", "user")
                .whereEqualTo("isVisible", true);

        FirestoreRecyclerOptions<UserEntity> options = new FirestoreRecyclerOptions.Builder<UserEntity>().setQuery(query, UserEntity.class).build();

        //user adapter
        userAdapter = new FirestoreRecyclerAdapter<UserEntity, UsersViewHolder>(options) {
            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull UserEntity model) {
                holder.listName.setText(model.getName());
                holder.listUsername.setText(model.getUsername());
                holder.listEmail.setText(model.getEmail());
            }

        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(HiringActivity.this, "User selected!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    //View the users in teh recyclerview
    private static class UsersViewHolder extends RecyclerView.ViewHolder {

        private TextView listName, listUsername, listEmail;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            listName = itemView.findViewById(R.id.listName);
            listUsername = itemView.findViewById(R.id.listUsername);
            listEmail = itemView.findViewById(R.id.listEmail);
        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        userAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userAdapter.startListening();
    }



}
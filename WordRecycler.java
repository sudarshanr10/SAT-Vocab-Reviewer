package com.example.testt00000;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WordRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    Button backB;
    ArrayList<Word> reviewList = new ArrayList<>();
    int counter = 0;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Word");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recycler1);
        recyclerView = findViewById(R.id.id_recycler);
        backB = findViewById(R.id.backButton2);
        ConstraintLayout constraintLayout = findViewById(R.id.id_layoutWordRecycler);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        RecyclerAdapter adapter = new RecyclerAdapter(this, reviewList);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Word word = data.getValue(Word.class);
                    reviewList.add(word);
                    adapter.notifyItemInserted(counter++);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package com.example.testt00000;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class WordFragment extends Fragment {

    Button addList, back, playButton;
    TextView name, definition, origins, url;
    MediaPlayer mediaPlayer;
    DatabaseReference dataref = FirebaseDatabase.getInstance().getReference().child("Word");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word1, container, false);

        addList = view.findViewById(R.id.addButton);
        back = view.findViewById(R.id.backButton);
        name = view.findViewById(R.id.nameText);
        definition = view.findViewById(R.id.defText);
        origins = view.findViewById(R.id.originsText);
        url = view.findViewById(R.id.urlText);
        playButton = view.findViewById(R.id.id_pronounceButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(WordFragment.this).commit();
            }
        });

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word superWord = new Word(name.getText().toString(), definition.getText().toString(),origins.getText().toString(), url.getText().toString());
                dataref.push().setValue(superWord);
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //playWord(getContext(), url.getText().toString());
                mediaPlayer = MediaPlayer.create(getContext(), Uri.parse(url.getText().toString()));
                mediaPlayer.start();
            }
        });
        return view;
    }
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }
}

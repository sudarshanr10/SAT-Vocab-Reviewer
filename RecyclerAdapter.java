package com.example.testt00000;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context parentContext;
    ArrayList<Word> reviewList = new ArrayList<Word>();
    public RecyclerAdapter(Context context, ArrayList<Word> reviewList){
        parentContext = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate xml here
        //and return view holder
        View view = LayoutInflater.from(parentContext).inflate(R.layout.activity_recycler_adapter1,parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.nameR.setText(reviewList.get(position).getName());
        holder.originsR.setText(reviewList.get(position).getOrigins());
        holder.definitionR.setText(reviewList.get(position).getDefinition());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView nameR;
        TextView originsR;
        TextView definitionR;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            //this is where we will do all of the findViewById's
            //and declare our variables
            nameR = itemView.findViewById(R.id.adapterName);
            originsR = itemView.findViewById(R.id.adapterOrigins);
            definitionR = itemView.findViewById(R.id.adapterDef);
        }
    }


}

package com.nasution.cardiacare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class exampleadapter extends RecyclerView.Adapter<exampleadapter.exampleviewholder>{
    private ArrayList<exampleitem> mExampleList;

    public static class exampleviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mtextview1;
        public TextView mtextview2;
        public TextView mtextview3;

        public exampleviewholder(@NonNull View itemView) {
            super(itemView);
            mtextview1 = itemView.findViewById(R.id.textview);
            mtextview2 = itemView.findViewById(R.id.textview3);
            mtextview3 = itemView.findViewById(R.id.textview2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"Test position"+this.getPosition(),Toast.LENGTH_SHORT).show();
        }
    }

    public exampleadapter(ArrayList<exampleitem> exampleList){
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        exampleviewholder evh = new exampleviewholder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull exampleviewholder holder, int position) {


        exampleitem currentItem = mExampleList.get(position);

        holder.mtextview1.setText(currentItem.getMtext1());
        holder.mtextview2.setText(currentItem.getMtext2());
        holder.mtextview3.setText(currentItem.getMtext3());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

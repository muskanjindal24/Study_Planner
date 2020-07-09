package com.example.studyplanner;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    View myview;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        myview = itemView;
    }

    public void setSubname(String subname){
        TextView msubjectname = myview.findViewById(R.id.sub_name);
        msubjectname.setText(subname);
    }

    public void setSubdate(String subdate){
        TextView msubdate = myview.findViewById(R.id.duedate);
        msubdate.setText(subdate);
    }

    public void setSubnote(String subnote){
        TextView msubnote = myview.findViewById(R.id.event_name);
        msubnote.setText(subnote);
    }

}

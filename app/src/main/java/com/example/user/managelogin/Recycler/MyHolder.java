package com.example.user.managelogin.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.managelogin.Data.GetData;
import com.example.user.managelogin.R;



public class MyHolder extends RecyclerView.ViewHolder{
    TextView username;
    TextView score;

    public MyHolder(View itemView) {
        super(itemView);
        username = (TextView)itemView.findViewById(R.id.username);
        score = (TextView)itemView.findViewById(R.id.score);
    }
}

package com.example.user.managelogin.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.managelogin.R;
import com.example.user.managelogin.Data.GetData;

import java.util.ArrayList;



public class MyRecycle extends RecyclerView.Adapter<MyHolder> {

    Context context;
    ArrayList<GetData> list;

    public MyRecycle(Context context,ArrayList<GetData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final GetData g = list.get(position);
        holder.username.setText(g.getUsername());
        holder.score.setText (g.getScore ());
        //GetData getData = list.get(position);
        //holder.setValues(getData);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}

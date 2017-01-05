package com.example.user.managelogin.Data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.managelogin.LoginActivity;
import com.example.user.managelogin.MainActivity;
import com.example.user.managelogin.R;
import com.example.user.managelogin.Top100Activity;
import com.example.user.managelogin.Top100Query;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by USER on 2017/1/3.
 */

public class TopAdapter extends BaseAdapter{
    Context context;
    ArrayList<TopScore> topScores;

    public TopAdapter(Context context,ArrayList<TopScore> topScores){
        this.context =context;
        this.topScores = topScores;

    }
    @Override
    public int getCount() {
        return topScores.size();
    }

    @Override
    public Object getItem(int position) {
        return topScores.get (position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){

            convertView = LayoutInflater.from (context).inflate (R.layout.topscore,parent,false);

        }
        final TextView node = (TextView)convertView.findViewById (R.id.node);
        TextView startDate = (TextView)convertView.findViewById (R.id.startDate );
        TextView endDate = (TextView)convertView.findViewById (R.id.endDate );

       final  TopScore t = (TopScore) this.getItem (position);

        node.setText (t.getNode ());
        startDate.setText (t.getStartDateInterval ());
        endDate.setText (t.getEndDateInterval ());



        return convertView;
    }
}

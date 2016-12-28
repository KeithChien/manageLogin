package com.example.user.managelogin.Recycler;

import android.support.v7.widget.RecyclerView;

import com.example.user.managelogin.Data.GetData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class FirebaseHelper {
    DatabaseReference datebase;

    ArrayList<GetData> list = new ArrayList<>();

    public FirebaseHelper(DatabaseReference datebase) {
        this.datebase = datebase;
    }

    private void fetchData(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            GetData getData = new GetData();
            getData.setUsername(ds.child("username").getValue().toString());
            getData.setScore( ds.child("score").getValue().toString ());
            System.out.println(getData.getUsername());
            System.out.println(getData.getScore());
            list.add(getData);


        }
    }
    public ArrayList<GetData> retrieve()
    {
        datebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
                System.out.println(list);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
       return list;
    }
}
package com.example.user.managelogin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.user.managelogin.Recycler.MyDividerItemDecoration;
import com.example.user.managelogin.Recycler.MyRecycle;
import com.example.user.managelogin.Data.GetData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<GetData> list = new ArrayList<>();
    RecyclerView recyclerView;
    MyRecycle adapter;
    private GetData getData;

    String keynode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration (new MyDividerItemDecoration (this,LinearLayoutManager.VERTICAL));
        Button auScore = (Button)findViewById (R.id.auScore);
        Button bIUpdate = (Button)findViewById (R.id.bIUpdate);
        Button sTopScore = (Button)findViewById (R.id.sTopScore);

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("score-boards");

        auScore.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent alintent = new Intent ();
                alintent.setClass (MainActivity.this, AllUserScore.class);
                startActivity (alintent);
            }
        });
        bIUpdate.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent biintent = new Intent ();
                biintent.setClass (MainActivity.this, UpdataBar.class);
                startActivity (biintent);
            }
        });
        sTopScore.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent sTintent = new Intent ();
                sTintent.setClass (MainActivity.this, Top100Query.class);
                startActivity (sTintent);
            }
        });



       myRef.addChildEventListener (new ChildEventListener () {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {


               long startTime = (long) dataSnapshot.child ("startDateInterval").getValue ();
               long endTime = (long) dataSnapshot.child ("endDateInterval").getValue ();
               Timestamp timestamp1 = new Timestamp (startTime);
               Timestamp timestamp2 = new Timestamp (endTime);
               Long tslong = System.currentTimeMillis ()/1000;
               Timestamp timestamp4 =new Timestamp(tslong);

               if (timestamp1.before (timestamp4) && timestamp2.after (timestamp4)) {

                   System.out.println ("時間OK");
                   keynode = dataSnapshot.getKey ();
               }
                   if (keynode != null) {
                       myRef.child (keynode).addValueEventListener (new ValueEventListener () {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot) {
                               queryTop ();
                           }

                           @Override
                           public void onCancelled(DatabaseError databaseError) {

                           }
                       });
                   }
                   else {
                       Toast.makeText (MainActivity.this,"當前沒人得分",Toast.LENGTH_LONG).show ();
                   }
               }


           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

    }
    public void queryTop(){
        Query query = myRef.child (keynode).child ("scores").orderByChild ("score").limitToFirst (100);
        query.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    GetData getData = new GetData();
                    getData.setUsername(ds.child("username").getValue().toString());
                    getData.setScore(ds.child("score").getValue().toString());


                    list.add(getData);

                }
                Collections.reverse (list);
                adapter = new MyRecycle(MainActivity.this,list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

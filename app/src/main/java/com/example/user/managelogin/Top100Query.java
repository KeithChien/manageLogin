package com.example.user.managelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.managelogin.Data.TopAdapter;
import com.example.user.managelogin.Data.TopScore;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Top100Query extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private TopScore topScore ;
    ArrayList<TopScore> topScoreArrayList = new ArrayList<> ();
    ListView topView;
    TopAdapter topAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_top100_query);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("score-boards");
        topView = (ListView)findViewById (R.id.topView);

        myRef.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                long timest = (long)dataSnapshot.child ("startDateInterval").getValue ()*1000;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                Timestamp timestamp = new Timestamp (timest);
                String testtime = df.format (timestamp);

                long timest2 = (long)dataSnapshot.child ("endDateInterval").getValue ()*1000;
                Timestamp timestamp2 = new Timestamp (timest2);
                String testtime2 = df.format (timestamp2);


                TopScore topScore =new TopScore ();
                topScore.setNode (dataSnapshot.getKey ());
                topScore.setStartDateInterval (testtime);
                topScore.setEndDateInterval (testtime2);
                topScoreArrayList.add (topScore);
               // System.out.println (dataSnapshot.child ("startDateInterval").getKey ());
                topAdapter =new TopAdapter (Top100Query.this,topScoreArrayList);
                topView.setAdapter (topAdapter);
                topAdapter.notifyDataSetChanged ();
                topView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent =new Intent ();
                        intent.setClass (Top100Query.this, Top100Activity.class);
                        LinearLayout linearLayout = (LinearLayout) view;
                        TextView t = (TextView)linearLayout.findViewById (R.id.node);
                        Bundle bundle = new Bundle ();
                        bundle.putString ("node",t.getText ().toString ());
                        intent.putExtras (bundle);
                        startActivity (intent);
                        System.out.println (t.getText ().toString ());
                    }
                });
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
}

package com.example.user.managelogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.managelogin.Data.GetData;
import com.example.user.managelogin.Recycler.MyDividerItemDecoration;
import com.example.user.managelogin.Recycler.MyRecycle;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class AllUserScore extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<GetData> list = new ArrayList<> ();
    RecyclerView userRcycler;
    MyRecycle useradapter;

     String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_all_user_score);
        userRcycler = (RecyclerView) findViewById(R.id.userRcycler);
        userRcycler.setHasFixedSize(true);
        userRcycler.setLayoutManager(new LinearLayoutManager (this,LinearLayoutManager.VERTICAL,false));
        userRcycler.addItemDecoration (new MyDividerItemDecoration (this,LinearLayoutManager.VERTICAL));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference ("score-boards");
        myRef.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                long startTime = (long) dataSnapshot.child ("startDateInterval").getValue ();
                long endTime = (long) dataSnapshot.child ("endDateInterval").getValue ();
                Timestamp timestamp1 = new Timestamp (startTime);
                Timestamp timestamp2 = new Timestamp (endTime);
                Long tslong = System.currentTimeMillis () / 1000;
                Timestamp timestamp4 = new Timestamp (tslong);
                System.out.println (dataSnapshot.child ("startDateInterval"));
                System.out.println (dataSnapshot.child ("endDateInterval"));
                System.out.println ("start" + startTime);
                System.out.println (dataSnapshot.getKey ());

                if (timestamp1.before (timestamp4) && timestamp2.after (timestamp4)) {


                    key = dataSnapshot.getKey ();
                }
                if (key != null){
                    myRef.child (key).addValueEventListener (new ValueEventListener () {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            queryTop ();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                else{

                        System.out.println (key);
                        Toast.makeText (AllUserScore.this, "當前時間沒有任何分數", Toast.LENGTH_SHORT).show ();

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

    public void queryTop() {

        myRef.child (key).child ("scores").addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren ()) {

                    GetData getData = new GetData ();

                    getData.setUsername (ds.child ("username").getValue ().toString ());
                    getData.setScore (ds.child ("score").getValue ().toString ());

                    list.add (getData);

                }

                useradapter = new MyRecycle (AllUserScore.this, list);
                userRcycler.setAdapter (useradapter);
                useradapter.notifyDataSetChanged ();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

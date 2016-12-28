package com.example.user.managelogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.widget.Toolbar;

import com.example.user.managelogin.Recycler.FirebaseHelper;
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

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    //FirebaseAuth.AuthStateListener firebaseAuthListener;
    //FirebaseUser mfirebaseUser;
    //FirebaseAuth mfirebaseAuth;
    ArrayList<GetData> list = new ArrayList<>();
    RecyclerView recyclerView;
    MyRecycle adapter;
    private GetData getData;
   // FirebaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration (new MyDividerItemDecoration (this,LinearLayoutManager.VERTICAL));
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("score-boards");
       // helper = new FirebaseHelper(myRef.child("0"));
        //adapter = new MyRecycle(helper.retrieve());


        myRef.child ("0").orderByChild ("scores").limitToFirst (5).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded (DataSnapshot dataSnapshot, String s){

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    GetData getData = new GetData();
                    getData.setUsername(ds.child("username").getValue().toString());
                    getData.setScore(ds.child("score").getValue().toString());

                    System.out.println(getData.getUsername());
                    System.out.println(getData.getScore());
                    list.add(getData);
                    adapter = new MyRecycle(MainActivity.this,list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }

            }
            @Override
            public void onChildChanged (DataSnapshot dataSnapshot, String s){
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    GetData getData = new GetData();
                    getData.setUsername(ds.child("username").getValue().toString());
                    getData.setScore( ds.child("score").getValue().toString ());
                    System.out.println(getData.getUsername());
                    System.out.println(getData.getScore());
                    list.add(getData);
                    adapter = new MyRecycle(MainActivity.this,list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onChildRemoved (DataSnapshot dataSnapshot){
            }
            @Override
            public void onChildMoved (DataSnapshot dataSnapshot, String s){
            }
            @Override
            public void onCancelled (DatabaseError databaseError){
            }
        });

    }



}

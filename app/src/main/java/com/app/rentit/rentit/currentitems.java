package com.app.rentit.rentit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class currentitems extends MainActivity{
    public static String[] str= new String[20];
    public static String[] str1= new String[20];
    public static String[] str2= new String[20];
    public static Long[] sto = new Long[20];
    public static Long[] sto1 = new Long[20];
    public static Long[] sto2 = new Long[20];
    static LinearLayout scroll;
    static LinearLayout scroll2;
    private String name;
    private final TextView[] tv = new TextView[50];
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref1;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        name = firebaseUser.getDisplayName();

        ref1 = firebaseDatabase.getReference("User/"+ name +"/Items");


        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for(i=0;i<str.length;i++){
                    str[i]=null;
                }
                for(i=0;i<str.length;i++){
                    str1[i]=null;
                }
                for(i=0;i<str.length;i++){
                    str2[i]=null;
                }
                i=0;
                for (DataSnapshot ds: dataSnapshot.child("Kitchen").getChildren()){
                    str[i] = (String) ds.getKey().toString();
                    sto[i] = (long) ds.getValue();
                    i++;
                }
                i=0;
                for (DataSnapshot ds: dataSnapshot.child("Bedroom").getChildren()){
                    str1[i] = (String) ds.getKey().toString();
                    sto1[i] = (long) ds.getValue();
                    i++;
                }
                i=0;
                for (DataSnapshot ds: dataSnapshot.child("Misc").getChildren()){
                    str2[i] = (String) ds.getKey().toString();
                    sto2[i] = (long) ds.getValue();
                    i++;
                }

                scroll.removeAllViews();
                scroll2.removeAllViews();
                populate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mAuth.signOut();
                signout();
            }
        });


        scroll = findViewById(R.id.scroll);
        scroll2 = findViewById(R.id.scroll2);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(currentitems.this,Login.class));
                }
            }
        };

    }

    public void populate (){
        Integer i;
        for (i=0; i<str.length; i++) {
            if(str[i]!=null) {
                tv[i] = new TextView(this);
                tv[i].setText("-"+str[i]);
                tv[i].setTextSize((float) 15);
                tv[i].setPadding(20, 30, 20, 30);
                if (sto[i] == 0) {
                    scroll2.addView(tv[i]);
                }
                if (sto[i] == 1){
                    scroll.addView(tv[i]);
                }

            }
            else{

            }

        }
        for (i=0; i<str1.length; i++) {
            if(str1[i]!=null) {
                tv[i] = new TextView(this);
                tv[i].setText("-"+str1[i]);
                tv[i].setTextSize((float) 15);
                tv[i].setPadding(20, 30, 20, 30);
                if (sto1[i] == 0) {
                    scroll2.addView(tv[i]);
                }
                if (sto1[i] == 1){
                    scroll.addView(tv[i]);
                }

            }
            else{

            }

        }
        for (i=0; i<str2.length; i++) {
            if(str2[i]!=null) {
                tv[i] = new TextView(this);
                tv[i].setText("-"+str2[i]);
                tv[i].setTextSize((float) 15);
                tv[i].setPadding(20, 30, 20, 30);
                if (sto2[i] == 0) {
                    scroll2.addView(tv[i]);
                }
                if (sto2[i] == 1){
                    scroll.addView(tv[i]);
                }

            }
            else{

            }

        }
    }
    private void signout() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });

    }
    protected int getLayoutResourceId() {
        return R.layout.activity_currentitems;
    }
}

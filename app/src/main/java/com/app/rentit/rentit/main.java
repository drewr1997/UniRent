package com.app.rentit.rentit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout;

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

import java.util.ArrayList;

public class main extends MainActivity {
    public static String[][] str = new String[4][20];
    public static Long[][] sto = new Long[4][20];

    static LinearLayout list, list1, list2, list3;
    static LinearLayout check, check1, check2, check3;
    static LinearLayout stock, stock1, stock2, stock3;
    static FloatingActionButton fl;
    static int q;
    private final TextView[] tv = new TextView[50];
    private final TextView[] st = new TextView[50];
    private final CheckBox[] ch = new CheckBox[50];
    static String[] items1 = new String[10];
    static String[] items2 = new String[10];
    static String[] items3 = new String[10];
    static String[] items4 = new String[10];
    static FloatingActionButton cart;
    public static String uid, email, name, uid1;
    static Boolean[][] bo = new Boolean[4][20];
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference ref = firebaseDatabase.getReference("Item/Kitchen");
    private DatabaseReference ref2 = firebaseDatabase.getReference("Item/Bedroom");
    private DatabaseReference ref3 = firebaseDatabase.getReference("Item/Misc");
    private DatabaseReference ref5 = firebaseDatabase.getReference("Item/Tech");
    private DatabaseReference ref4;
    DatabaseReference ref1 = firebaseDatabase.getReference("User");
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            uid = firebaseUser.getUid().toString();
            email = firebaseUser.getEmail();
            name = firebaseUser.getDisplayName();
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Login activity = new Login();
        activity.finish();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(main.this, Login.class));
                }
            }
        };
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            uid = firebaseUser.getUid().toString();
            email = firebaseUser.getEmail();
            name = firebaseUser.getDisplayName();
        }

        ref4 = firebaseDatabase.getReference("User/" + name + "/Items");

        ref4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int o = 0;
                for (DataSnapshot ds : dataSnapshot.child("Kitchen").getChildren()) {
                    items1[o] = ds.getKey().toString();
                    o++;
                }
                o = 0;
                for (DataSnapshot ds : dataSnapshot.child("Bedroom").getChildren()) {
                    items2[o] = ds.getKey().toString();
                    o++;
                }
                o = 0;
                for (DataSnapshot ds : dataSnapshot.child("Misc").getChildren()) {
                    items3[o] = ds.getKey().toString();
                    o++;
                }
                o=0;
                for (DataSnapshot ds : dataSnapshot.child("Tech").getChildren()) {
                    items4[o] = ds.getKey().toString();
                    o++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (i = 0; i < str.length; i++) {
                    str[0][i] = null;
                }
                i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    str[0][i] = (String) ds.getKey().toString();
                    sto[0][i] = (long) ds.getValue();
                    i++;
                }
                list.removeAllViews();
                check.removeAllViews();
                stock.removeAllViews();


                populate(0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mAuth.signOut();
                signout();
            }
        });

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (i = 0; i < str.length; i++) {
                    str[1][i] = null;
                }
                i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    str[1][i] = (String) ds.getKey().toString();
                    sto[1][i] = (long) ds.getValue();
                    i++;
                }
                list1.removeAllViews();
                check1.removeAllViews();
                stock1.removeAllViews();

                populate(1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mAuth.signOut();
                signout();
            }
        });

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (i = 0; i < str.length; i++) {
                    str[2][i] = null;
                }
                i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    str[2][i] = (String) ds.getKey().toString();
                    sto[2][i] = (long) ds.getValue();
                    i++;
                }
                list2.removeAllViews();
                check2.removeAllViews();
                stock2.removeAllViews();

                populate(2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mAuth.signOut();
                signout();
            }
        });

        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (i = 0; i < str.length; i++) {
                    str[3][i] = null;
                }
                i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    str[3][i] = (String) ds.getKey().toString();
                    sto[3][i] = (long) ds.getValue();
                    i++;
                }
                list3.removeAllViews();
                check3.removeAllViews();
                stock3.removeAllViews();

                populate(3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mAuth.signOut();
                signout();
            }
        });

        list = (LinearLayout) findViewById(R.id.linear);
        check = (LinearLayout) findViewById(R.id.checkbox);
        stock = findViewById(R.id.stock);
        list1 = (LinearLayout) findViewById(R.id.linear1);
        check1 = (LinearLayout) findViewById(R.id.checkbox1);
        stock1 = findViewById(R.id.stock1);
        list2 = (LinearLayout) findViewById(R.id.linear2);
        check2 = (LinearLayout) findViewById(R.id.checkbox2);
        stock2 = findViewById(R.id.stock2);
        list3 = (LinearLayout) findViewById(R.id.linear3);
        check3 = (LinearLayout) findViewById(R.id.checkbox3);
        stock3 = findViewById(R.id.stock3);


        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int w=0;w<3;w++){
                    for (q=0;q<bo[w].length;q++){
                        if (bo[w][q]!=false) {
                            cart();
                            q=30;
                            break;
                        }
                    }
                    if (q==30){
                        break;
                    }

                }
            }
        });


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




        for (int i = 0; i < 20; i++) {
            bo[0][i] = false;
            bo[1][i] = false;
            bo[2][i] = false;
            bo[3][i] = false;
        }


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
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

    private void cart() {
        Intent intent = new Intent(this, Cart.class);
        startActivityForResult(intent, 1);
    }

    public void populate(int l) {
        Integer i;
        for (i = 0; i < str[0].length; i++) {
            if (l == 0) {
                if (str[0][i] != null) {
                    for (int y = 0; y < items1.length; y++) {

                        if (items1[y]!=(str[0][i])||items1[y]==null){
                            st[i] = new TextView(this);
                            st[i].setText(sto[0][i].toString());
                            st[i].setTextSize((float) 15);
                            st[i].setPadding(20, 30, 20, 30);
                            tv[i] = new TextView(this);
                            tv[i].setText("-" + str[0][i]);
                            tv[i].setTextSize((float) 15);
                            tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                            tv[i].setPadding(20, 30, 20, 30);
                            ch[i] = new CheckBox(this);
                            ch[i].setText("");
                            ch[i].setPadding(20, 30, 20, 30);
                            ch[i].setTextSize((float) 15);
                            ch[i].setTag(i);
                            ch[i].setId(i);
                            ch[i].setTypeface(Typeface.DEFAULT_BOLD);
                            bo[0][i] = false;
                            ch[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                    if (isChecked) {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[0][j] = true;
                                    } else {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[0][j] = false;
                                    }
                                }
                            });
                            stock.addView(st[i]);
                            list.addView(tv[i]);
                            check.addView(ch[i]);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }

            }
            if (l == 1) {
                if (str[1][i] != null) {
                    for (int y = 0; y < items2.length; y++) {

                        if (items2[y]!=(str[1][i])||items2[y]==null){
                            st[i] = new TextView(this);
                            st[i].setText(sto[1][i].toString());
                            st[i].setTextSize((float) 15);
                            st[i].setPadding(20, 30, 20, 30);
                            tv[i] = new TextView(this);
                            tv[i].setText("-" + str[1][i]);
                            tv[i].setTextSize((float) 15);
                            tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                            tv[i].setPadding(20, 30, 20, 30);
                            ch[i] = new CheckBox(this);
                            ch[i].setText("");
                            ch[i].setPadding(20, 30, 20, 30);
                            ch[i].setTextSize((float) 15);
                            ch[i].setTag(i);
                            ch[i].setId(i);
                            ch[i].setTypeface(Typeface.DEFAULT_BOLD);
                            bo[1][i] = false;
                            ch[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                    if (isChecked) {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[1][j] = true;
                                    } else {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[1][j] = false;
                                    }
                                }
                            });
                            stock1.addView(st[i]);
                            list1.addView(tv[i]);
                            check1.addView(ch[i]);
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
            if (l == 3) {
                if (str[3][i] != null) {
                    for (int y = 0; y < items3.length; y++) {

                        if (items3[y]!=(str[3][i])||items3[y]==null){
                            st[i] = new TextView(this);
                            st[i].setText(sto[3][i].toString());
                            st[i].setTextSize((float) 15);
                            st[i].setPadding(20, 30, 20, 30);
                            tv[i] = new TextView(this);
                            tv[i].setText("-" + str[3][i]);
                            tv[i].setTextSize((float) 15);
                            tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                            tv[i].setPadding(20, 30, 20, 30);
                            ch[i] = new CheckBox(this);
                            ch[i].setText("");
                            ch[i].setPadding(20, 30, 20, 30);
                            ch[i].setTextSize((float) 15);
                            ch[i].setTag(i);
                            ch[i].setId(i);
                            ch[i].setTypeface(Typeface.DEFAULT_BOLD);
                            bo[3][i] = false;
                            ch[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                    if (isChecked) {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[3][j] = true;
                                    } else {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[3][j] = false;
                                    }
                                }
                            });
                            stock3.addView(st[i]);
                            list3.addView(tv[i]);
                            check3.addView(ch[i]);
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
            if (l == 2) {
                if (str[2][i] != null) {
                    for (int y = 0; y < items1.length; y++) {

                        if (items3[y]!=(str[2][i])||items3[y]==null){
                            st[i] = new TextView(this);
                            st[i].setText(sto[2][i].toString());
                            st[i].setTextSize((float) 15);
                            st[i].setPadding(20, 30, 20, 30);
                            tv[i] = new TextView(this);
                            tv[i].setText("-" + str[2][i]);
                            tv[i].setTextSize((float) 15);
                            tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                            tv[i].setPadding(20, 30, 20, 30);
                            ch[i] = new CheckBox(this);
                            ch[i].setText("");
                            ch[i].setPadding(20, 30, 20, 30);
                            ch[i].setTextSize((float) 15);
                            ch[i].setTag(i);
                            ch[i].setId(i);
                            ch[i].setTypeface(Typeface.DEFAULT_BOLD);
                            bo[2][i] = false;
                            ch[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                    if (isChecked) {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[2][j] = true;
                                    } else {
                                        int j;
                                        j = Integer.parseInt(compoundButton.getTag().toString());
                                        bo[2][j] = false;
                                    }
                                }
                            });
                            stock2.addView(st[i]);
                            list2.addView(tv[i]);
                            check2.addView(ch[i]);
                            break;
                        }
                        if (items3[y].equalsIgnoreCase(str[2][i])) {
                            break;
                        }

                    }

                }
            } else {

            }


        }
    }
}

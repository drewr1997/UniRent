package com.app.rentit.rentit;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cart extends AppCompatActivity {
    private final TextView[] tv = new TextView[50];
    private Button back, confrim;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference ref = firebaseDatabase.getReference("Item");
    DatabaseReference ref1 = firebaseDatabase.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        final LinearLayout scroll = (LinearLayout) findViewById(R.id.scroll);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confrim = findViewById(R.id.confirm);
        confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<main.str[0].length;i++){
                    if(main.bo[0][i]){
                        ref1.child(main.name + "/Items/Kitchen/"+main.str[0][i]).setValue(0);

                    }
                    else{

                    }
                }
                for (int i=0;i<main.str[1].length;i++){
                    if(main.bo[1][i]){
                        ref1.child(main.name + "/Items/Bedroom/"+main.str[1][i]).setValue(0);

                    }
                    else{

                    }
                }
                for (int i=0;i<main.str[2].length;i++){
                    if(main.bo[2][i]){
                        ref1.child(main.name + "/Items/Misc/"+main.str[2][i]).setValue(0);

                    }
                    else{

                    }
                }
                for (int i=0;i<main.str[3].length;i++){
                    if(main.bo[3][i]){
                        ref1.child(main.name + "/Items/Tech/"+main.str[3][i]).setValue(0);

                    }
                    else{

                    }
                }
                finish();
            }
        });

        for (int i=0;i<main.str[0].length;i++){
            if(main.bo[0][i]){
                tv[i] = new TextView(this);
                tv[i].setText("-"+main.str[0][i]);
                tv[i].setTextSize((float) 15);
                tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                tv[i].setPadding(20, 30, 20, 30);
                scroll.addView(tv[i]);

            }
            else{

            }
        }
        for (int i=0;i<main.str[1].length;i++){
            if(main.bo[1][i]){
                tv[i] = new TextView(this);
                tv[i].setText("-"+main.str[1][i]);
                tv[i].setTextSize((float) 15);
                tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                tv[i].setPadding(20, 30, 20, 30);
                scroll.addView(tv[i]);

            }
            else{

            }
        }
        for (int i=0;i<main.str[2].length;i++){
            if(main.bo[2][i]){
                tv[i] = new TextView(this);
                tv[i].setText("-"+main.str[2][i]);
                tv[i].setTextSize((float) 15);
                tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                tv[i].setPadding(20, 30, 20, 30);
                scroll.addView(tv[i]);

            }
            else{

            }
        }
        for (int i=0;i<main.str[3].length;i++){
            if(main.bo[3][i]){
                tv[i] = new TextView(this);
                tv[i].setText("-"+main.str[3][i]);
                tv[i].setTextSize((float) 15);
                tv[i].setTypeface(Typeface.DEFAULT_BOLD);
                tv[i].setPadding(20, 30, 20, 30);
                scroll.addView(tv[i]);

            }
            else{

            }
        }
    }
}

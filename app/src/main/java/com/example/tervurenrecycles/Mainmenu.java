package com.example.tervurenrecycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Mainmenu extends AppCompatActivity {

    ImageButton LocalPoint;
    ImageButton ParkPoint;
    ImageButton RecyclePoint;
    ImageButton MoreInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        LocalPoint = findViewById(R.id.LocalPoint);
        ParkPoint = findViewById(R.id.ParkPoint);
        RecyclePoint = findViewById(R.id.RecyclePoint);
        MoreInfo = findViewById(R.id.MoreInfo);

        LocalPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newlocalpoint();
            }
        });
        ParkPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newparkpoint();
            }
        });
        RecyclePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newrecyclepoint();
            }
        });
        MoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newmoreinfo();
            }
        });


    }

    private void newlocalpoint() {
        Intent settings = new Intent(this, LocalPoint.class);
        startActivity(settings);
    }

    private void newparkpoint() {
        Intent settings = new Intent(this, ParkPoint.class);
        startActivity(settings);
    }

    private void newrecyclepoint() {
        Intent settings = new Intent(this, RecyclePoint.class);
        startActivity(settings);
    }

    private void newmoreinfo() {
        Intent settings = new Intent(this, MoreInfo.class);
        startActivity(settings);
    }
}

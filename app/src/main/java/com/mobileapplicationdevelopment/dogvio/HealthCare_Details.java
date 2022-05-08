package com.mobileapplicationdevelopment.dogvio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthCare_Details extends AppCompatActivity {

    Button button7, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care_details);

        ActionBar actionBar3 = getSupportActionBar();
        actionBar3.setTitle("Dog Details");
        actionBar3.setDisplayShowHomeEnabled(true);
        actionBar3.setDisplayHomeAsUpEnabled(true);


        button7 = findViewById(R.id.btn_detail_update);
        button2 = findViewById(R.id.welcome2);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), HealthCare_Details_Update.class);
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), HealthCare_Welcome.class);
                startActivity(intent1);
            }
        });
    }
}
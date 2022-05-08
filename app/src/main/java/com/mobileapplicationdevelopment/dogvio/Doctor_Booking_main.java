package com.mobileapplicationdevelopment.dogvio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Doctor_Booking_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_booking_main);

        ActionBar DocActionBar1 = getSupportActionBar();
        DocActionBar1.setTitle("Doctor Booking");
        DocActionBar1.setDisplayShowHomeEnabled(true);
        DocActionBar1.setDisplayHomeAsUpEnabled(true);


        Button button_d1 = findViewById(R.id.doc_page1_btn);
        Button button_d2 = findViewById(R.id.doc_page1_btn2);

        button_d1.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(Doctor_Booking_main.this,Doctor_Booking_Select_Page.class);
                                             startActivity(intent);
                                         }
                                     }
        );
        button_d2.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(Doctor_Booking_main.this,doc_all_appointment.class);
                                             startActivity(intent);
                                         }
                                     }
        );
    }
}
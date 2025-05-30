package com.example.nfcparking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openBooking = findViewById(R.id.openBooking);

        // Open Booking Page
        openBooking.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SlotBookingActivity.class);
            startActivity(intent);
        });
    }
}
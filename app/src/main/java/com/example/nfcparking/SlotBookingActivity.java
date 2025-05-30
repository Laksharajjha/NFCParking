package com.example.nfcparking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SlotBookingActivity extends AppCompatActivity {

    private ListView slotListView;
    private Button bookSlotButton;
    private TextView ticketTokenText;
    private String selectedSlot = null;
    private List<String> availableSlots;
    private int selectedPosition = -1; // Track selected position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_booking);

        slotListView = findViewById(R.id.slotListView);
        bookSlotButton = findViewById(R.id.bookSlotButton);
        ticketTokenText = findViewById(R.id.ticketTokenText);

        availableSlots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) availableSlots.add("Slot " + i);

        // Custom ArrayAdapter for the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.slot_item, R.id.slotName, availableSlots) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                RadioButton radioButton = view.findViewById(R.id.slotRadioButton);
                radioButton.setChecked(position == selectedPosition);
                radioButton.setOnClickListener(v -> {
                    selectedPosition = position;
                    selectedSlot = availableSlots.get(position);
                    notifyDataSetChanged();
                    bookSlotButton.setVisibility(View.VISIBLE);
                    ticketTokenText.setVisibility(View.GONE);
                });
                return view;
            }
        };

        slotListView.setAdapter(adapter);

        bookSlotButton.setOnClickListener(v -> {
            if (selectedSlot != null) {
                // Generate a unique ticket token
                String ticketToken = UUID.randomUUID().toString();

                // Save the token to SharedPreferences
                SharedPreferences prefs = getSharedPreferences("NFCParkingPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nfcToken", ticketToken);
                editor.apply();

                // Display the token to the user
                ticketTokenText.setText("NFC Ticket Token: " + ticketToken);
                ticketTokenText.setVisibility(View.VISIBLE);

                // Show confirmation toast
                Toast.makeText(this, selectedSlot + " booked!", Toast.LENGTH_SHORT).show();

                // Start the NfcService
                Intent serviceIntent = new Intent(this, NfcService.class);
                startService(serviceIntent);
            }
        });
    }
}
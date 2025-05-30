package com.example.nfcparking;

import android.content.SharedPreferences;
import android.nfc.cardemulation.HostApduService;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.Bundle;

public class NfcService extends HostApduService {
    private String nfcToken;

    @Override
    public void onCreate() {
        super.onCreate();
        // Retrieve the token from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("NFCParkingPrefs", MODE_PRIVATE);
        nfcToken = prefs.getString("nfcToken", null);
    }

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        if (nfcToken != null) {
            try {
                // Create an NDEF message with the token
                NdefMessage message = new NdefMessage(
                        new NdefRecord[] { NdefRecord.createTextRecord("en", nfcToken) }
                );
                return message.toByteArray();
            } catch (Exception e) {
                // Return error response if something goes wrong
                return new byte[] { (byte) 0x6F, (byte) 0x00 };
            }
        }
        // Return error response if no token is available
        return new byte[] { (byte) 0x6F, (byte) 0x00 };
    }

    @Override
    public void onDeactivated(int reason) {
        // Handle deactivation if needed
    }
}
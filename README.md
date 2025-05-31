NFC Parking System
Overview
The NFC Parking System is an Android-based project consisting of two apps: NFCParking and NFC Reviewer. These apps work together to simulate a parking management system where users can book a parking slot and generate an NFC ticket (using the NFCParking app) and authenticate the ticket at a parking gate (using the NFC Reviewer app). The NFCParking app uses Host Card Emulation (HCE) to emulate an NFC tag, while the NFC Reviewer app reads the NFC ticket to authenticate it.
Apps in This Project

NFCParking: Allows users to book a parking slot and generates a unique nfcToken as an NFC ticket. This app runs on Device A and emulates an NFC tag using HCE.
NFC Reviewer: Reads the NFC ticket from the NFCParking app and authenticates the user for parking access. This app runs on Device B and communicates with the HCE service.

Features

NFCParking:
Displays a list of available parking slots.
Allows users to book a slot and generates a unique nfcToken.
Emulates an NFC tag via HCE to send the nfcToken to the NFC Reviewer app.


NFC Reviewer:
Reads the nfcToken from the NFCParking app via NFC.
Authenticates the token (currently set to validate any token for testing purposes).
Displays "Access Granted" or an error message based on the authentication result.
Includes a "Simulate NFC Tap" button for testing without NFC.



Prerequisites

Two NFC-capable Android devices running Android 4.4 (KitKat) or higher.
Device A: For the NFCParking app.
Device B: For the NFC Reviewer app.


Android Studio (version 2023.3.1 or later) for building and running the apps.
NFC must be enabled on both devices (Settings > NFC > Toggle On).
For HCE to work on Device A, ensure no other apps (e.g., Google Pay) are set as the default NFC payment app, as this can interfere with HCE.

Project Structure

/NFCParking: Contains the source code for the NFCParking app.
/NFCReviewer: Contains the source code for the NFC Reviewer app.
/README.md: This file.

Setup Instructions
1. Clone the Repository
git clone https://github.com/your-username/nfc-parking-system.git
cd nfc-parking-system

2. Open in Android Studio

Open Android Studio.
Select "Open an existing project" and navigate to the cloned repository folder.
Android Studio should recognize the project structure and load both the NFCParking and NFC Reviewer modules.

3. Build and Install the Apps
NFCParking (Device A)

In Android Studio, select the NFCParking module from the project view.
Connect Device A via USB or Wi-Fi debugging.
Click the green "Run" button to build and install the app on Device A.

NFC Reviewer (Device B)

In Android Studio, select the NFCReviewer module from the project view.
Connect Device B via USB or Wi-Fi debugging.
Click the green "Run" button to build and install the app on Device B.

4. Configure NFC Settings

On both devices, ensure NFC is enabled (Settings > NFC > Toggle On).
On Device A, ensure no other NFC payment apps are set as the default (Settings > NFC > Default Payment App > Set to "None").

Usage
Step 1: Launch NFCParking on Device A

Open the NFCParking app on Device A.
Select an available parking slot from the list.
Click the "Book Slot" button to generate an nfcToken.
The app will display the nfcToken (e.g., "NFC Ticket Token: 123e4567-e89b-12d3-a456-426614174000").
Keep the app in the foreground to enable HCE.

Step 2: Launch NFC Reviewer on Device B

Open the NFC Reviewer app on Device B.
The app will display "Waiting for NFC tap…".

Step 3: Authenticate the NFC Ticket

Tap the back of Device A against the back of Device B.
Hold the devices close for a few seconds.
The NFC Reviewer app should display "Access Granted\nToken: [your-token]" if the NFC communication is successful.

Step 4: Simulate NFC Tap (For Testing)

If NFC communication isn’t working, you can use the "Simulate NFC Tap" button in the NFC Reviewer app:
Click the button to simulate receiving a token.
The app will display "Access Granted\nToken: 123e4567-e89b-12d3-a456-426614174000".



Troubleshooting
NFC Communication Issues

NFC Reviewer Stuck on "Waiting for NFC tap…":
Ensure NFC is enabled on both devices.
Ensure both apps are in the foreground when tapping.
Check Logcat output in Android Studio:
For NFCParking: Look for logs like "Sending NDEF message with token".
For NFC Reviewer: Look for logs like "onNewIntent triggered" and "Received token".


Test the NFCParking app with a third-party NFC reader app (e.g., NFC Tools) on Device B to confirm it’s sending the token.


HCE Conflicts:
On Device A, ensure no other apps are set as the default NFC payment app.
Restart both devices to reset the NFC controller.



Device Compatibility

HCE support varies by device. If NFC communication doesn’t work, try different devices.
Some devices prioritize Secure Element communication (e.g., for payments) over HCE, which can interfere with the NFCParking app.

Future Enhancements

Backend Integration: Use Firebase to store and validate nfcTokens dynamically.
Enhanced NFC Ticket: Include additional details in the NFC ticket, such as slot number and timestamp.
Payment Integration: Add Stripe to the NFCParking app to require payment before generating the NFC ticket.
Security: Encrypt the nfcToken before sending it via NFC.

Contributing
Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.
License
This project is licensed under the MIT License.

package com.weitech.ar.arcamerademo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    private void initButtons() {
        int width = AppUtils.GetScreenWidth();
        int height = AppUtils.GetScreenHeight();
        int buttonWidth = (int) (width * 0.4);
        int buttonHeight = (int) (height * 0.075);
        ConstraintLayout layout = findViewById(R.id.main);
        {
            Button button = new Button(this);
            button.setText("AppTest");
            button.setX((int) (width * 0.3));
            button.setY((int) (height * 0.2));
            button.setOnClickListener(v -> startTestUnity());
            layout.addView(button, buttonWidth, buttonHeight);
        }
        {
            Button button = new Button(this);
            button.setText("AR Camera");
            button.setX((int) (width * 0.3));
            button.setY((int) (height * 0.4));
            button.setOnClickListener(v -> startCameraUnity());
            layout.addView(button, buttonWidth, buttonHeight);
        }
        {
            Button button = new Button(this);
            button.setText("AR Preview");
            button.setX((int) (width * 0.3));
            button.setY((int) (height * 0.6));
            button.setOnClickListener(v -> startPreviewUnity());
            layout.addView(button, buttonWidth, buttonHeight);
        }
    }

    private void startTestUnity() {
        Intent intent = new Intent(this, TestUnityActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

//    private boolean checkARSupport() throws Exception {
//        Log.i(TAG, "---checkARSupport---");
//        ArCoreApk.Availability isArSupported = ArCoreApk.getInstance().checkAvailability(this);
//        Log.i(TAG, "Availability: " + isArSupported);
//        if (isArSupported == ArCoreApk.Availability.SUPPORTED_INSTALLED) {
//            try {
//                new Session(this);
//            } catch (UnavailableDeviceNotCompatibleException e) {
//                Log.i(TAG, "UnavailableDeviceNotCompatibleException");
//                return false;
//            }
//            return true;
//        } else if (isArSupported == ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED) {
//            Log.i(TAG, "Not Installed!!!!");
//            ArCoreApk.InstallStatus status = ArCoreApk.getInstance().requestInstall(this, true);
//            Log.i(TAG, "Status: " + status);
//            return false;
//        } else if (isArSupported == ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD) {
//            Log.i(TAG, "ApkTooOld!!!!");
//            ArCoreApk.InstallStatus status = ArCoreApk.getInstance().requestInstall(this, true);
//            Log.i(TAG, "Status: " + status);
//            if (status == ArCoreApk.InstallStatus.INSTALLED) {
//                try {
//                    new Session(this);
//                } catch (UnavailableDeviceNotCompatibleException e) {
//                    Log.i(TAG, "UnavailableDeviceNotCompatibleException");
//                    return false;
//                }
//                return true;
//            } else if (status == ArCoreApk.InstallStatus.INSTALL_REQUESTED) {
//                return false;
//            }
//        }
//        return false;
//    }

    private void startCameraUnity() {
//        boolean isSupported = false;
//        try {
//            isSupported = checkARSupport();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (isSupported) {
        Intent intent = new Intent(this, ARCameraActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
//        } else {
//            Log.i(TAG, "Not Supported, Ignore");
//        }
    }

    private void startPreviewUnity() {
        Intent intent = new Intent(this, ARPreviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}
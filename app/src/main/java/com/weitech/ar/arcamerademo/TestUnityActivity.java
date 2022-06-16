package com.weitech.ar.arcamerademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayerActivity;
import com.weitech.ar.sdk.WTUnitySDK;
import com.weitech.ar.sdk.bridge.WTUnityCallNativeProxy;
import com.weitech.ar.sdk.bridge.WTUnityCallbackUtils;

import java.io.File;


public class TestUnityActivity extends UnityPlayerActivity implements WTUnityCallNativeProxy.WTUnityTestingCallbackListener {
    static final String TAG = "TestUnityActivity";

    WTUnitySDK unitySDK;
    File modelDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        modelDir = this.getExternalFilesDir("Models");
        if (!modelDir.exists()) {
            modelDir.mkdirs();
        }

        unitySDK = WTUnitySDK.SharedInstance();
        unitySDK.switchToScene("AppTest");

        addButtonsToUnityFrame();

        WTUnityCallbackUtils.getInstance().registerUnityTestingCallbackListener(this);
    }

    private void addButtonsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;
        int width = AppUtils.GetScreenWidth();
        int height = AppUtils.GetScreenHeight();
        int buttonWidth = (int) (width * 0.4);
        int buttonHeight = (int) (height * 0.08);

        {
            Button button = new Button(this);
            button.setText("Load Model");
            button.setX((int) (width * 0.5));
            button.setY((int) (height * 0.2));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sendUnityLoadModel();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }

        {
            Button button = new Button(this);
            button.setText("Load Mvx");
            button.setX((int) (width * 0.5));
            button.setY((int) (height * 0.3));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sendUnityLoadMvx();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }

        {
            Button button = new Button(this);
            button.setText("Send Unity Message");
            button.setX((int) (width * 0.1));
            button.setY((int) (height * 0.8));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sendUnityMessage();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }
    }

    @Override
    public void unityDidChangeCubeColor(String color) {
        Log.i(TAG, "Callback: unityDidChangeCubeColor");
        Log.i(TAG, color);
    }


    @Override
    public void unityDidChangeCubeScale(float xy, float z) {
        Log.i(TAG, "Callback: unityDidChangeCubeScaleXY");
        Log.i(TAG, xy + ", " + z);
    }

    private void sendUnityMessage() {
        Log.i(TAG, "sendUnityMessage");
        String[] colorArray = {"red", "blue", "yellow", "black"};
        int randomIndex = (int) (Math.random() * 4 % 4);
        unitySDK.SendUnityMessage("AppTest", "ChangeCubeColor", colorArray[randomIndex]);
    }

    private void sendUnityLoadModel() {
        String[] models = {"Parrot", "Flamingo", "Soldier", "Xbot", "Horse", "Stork"};
        int count = models.length;
        int randomIndex = (int) (Math.random() * count % count);

        String modelName = String.format("%s.glb", models[randomIndex]);
        File modelFile = new File(modelDir, modelName);
        unitySDK.SendUnityMessage("AppTest", "AddGltfModel", modelFile.toString());
    }

    private void sendUnityLoadMvx() {
        Log.i(TAG, "sendUnityLoadMvx");
        File mvxFile = new File(modelDir, "MVX/1.mvx");
        unitySDK.SendUnityMessage("AppTest", "AddMvxModel", mvxFile.toString());
    }
}

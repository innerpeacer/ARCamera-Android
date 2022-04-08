package com.weitech.ar.arcamerademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayerActivity;
import com.weitech.ar.sdk.WTUnitySDK;
import com.weitech.ar.sdk.bridge.WTUnityCallNativeProxy;


public class TestUnityActivity extends UnityPlayerActivity implements WTUnityCallNativeProxy.WTUnityTestingCallbackListener {
    static final String TAG = "TestUnityActivity";

    WTUnitySDK unitySDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unitySDK = WTUnitySDK.SharedInstance();
        unitySDK.init(mUnityPlayer);

        addButtonsToUnityFrame();

        WTUnityCallNativeProxy.getInstance().registerUnityTestingCallbackListener(this);
    }

    private void addButtonsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;

        {
            Button myButton = new Button(this);
            myButton.setText("ChangeColor");
            myButton.setX(320);
            myButton.setY(500);
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    unitySDK.ChangeCubeColor(Math.random() > 0.5 ? "red" : "blue");
                }
            });
            layout.addView(myButton, 300, 200);
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
    
}

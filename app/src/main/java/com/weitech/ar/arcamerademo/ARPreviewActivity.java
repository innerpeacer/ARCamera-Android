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


public class ARPreviewActivity extends UnityPlayerActivity implements WTUnityCallNativeProxy.WTUnitySceneControllerCallbackListener {
    static final String TAG = "ARPreviewActivity";

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
        WTUnityCallbackUtils.registerUnitySceneControllerCallbackListener(this);
        unitySDK.switchToScene("ARPreviewScene");

        addButtonsToUnityFrame();
    }

    private void addButtonsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;
        int buttonWidth = 500;
        int buttonHeight = 200;

        {
            Button button = new Button(this);
            button.setText("Preview 1");
            button.setX(500);
            button.setY(400);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    previewMvxModel1();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }

        {
            Button button = new Button(this);
            button.setText("Preview 2");
            button.setX(500);
            button.setY(700);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    previewMvxModel2();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }
    }


    @Override
    public void unityDidLoadScene(String sceneName) {
        Log.i(TAG, "======== unityDidLoadScene: " + sceneName);
        if (sceneName.equals("ARPreviewScene")) {
            unitySDK.setPreviewBackgroundColor(0.5f, 0.0f, 0.8f, 1.0f);
            unitySDK.setPreviewCameraRect(0, 0.2f, 1.0f, 0.6f);
            previewMvxModel1();
        }
    }


    @Override
    public void unityDidUnloadScene(String sceneName) {
        Log.i(TAG, "======== unityDidUnloadScene: " + sceneName);
    }


    private void previewMvxModel1() {
        Log.i(TAG, "previewMvxModel1");
        File mvxFile = new File(modelDir, "MVX/1.mvx");
        unitySDK.previewMantisVisionModel(mvxFile.toString());
        unitySDK.setPreviewCameraDistance(2);
    }

    private void previewMvxModel2() {
        Log.i(TAG, "previewMvxModel2");
        File mvxFile = new File(modelDir, "MVX/2.mvx");
        unitySDK.previewMantisVisionModel(mvxFile.toString());
        unitySDK.setPreviewCameraDistance(5);
    }
}

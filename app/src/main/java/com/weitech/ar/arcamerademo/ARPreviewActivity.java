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


public class ARPreviewActivity extends UnityPlayerActivity implements WTUnityCallNativeProxy.WTUnitySceneControllerCallbackListener, WTUnityCallNativeProxy.WTModelHandlingCallbackListener {
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
        WTUnityCallbackUtils.registerModelHandlingCallbackListener(this);
        addButtonsToUnityFrame();
    }

    private void addButtonsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;
        int width = AppUtils.GetScreenWidth();
        int height = AppUtils.GetScreenHeight();
        int buttonWidth = (int) (width * 0.5);
        int buttonHeight = (int) (height * 0.08);

        {
            Button button = new Button(this);
            button.setText("Preview 1");
            button.setX((int) (width * 0.4));
            button.setY((int) (height * 0.2));

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
            button.setX((int) (width * 0.4));
            button.setY((int) (height * 0.3));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    previewMvxModel2();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }
    }

    @Override
    public void unityDidLoadEntryScene() {
        unitySDK.switchToScene(WTUnitySDK.PREVIEW_SCENE);
    }

    @Override
    public void unityDidLoadExitScene() {

    }

    @Override
    public void unityDidLoadScene(String sceneName) {
        Log.i(TAG, "======== unityDidLoadScene: " + sceneName);
        if (sceneName.equals(WTUnitySDK.PREVIEW_SCENE)) {
//            unitySDK.setPreviewBackgroundColor(0.5f, 0.0f, 0.8f, 1.0f);
            unitySDK.setPreviewCameraRect(0, 0.1f, 1.0f, 0.8f);
            previewMvxModel1();
        }
    }


    @Override
    public void unityDidUnloadScene(String sceneName) {
        Log.i(TAG, "======== unityDidUnloadScene: " + sceneName);
    }


    private void previewMvxModel1() {
        Log.i(TAG, "previewMvxModel1");
        String fileName = "1";
        File modelFile = new File(modelDir, "MVX/" + fileName + ".mvx");
        File modelInfoFile = new File(modelDir, "MVX/" + fileName + ".json");
        unitySDK.previewModel(modelFile.toString(), modelInfoFile.toString());
    }

    private void previewMvxModel2() {
        Log.i(TAG, "previewMvxModel2");
        String fileName = "Flamingo";
        File modelFile = new File(modelDir, fileName + ".glb");
        File modelInfoFile = new File(modelDir, fileName + ".json");
        unitySDK.previewModel(modelFile.toString(), modelInfoFile.toString());
    }

    @Override
    public void unityDidFinishLoadingModel(int modelType, String modelPath) {
        Log.i(TAG, "Did Load Model: %@" + modelPath);
    }

    @Override
    public void unityDidFailedLoadingModel(int modelType, String modelPath, String description) {
        Log.i(TAG, "Failed Load Model: %@" + description);
    }

    @Override
    public void unityDidRemoveModel(int modelType, String modelID) {

    }

    @Override
    public void unityDidUnSelectModel(int modelType, String modelID) {

    }

    @Override
    public void unityDidSelectModel(int modelType, String modelID) {

    }

    @Override
    public void unityDidPlaceModel(int modelType, String modelID) {

    }

    @Override
    public void unityDidFailedRemovingModel(String modelID, String description) {

    }
}

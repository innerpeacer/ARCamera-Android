package com.weitech.ar.arcamerademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayerActivity;
import com.weitech.ar.sdk.WTModelInfo;
import com.weitech.ar.sdk.WTUnitySDK;
import com.weitech.ar.sdk.bridge.WTUnityCallNativeProxy;
import com.weitech.ar.sdk.bridge.WTUnityCallbackUtils;

import java.io.File;


public class ARPreviewActivity extends UnityPlayerActivity implements WTUnityCallNativeProxy.WTUnitySceneControllerCallbackListener, WTUnityCallNativeProxy.WTModelHandlingCallbackListener {
    static final String TAG = "ARPreviewActivity";

    WTUnitySDK unitySDK;
    File modelDir;
    WTModelInfo currentModelInfo;
    int testAnimationIndex;

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
            button.getBackground().setAlpha(100);
            button.setX((int) (width * 0.4));
            button.setY((int) (height * 0.2));

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    previewModel1();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }

        {
            Button button = new Button(this);
            button.setText("Preview 2");
            button.getBackground().setAlpha(100);
            button.setX((int) (width * 0.4));
            button.setY((int) (height * 0.3));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    previewModel2();
                }
            });
            layout.addView(button, buttonWidth, buttonHeight);
        }

        {
            Button button = new Button(this);
            button.setText("Play Animation");
            button.getBackground().setAlpha(100);
            button.setX((int) (width * 0.4));
            button.setY((int) (height * 0.6));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    playAnimation();
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
            previewModel1();
        }
    }


    @Override
    public void unityDidUnloadScene(String sceneName) {
        Log.i(TAG, "======== unityDidUnloadScene: " + sceneName);
    }


    private void playAnimation() {
        Log.i(TAG, "========= Play Animation");
        if (currentModelInfo == null)
            return;

        if (testAnimationIndex >= currentModelInfo.animation.clips.size()) {
            testAnimationIndex = 0;
        }

        WTModelInfo.WTAnimationClip clip = currentModelInfo.animation.clips.get(testAnimationIndex++);
        unitySDK.playPreviewAnimation(clip.clipName);
    }


    private void previewModel1() {
        Log.i(TAG, "previewMvxModel1");
        String fileName = "1";
        previewMVXModel(fileName);
    }

    private void previewModel2() {
        Log.i(TAG, "previewMvxModel2");
        if (Math.random() > 0.5) {
            String fileName = "Flamingo";
            previewGLBModel(fileName);
        } else {
            String fileName = "techgirl";
            previewWABModel(fileName);
        }
    }

    private void previewMVXModel(String modelName) {
        File modelFile = new File(modelDir, "MVX/" + modelName + ".mvx");
        File modelInfoFile = new File(modelDir, "MVX/" + modelName + ".json");
        currentModelInfo = WTModelInfo.FromFile(modelInfoFile.toString());
        unitySDK.previewModel(modelFile.toString(), modelInfoFile.toString());
    }

    private void previewGLBModel(String modelName) {
        File modelFile = new File(modelDir, "GLB/" + modelName + ".glb");
        File modelInfoFile = new File(modelDir, "GLB/" + modelName + ".json");
        currentModelInfo = WTModelInfo.FromFile(modelInfoFile.toString());
        unitySDK.previewModel(modelFile.toString(), modelInfoFile.toString());
    }

    private void previewWABModel(String modelName) {
        File modelFile = new File(modelDir, "WAB/" + modelName + ".wab");
        File modelInfoFile = new File(modelDir, "WAB/" + modelName + ".json");
        currentModelInfo = WTModelInfo.FromFile(modelInfoFile.toString());
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

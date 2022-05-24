package com.weitech.ar.arcamerademo;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.unity3d.player.UnityPlayerActivity;
import com.weitech.ar.sdk.WTUnitySDK;
import com.weitech.ar.sdk.bridge.WTUnityCallNativeProxy;
import com.weitech.ar.sdk.bridge.WTUnityCallbackUtils;

import java.io.File;


public class ARCameraActivity extends UnityPlayerActivity implements WTUnityCallNativeProxy.WTUnitySceneControllerCallbackListener, WTUnityCallNativeProxy.WTUnityShootingCallbackListener, WTUnityCallNativeProxy.WTModelHandlingCallbackListener {
    static final String TAG = "ARCameraActivity";

    WTUnitySDK unitySDK;
    File modelDir;


    LinearLayout shootingView;
    LinearLayout modelView;
    LinearLayout removeView;

    String selectedModelObjectID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        modelDir = this.getExternalFilesDir("Models");
        if (!modelDir.exists()) {
            modelDir.mkdirs();
        }

        unitySDK = WTUnitySDK.SharedInstance();
//
        addButtonsToUnityFrame();
//
        WTUnityCallbackUtils.registerUnitySceneControllerCallbackListener(this);
        WTUnityCallbackUtils.registerUnityShootingCallbackListener(this);
        WTUnityCallbackUtils.registerModelHandlingCallbackListener(this);
    }


    @Override
    public void unityDidLoadEntryScene() {
        unitySDK.switchToScene(WTUnitySDK.CAMERA_SCENE);
    }

    @Override
    public void unityDidLoadExitScene() {

    }

    @Override
    public void unityDidLoadScene(String sceneName) {
        if (sceneName.equals(WTUnitySDK.CAMERA_SCENE)) {
            unitySDK.setShootingParams(WTUnitySDK.WTShootingParams.HD);
        }
    }

    @Override
    public void unityDidUnloadScene(String sceneName) {

    }

    private void addButtonsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;

        int height = Resources.getSystem().getDisplayMetrics().heightPixels;

        {
            modelView = (LinearLayout) getLayoutInflater().inflate(R.layout.model_view, null);
            modelView.setY(height - 500);
            layout.addView(modelView);


            ImageButton modelButton = modelView.findViewById(R.id.modelButton);
            modelButton.setOnClickListener((view -> {
                UseCommon3DModel();
            }));

            ImageButton mvxButton = modelView.findViewById(R.id.mvxButton);
            mvxButton.setOnClickListener((view -> {
                UseMvxModel();
            }));
        }

        {
            shootingView = (LinearLayout) getLayoutInflater().inflate(R.layout.shooting_view, null);
            shootingView.setY(height - 500);
            layout.addView(shootingView);

            Button backButton = shootingView.findViewById(R.id.back_button);
            backButton.setOnClickListener((view -> {
                SwitchView();
            }));


            Button photoButton = shootingView.findViewById(R.id.photo_button);
            photoButton.setOnClickListener(view -> {
                TakePhoto();
            });

            Button startVideoButton = shootingView.findViewById(R.id.start_video_button);
            startVideoButton.setOnClickListener(view -> {
                StartRecordingVideo();
            });

            Button stopVideoButton = shootingView.findViewById(R.id.stop_video_button);
            stopVideoButton.setOnClickListener(view -> {
                StopRecordingVideo();
            });

            shootingView.setVisibility(View.INVISIBLE);
        }

        {
            removeView = (LinearLayout) getLayoutInflater().inflate(R.layout.remove_view, null);
            removeView.setY(500);
            removeView.setX(50);
            layout.addView(removeView);

            ImageButton removeButton = removeView.findViewById(R.id.removeButton);
            removeButton.setOnClickListener((view -> {
                RemoveModelObject();
            }));
//            ShowRemoveView(false);
        }
    }

    private void ShowRemoveView(boolean show) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "Show Remove View: " + show);
                removeView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    private void RemoveModelObject() {
        Log.i(TAG, "RemoveModelObject");
        unitySDK.removeModelObject(selectedModelObjectID);
    }

    private void SwitchView() {
        if (shootingView.getVisibility() == View.VISIBLE) {
            shootingView.setVisibility(View.INVISIBLE);
            modelView.setVisibility(View.VISIBLE);
        } else {
            shootingView.setVisibility(View.VISIBLE);
            modelView.setVisibility(View.INVISIBLE);
        }
    }

    private void UseCommon3DModel() {
        Log.i(TAG, "UseCommon3DModel");
        File modelFile = new File(modelDir, "Flamingo.glb");
        Log.i(TAG, modelFile.toString());
//        unitySDK.useCommon3DModel(modelFile.toString());
        unitySDK.useCommon3DModelAsync(modelFile.toString());
        SwitchView();
    }

    private void UseMvxModel() {
        Log.i(TAG, "UseMvxModel");
        File mvxFile = new File(modelDir, "MVX/1.mvx");
        Log.i(TAG, mvxFile.toString());
        unitySDK.useMantisVisionModel(mvxFile.toString());
        SwitchView();
    }

    private void TakePhoto() {
        Log.i(TAG, "TakePhoto");
        unitySDK.takePhoto("TestPhoto");
    }

    private void StartRecordingVideo() {
        Log.i(TAG, "StartRecordingVideo");
        unitySDK.startRecordingVideo("TestVideo");
    }

    private void StopRecordingVideo() {
        Log.i(TAG, "StopRecordingVideo");
        unitySDK.stopRecordingVideo();
    }

    @Override
    public void unityDidFinishPhotoing(String pID, String path) {
        Log.i(TAG, "unityDidFinishPhotoing: " + pID);
        Log.i(TAG, path);

        File imageFile = new File(path);
        Log.i(TAG, "Image Exist: " + imageFile.exists());
        Log.i(TAG, String.format("File Size: %.2f MB", imageFile.length() / 1024.0 / 1024.0));

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        Log.i(TAG, "Width-Height: " + imageWidth + "-" + imageHeight);
    }

    @Override
    public void unityDidStartRecording(String vID) {
        Log.i(TAG, "unityDidStartRecording: " + vID);
    }

    @Override
    public void unityDidFinishRecording(String vID, String path) {
        Log.i(TAG, "unityDidFinishRecording: " + vID);
        Log.i(TAG, path);

        File videoFile = new File(path);
        Log.i(TAG, "Video Exist: " + videoFile.exists());
        Log.i(TAG, String.format("File Size: %.2f MB", videoFile.length() / 1024.0 / 1024.0));
    }


    @Override
    public void unityDidFinishLoadingModel(int modelType, String modelPath) {
        Log.i(TAG, String.format("======== Did Load Model: %s", modelPath));

    }

    @Override
    public void unityDidFailedLoadingModel(int modelType, String modelPath, String description) {
        Log.i(TAG, String.format("======== Failed Load Model: %s", description));
    }

    @Override
    public void unityDidPlaceModel(int modelType, String modelID) {
        String type = (modelType == WTUnitySDK.WTModelType.MantisVisionHD.getValue()) ? "Mantis" : "3D";
        Log.i(TAG, String.format("======== Did Place %s Model: %s", type, modelID));
    }

    @Override
    public void unityDidSelectModel(int modelType, String modelID) {
        String type = (modelType == WTUnitySDK.WTModelType.MantisVisionHD.getValue()) ? "Mantis" : "3D";
        Log.i(TAG, String.format("======== Did Select %s Model: %s", type, modelID));
        selectedModelObjectID = modelID;
        ShowRemoveView(true);
    }

    @Override
    public void unityDidUnSelectModel(int modelType, String modelID) {
        String type = (modelType == WTUnitySDK.WTModelType.MantisVisionHD.getValue()) ? "Mantis" : "3D";
        Log.i(TAG, String.format("======== Did UnSelect %s Model: %s", type, modelID));
        selectedModelObjectID = null;
        ShowRemoveView(false);
    }

    @Override
    public void unityDidRemoveModel(int modelType, String modelID) {
        String type = (modelType == WTUnitySDK.WTModelType.MantisVisionHD.getValue()) ? "Mantis" : "3D";
        Log.i(TAG, String.format("======== Did Remove %s Model: %s", type, modelID));
    }

    @Override
    public void unityDidFailedRemovingModel(String modelID, String description) {
        Log.i(TAG, String.format("======== Failed Remove Model: %s", description));
    }
}

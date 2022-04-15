package com.weitech.ar.arcamerademo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.unity3d.player.UnityPlayerActivity;
import com.weitech.ar.sdk.WTUnitySDK;
import com.weitech.ar.sdk.bridge.WTUnityCallNativeProxy;
import com.weitech.ar.sdk.bridge.WTUnityCallbackUtils;

import java.io.File;


public class ARCameraActivity extends UnityPlayerActivity implements WTUnityCallNativeProxy.WTUnityShootingCallbackListener {
    static final String TAG = "ARCameraActivity";

    WTUnitySDK unitySDK;
    File modelDir;


    LinearLayout shootingView;
    LinearLayout modelView;

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
        WTUnityCallbackUtils.getInstance().registerUnityShootingCallbackListener(this);
        unitySDK.setShootingParams(WTUnitySDK.WTShootingParams.HD);
    }

    private void addButtonsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;

        WindowManager wm = getWindowManager();
        int height = wm.getCurrentWindowMetrics().getBounds().height();

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
        unitySDK.useCommon3DModel(modelFile.toString());
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
}

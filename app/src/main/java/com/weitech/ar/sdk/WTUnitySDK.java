package com.weitech.ar.sdk;

import com.unity3d.player.UnityPlayer;
import com.weitech.ar.sdk.bridge.WTUnitySystemEventProxy;
import com.weitech.ar.sdk.bridge.WTUnitySystemEventUtils;

import org.json.JSONObject;

public class WTUnitySDK implements WTUnitySystemEventProxy.WTUnitySystemEventCallbackListener {
    private static final String TAG = "WTUnitySDK";

    public enum WTShootingParams {
        HD,
        SD
    }

    public enum WTModelType {
        Common3D(1),
        MantisVisionHD(2),
//        MantisVisionSD(3)
        ;

        private int value;

        WTModelType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private static WTUnitySDK sharedInstance = new WTUnitySDK();

    private static final String SHARED_SCENE_MANAGER = "SharedSceneManager";
    private static final String AR_CAMERA_SCENE_CONTROLLER = "ARCameraSceneController";
    private static final String AR_CAMERA_PREVIEW_CONTROLLER = "ARPreviewSceneController";

    private WTUnitySDK() {
        WTUnitySystemEventUtils.registerUnitySystemEventCallbackListener(this);
    }

    public static WTUnitySDK SharedInstance() {
        return sharedInstance;
    }

    public void switchToScene(String sceneName) {
        UnityPlayer.UnitySendMessage(SHARED_SCENE_MANAGER, "SwitchScene", sceneName);
    }

    public void useMantisVisionModel(String modelPath) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "UseMvx", modelPath);
    }

    public void useCommon3DModel(String modelPath) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "UseModel", modelPath);
    }

    public void useCommon3DModelAsync(String modelPath) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "UseModelAsync", modelPath);
    }


    public void removeModelObject(String objectID) {
        if (objectID == null) objectID = "";
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "RemovePlacedModelObject", objectID);
    }


    public void setShootingParams(WTShootingParams params) {
        double photoSuperSize = 1;
        double videoSuperSize = 0.5;
        int videoFrameRate = 24;

        if (params == WTShootingParams.HD) {
            photoSuperSize = 2;
            videoSuperSize = 1;
            videoFrameRate = 24;
        }

        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "SetPhotoSuperSize", String.format("%f", photoSuperSize));
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "SetVideoSuperSize", String.format("%f", videoSuperSize));
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "SetVideoFrameRate", String.format("%d", videoFrameRate));
    }


    public void takePhoto(String pID) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "TakePhoto", pID);
    }

    public void startRecordingVideo(String vID) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "StartRecordingVideo", vID);
    }

    public void stopRecordingVideo() {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "StopRecordingVideo", "");
    }

    public void previewMantisVisionModel(String modelPath) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "PreviewMvxModel", modelPath);
    }

    public void previewCommon3DModel(String modelPath) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "PreviewCommon3DModel", modelPath);
    }

    public void previewModel(String modelPath, String modelInfoPath) {
        JSONObject json = new JSONObject();
        try {
            json.put("modelPath", modelPath);
            json.put("modelInfoPath", modelInfoPath);
        } catch (Exception e) {
        }
        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "PreviewModel", json.toString());
    }

    public void setPreviewCameraRect(float x, float y, float width, float height) {
        JSONObject json = new JSONObject();
        try {
            json.put("x", x);
            json.put("y", y);
            json.put("width", width);
            json.put("height", height);
        } catch (Exception e) {

        }
        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "SetPreviewRect", json.toString());
    }

//    public void setPreviewCameraDistance(float d) {
//        if (d <= 0) return;
//        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "SetPreviewCameraDistance", String.format("-%f", d));
//    }

//    public void setPreviewCameraField(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
//        JSONObject json = new JSONObject();
//        try {
//            json.put("xMin", xMin);
//            json.put("xMax", xMax);
//            json.put("yMin", yMin);
//            json.put("yMax", yMax);
//            json.put("zMin", zMin);
//            json.put("zMax", zMax);
//        } catch (Exception e) {
//
//        }
//        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "SetCameraField", json.toString());
//    }

    public void setPreviewBackgroundColor(float red, float green, float blue, float alpha) {
        JSONObject json = new JSONObject();
        try {
            json.put("r", red);
            json.put("g", green);
            json.put("b", blue);
            json.put("a", alpha);
        } catch (Exception e) {

        }
        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "SetBackgroundColor", json.toString());
    }

    public void ChangeCubeColor(String color) {
        UnityPlayer.UnitySendMessage("AppController", "ChangeCubeColor", color);
    }

    public void SendUnityMessage(String object, String methodName, String param) {
        UnityPlayer.UnitySendMessage(object, methodName, param);
    }

    @Override
    public void unitySystemEntrySceneDidLoad() {
//        Log.i(TAG, "unitySystemEntrySceneDidLoad");
    }

    @Override
    public void unitySystemExitSceneDidLoad() {
//        Log.i(TAG, "unitySystemExitSceneDidLoad");
    }
}

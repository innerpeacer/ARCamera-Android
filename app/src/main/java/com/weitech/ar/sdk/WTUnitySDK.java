package com.weitech.ar.sdk;

import com.unity3d.player.UnityPlayer;

import org.json.JSONObject;

public class WTUnitySDK {

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

    private static final String AR_CAMERA_SCENE_CONTROLLER = "ARCameraSceneController";
    private static final String AR_CAMERA_PREVIEW_CONTROLLER = "ARPreviewSceneController";

    private WTUnitySDK() {

    }

    public static WTUnitySDK SharedInstance() {
        return sharedInstance;
    }

    public void switchToScene(String sceneName) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "SwitchScene", sceneName);
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

    public void setPreviewCameraDistance(float d) {
        if (d <= 0) return;
        UnityPlayer.UnitySendMessage(AR_CAMERA_PREVIEW_CONTROLLER, "SetPreviewCameraDistance", String.format("-%f", d));
    }

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
}

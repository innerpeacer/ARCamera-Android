package com.weitech.ar.sdk;

import com.unity3d.player.UnityPlayer;

public class WTUnitySDK {

    private static WTUnitySDK sharedInstance = new WTUnitySDK();

    private static final String AR_CAMERA_SCENE_CONTROLLER = "ARCameraSceneController";

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

    public void takePhoto(String pID) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "TakePhoto", pID);
    }

    public void startRecordingVideo(String vID) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "StartRecordingVideo", vID);
    }

    public void stopRecordingVideo() {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "StopRecordingVideo", "");
    }

    public void ChangeCubeColor(String color) {
        UnityPlayer.UnitySendMessage("AppController", "ChangeCubeColor", color);
    }

    public void SendUnityMessage(String object, String methodName, String param) {
        UnityPlayer.UnitySendMessage(object, methodName, param);
    }
}

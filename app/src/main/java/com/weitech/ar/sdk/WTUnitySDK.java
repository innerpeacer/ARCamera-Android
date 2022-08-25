package com.weitech.ar.sdk;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import com.weitech.ar.sdk.bridge.WTUnitySystemEventProxy;
import com.weitech.ar.sdk.bridge.WTUnitySystemEventUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class WTUnitySDK implements WTUnitySystemEventProxy.WTUnitySystemEventCallbackListener {
    private static final String TAG = "WTUnitySDK";

    public static final String CAMERA_SCENE = "ARCameraScene";
    public static final String CAMERA_SCENE_HUAWEI = "ARCameraSceneForHuawei";
    public static final String CAMERA_SCENE_UNSUPPORTED = "ARCameraUnsupportedScene";
    public static final String PREVIEW_SCENE = "ARPreviewScene";

    public enum WTShootingParams {
        HD,
        SD
    }

    public enum WTModelType {
        Unknown(0),
        Common3D(1),
        MantisVisionHD(2),
        //        MantisVisionSD(3)
        AssetBundles(4);

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
    private static final String SHARED_BACKGROUND_MANAGER = "SharedBackgroundManager";
    private static final String AR_CAMERA_SCENE_CONTROLLER = "ARCameraSceneController";
    private static final String AR_CAMERA_UNSUPPORTED_SCENE_CONTROLLER = "ARCameraUnsupportedSceneController";
    private static final String AR_PREVIEW_SCENE_CONTROLLER = "ARPreviewSceneController";

    private WTUnitySDK() {
        WTUnitySystemEventUtils.registerUnitySystemEventCallbackListener(this);
    }

    public static WTUnitySDK SharedInstance() {
        return sharedInstance;
    }

    public void switchToScene(String sceneName) {
        UnityPlayer.UnitySendMessage(SHARED_SCENE_MANAGER, "SwitchScene", sceneName);
    }

    public void setGlobalBackgroundColor(float red, float green, float blue, float alpha) {
        JSONObject json = new JSONObject();
        try {
            json.put("r", red);
            json.put("g", green);
            json.put("b", blue);
            json.put("a", alpha);
        } catch (Exception e) {

        }
        UnityPlayer.UnitySendMessage(SHARED_BACKGROUND_MANAGER, "SetGlobalBackgroundColor", json.toString());
    }

    public void setGlobalBackgroundImage(String path) {
        if (path != null) {
            UnityPlayer.UnitySendMessage(SHARED_BACKGROUND_MANAGER, "SetGlobalBackgroundImage", path);
        }
    }

    public void useModel(String modelPath, String modelInfoPath) {
        JSONObject json = new JSONObject();
        try {
            json.put("modelPath", modelPath);
            json.put("modelInfoPath", modelInfoPath);
        } catch (Exception e) {
        }
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "UseModel", json.toString());
    }

    public void useModelAsync(String modelPath, String modelInfoPath) {
        JSONObject json = new JSONObject();
        try {
            json.put("modelPath", modelPath);
            json.put("modelInfoPath", modelInfoPath);
        } catch (Exception e) {
        }
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "UseModelAsync", json.toString());
    }


    public void removeModelObject(String objectID) {
        if (objectID == null) objectID = "";
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "RemovePlacedModelObject", objectID);
    }

    public void setEditModeWaitingInterval(float timeInterval) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "SetEditModeWaitingInterval", timeInterval + "");
    }

    public void playCameraAnimation(String clipName) {
        if (clipName != null) {
            UnityPlayer.UnitySendMessage(AR_CAMERA_SCENE_CONTROLLER, "PlayAnimation", clipName);
        }
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

    public void pauseCameraInUnsupported() {
        UnityPlayer.UnitySendMessage(AR_CAMERA_UNSUPPORTED_SCENE_CONTROLLER, "PauseCamera", "");
    }

    public void takePhotoInUnsupported(String pID) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_UNSUPPORTED_SCENE_CONTROLLER, "TakePhoto", pID);
    }

    public void startRecordingVideoInUnsupported(String vID) {
        UnityPlayer.UnitySendMessage(AR_CAMERA_UNSUPPORTED_SCENE_CONTROLLER, "StartRecordingVideo", vID);
    }

    public void stopRecordingVideoInUnsupported() {
        UnityPlayer.UnitySendMessage(AR_CAMERA_UNSUPPORTED_SCENE_CONTROLLER, "StopRecordingVideo", "");
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

    public void previewModel(String modelPath, String modelInfoPath) {
        JSONObject json = new JSONObject();
        try {
            json.put("modelPath", modelPath);
            json.put("modelInfoPath", modelInfoPath);
        } catch (Exception e) {
        }
        UnityPlayer.UnitySendMessage(AR_PREVIEW_SCENE_CONTROLLER, "PreviewModel", json.toString());
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
        UnityPlayer.UnitySendMessage(AR_PREVIEW_SCENE_CONTROLLER, "SetPreviewRect", json.toString());
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
        UnityPlayer.UnitySendMessage(AR_PREVIEW_SCENE_CONTROLLER, "SetBackgroundColor", json.toString());
    }

    public void setPreviewBackgroundImage(String path) {
        if (path != null) {
            UnityPlayer.UnitySendMessage(AR_PREVIEW_SCENE_CONTROLLER, "SetBackgroundImage", path);
        }
    }

    public void playPreviewAnimation(String clipName) {
        if (clipName != null) {
            UnityPlayer.UnitySendMessage(AR_PREVIEW_SCENE_CONTROLLER, "PlayAnimation", clipName);
        }
    }

    private void SetMvxFrameParams(String sceneName, float targetFPS, int skipFrame) {
        JSONObject json = new JSONObject();
        try {
            json.put("targetFPS", targetFPS);
            json.put("skipFrame", skipFrame);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UnityPlayer.UnitySendMessage(sceneName, "SetMvxFrameParams", json.toString());
    }

    public void setPreviewMvxFrameParams(float targetFPS, int skipFrame) {
        SetMvxFrameParams(AR_PREVIEW_SCENE_CONTROLLER, targetFPS, skipFrame);
    }


    public void setCameraMvxFrameParams(float targetFPS, int skipFrame) {
        SetMvxFrameParams(AR_CAMERA_SCENE_CONTROLLER, targetFPS, skipFrame);
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

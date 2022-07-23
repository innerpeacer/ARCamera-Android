//package com.weitech.ar.sdk.bridge;
//
//public class WTUnityCallbackUtils {
//    public static WTUnityCallbackUtils instance = new WTUnityCallbackUtils();
//
//    private WTUnityCallbackUtils() {
//
//    }
//
//    public static WTUnityCallbackUtils getInstance() {
//        return instance;
//    }
//
//    //  ---------------- WTUnitySceneControllerCallbackListener ----------------
//    private static WTUnityCallNativeProxy.WTUnitySceneControllerCallbackListener sceneControllerCallbackListener;
//
//    public static void registerUnitySceneControllerCallbackListener(WTUnityCallNativeProxy.WTUnitySceneControllerCallbackListener listener) {
//        sceneControllerCallbackListener = listener;
//    }
//
//    public static void unregisterUnitySceneControllerCallbackListener() {
//        sceneControllerCallbackListener = null;
//    }
//
//    void NotifyEntrySceneLoaded() {
//        if (sceneControllerCallbackListener != null) {
//            sceneControllerCallbackListener.unityDidLoadEntryScene();
//        }
//    }
//
//    void NotifyExitSceneLoaded() {
//        if (sceneControllerCallbackListener != null) {
//            sceneControllerCallbackListener.unityDidLoadExitScene();
//        }
//    }
//
//    void NotifySceneLoaded(String sceneName) {
//        if (sceneControllerCallbackListener != null) {
//            sceneControllerCallbackListener.unityDidLoadScene(sceneName);
//        }
//    }
//
//    void NotifySceneUnLoaded(String sceneName) {
//        if (sceneControllerCallbackListener != null) {
//            sceneControllerCallbackListener.unityDidUnloadScene(sceneName);
//        }
//    }
//
//    //  ---------------- WTUnityShootingCallbackListener ----------------
//    private static WTUnityCallNativeProxy.WTUnityShootingCallbackListener shootingCallbackListener;
//
//    public static void registerUnityShootingCallbackListener(WTUnityCallNativeProxy.WTUnityShootingCallbackListener listener) {
//        shootingCallbackListener = listener;
//    }
//
//    public static void unregisterUnityShootingCallbackListener() {
//        shootingCallbackListener = null;
//    }
//
//    void NotifyFinishPhotoing(String pID, String path) {
//        if (shootingCallbackListener != null) {
//            shootingCallbackListener.unityDidFinishPhotoing(pID, path);
//        }
//    }
//
//    void NotifyStartRecording(String vID) {
//        if (shootingCallbackListener != null) {
//            shootingCallbackListener.unityDidStartRecording(vID);
//        }
//    }
//
//    void NotifyFinishRecording(String vID, String path) {
//        if (shootingCallbackListener != null) {
//            shootingCallbackListener.unityDidFinishRecording(vID, path);
//        }
//    }
//
//    //  ---------------- WTModelHandlingCallbackListener ----------------
//    private static WTUnityCallNativeProxy.WTModelHandlingCallbackListener modelHandlingCallbackListener;
//
//    public static void registerModelHandlingCallbackListener(WTUnityCallNativeProxy.WTModelHandlingCallbackListener listener) {
//        modelHandlingCallbackListener = listener;
//    }
//
//    public static void unregisterModelHandlingCallbackListener() {
//        modelHandlingCallbackListener = null;
//    }
//
//    void NotifyFinishLoadingModel(int modelType, String modelPath, String modelInfoPath) {
//        if (modelHandlingCallbackListener != null) {
//            modelHandlingCallbackListener.unityDidFinishLoadingModel(modelType, modelPath, modelInfoPath);
//        }
//    }
//
//
//    void NotifyFailedLoadingModel(int modelType, String modelPath, String modelInfoPath, String description) {
//        if (modelHandlingCallbackListener != null) {
//            modelHandlingCallbackListener.unityDidFailedLoadingModel(modelType, modelPath, modelInfoPath, description);
//        }
//    }
//
//
//    void NotifyPlaceModel(int modelType, String modelID) {
//        if (modelHandlingCallbackListener != null) {
//            modelHandlingCallbackListener.unityDidPlaceModel(modelType, modelID);
//        }
//    }
//
//    void NotifySelectModel(int modelType, String modelID) {
//        if (modelHandlingCallbackListener != null) {
//            modelHandlingCallbackListener.unityDidSelectModel(modelType, modelID);
//        }
//    }
//
//    void NotifyUnSelectModel(int modelType, String modelID) {
//        if (modelHandlingCallbackListener != null) {
//            modelHandlingCallbackListener.unityDidUnSelectModel(modelType, modelID);
//        }
//    }
//
//    void NotifyRemoveModel(int modelType, String modelID) {
//        if (modelHandlingCallbackListener != null) {
//            modelHandlingCallbackListener.unityDidRemoveModel(modelType, modelID);
//        }
//    }
//
//    void NotifyFailedRemovingModel(String modelID, String description) {
//        if (modelHandlingCallbackListener != null) {
//            modelHandlingCallbackListener.unityDidFailedRemovingModel(modelID, description);
//        }
//    }
//
////  ---------------- WTUnityTestingCallbackListener ----------------
//
//    private static WTUnityCallNativeProxy.WTUnityTestingCallbackListener testingCallbackListener;
//
//    public static void registerUnityTestingCallbackListener(WTUnityCallNativeProxy.WTUnityTestingCallbackListener listener) {
//        testingCallbackListener = listener;
//    }
//
//
//    public static void unregisterUnityTestingCallbackListener() {
//        testingCallbackListener = null;
//    }
//
//    void NotifyCubeColorChanged(String color) {
//        if (testingCallbackListener != null) {
//            testingCallbackListener.unityDidChangeCubeColor(color);
//        }
//    }
//
//    void NotifyCubeScaleChanged(float xy, float z) {
//        if (testingCallbackListener != null) {
//            testingCallbackListener.unityDidChangeCubeScale(xy, z);
//        }
//    }
//}

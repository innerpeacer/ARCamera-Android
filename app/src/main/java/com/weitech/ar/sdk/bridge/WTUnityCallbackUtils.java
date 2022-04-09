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
//    void notifyFinishPhotoing(String pID, String path) {
//        if (shootingCallbackListener != null) {
//            shootingCallbackListener.unityDidFinishPhotoing(pID, path);
//        }
//    }
//
//    void notifyStartRecording(String vID) {
//        if (shootingCallbackListener != null) {
//            shootingCallbackListener.unityDidStartRecording(vID);
//        }
//    }
//
//    void notifyFinishRecording(String vID, String path) {
//        if (shootingCallbackListener != null) {
//            shootingCallbackListener.unityDidFinishRecording(vID, path);
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
//    void notifyCubeColorChanged(String color) {
//        if (testingCallbackListener != null) {
//            testingCallbackListener.unityDidChangeCubeColor(color);
//        }
//    }
//
//    void notifyCubeScaleChanged(float xy, float z) {
//        if (testingCallbackListener != null) {
//            testingCallbackListener.unityDidChangeCubeScale(xy, z);
//        }
//    }
//}

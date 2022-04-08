//package com.weitech.ar.sdk.bridge;
//
//public class WTUnityCallNativeProxy {
//    public static WTUnityCallNativeProxy instance = new WTUnityCallNativeProxy();
//
//    public static WTUnityCallNativeProxy getInstance() {
//        return instance;
//    }
//
//    private WTUnityCallNativeProxy() {
//
//    }
//
//    private WTUnityTestingCallbackListener testingCallbackListener;
//
//
//    public interface WTUnityTestingCallbackListener {
//        void unityDidChangeCubeScale(float xy, float z);
//
//        void unityDidChangeCubeColor(String color);
//    }
//
//    public void registerUnityTestingCallbackListener(WTUnityTestingCallbackListener listener) {
//        testingCallbackListener = listener;
//    }
//
//    public void unregisterUnityTestingCallbackListener() {
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
//

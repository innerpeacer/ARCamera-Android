//package com.weitech.ar.sdk.bridge;
//
//public class WTUnitySystemEventUtils {
//    public static WTUnitySystemEventUtils instance = new WTUnitySystemEventUtils();
//
//    private WTUnitySystemEventUtils() {
//
//    }
//
//    public static WTUnitySystemEventUtils getInstance() {
//        return instance;
//    }
//
//    private static WTUnitySystemEventProxy.WTUnitySystemEventCallbackListener systemEventCallbackListener;
//
//    public static void registerUnitySystemEventCallbackListener(WTUnitySystemEventProxy.WTUnitySystemEventCallbackListener listener) {
//        systemEventCallbackListener = listener;
//    }
//
//    public static void unregisterUnitySystemEventCallbackListener() {
//        systemEventCallbackListener = null;
//    }
//
//    void NotifySystemEntrySceneLoaded() {
//        if (systemEventCallbackListener != null) {
//            systemEventCallbackListener.unitySystemEntrySceneDidLoad();
//        }
//    }
//
//    void NotifySystemExitSceneLoaded() {
//        if (systemEventCallbackListener != null) {
//            systemEventCallbackListener.unitySystemExitSceneDidLoad();
//        }
//    }
//
//}

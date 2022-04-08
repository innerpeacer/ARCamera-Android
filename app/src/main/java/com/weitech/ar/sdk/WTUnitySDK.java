package com.weitech.ar.sdk;

import com.unity3d.player.UnityPlayer;

public class WTUnitySDK {

    private UnityPlayer unityPlayer;
    private static WTUnitySDK sharedInstance = new WTUnitySDK();

    private WTUnitySDK() {

    }

    public static WTUnitySDK SharedInstance() {
        return sharedInstance;
    }

    public void init(UnityPlayer player) {
        this.unityPlayer = player;
    }

    public void ChangeCubeColor(String color) {
        unityPlayer.UnitySendMessage("AppController", "ChangeCubeColor", color);

    }
}

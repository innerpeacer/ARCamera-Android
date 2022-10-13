package com.weitech.ar.sdk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WTModelInfo {

    public String modelName;
    public WTUnitySDK.WTModelType modelType;
    public WTAnimation animation;

    public static WTModelInfo FromFile(String path) {

        try {
            FileInputStream inStream = new FileInputStream(new File(path));
            InputStreamReader inputReader = new InputStreamReader(inStream);
            BufferedReader bufReader = new BufferedReader(inputReader);

            String line;
            StringBuilder jsonStr = new StringBuilder();
            while ((line = bufReader.readLine()) != null)
                jsonStr.append(line);

            JSONObject jsonObject = new JSONObject(jsonStr.toString());


            WTModelInfo modelInfo = new WTModelInfo();
            modelInfo.modelName = jsonObject.getString("name");

            String typeStr = jsonObject.getString("type");
            WTUnitySDK.WTModelType modelType = WTUnitySDK.WTModelType.Unknown;
            if (typeStr.equals("MantisHD")) {
                modelType = WTUnitySDK.WTModelType.MantisVisionHD;
            } else if (typeStr.equals("3D")) {
                modelType = WTUnitySDK.WTModelType.Common3D;
            } else if (typeStr.equals("WAB")) {
                modelType = WTUnitySDK.WTModelType.AssetBundles;
            } else if (typeStr.equals("Frame3D")) {
                modelType = WTUnitySDK.WTModelType.Frame3D;
            } else if (typeStr.equals("FrameWAB")) {
                modelType = WTUnitySDK.WTModelType.FrameAssetBundles;
            }
            modelInfo.modelType = modelType;

            JSONObject animationObject = jsonObject.getJSONObject("animations");
            if (animationObject != null && animationObject.has("clips")) {
                WTAnimation animation = new WTAnimation();
                JSONArray clipArray = animationObject.getJSONArray("clips");
                List<WTAnimationClip> clipList = new ArrayList<>();
                for (int i = 0; i < clipArray.length(); ++i) {
                    JSONObject clipObject = clipArray.getJSONObject(i);
                    WTAnimationClip clip = new WTAnimationClip(clipObject.getString("name"), clipObject.getString("clipName"));
                    clipList.add(clip);
                }
                animation.clips = clipList;

                if (animationObject.has("default")) {
                    animation.defaultClip = animation.findClip(animationObject.getString("default"));
                }
                modelInfo.animation = animation;
            }

            return modelInfo;
        } catch (Exception e) {

        }
        return null;
    }

    public static class WTAnimationClip {
        public String name;
        public String clipName;

        public WTAnimationClip(String name, String clipName) {
            this.name = name;
            this.clipName = clipName;
        }
    }

    public static class WTAnimation {
        public WTAnimationClip defaultClip;
        public List<WTAnimationClip> clips = new ArrayList<>();

        public WTAnimationClip findClip(String clipName) {
            for (WTAnimationClip clip : clips) {
                if (clip.name.equals(clipName)) {
                    return clip;
                }
            }
            return null;
        }
    }

}

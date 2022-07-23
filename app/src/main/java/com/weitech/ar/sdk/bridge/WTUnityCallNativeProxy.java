//package com.weitech.ar.sdk.bridge;
//
//public class WTUnityCallNativeProxy {
//
//    public interface WTUnityTestingCallbackListener {
//        void unityDidChangeCubeScale(float xy, float z);
//
//        void unityDidChangeCubeColor(String color);
//    }
//
//    public interface WTUnitySceneControllerCallbackListener {
//        void unityDidLoadEntryScene();
//
//        void unityDidLoadExitScene();
//
//        void unityDidLoadScene(String sceneName);
//
//        void unityDidUnloadScene(String sceneName);
//    }
//
//    public interface WTUnityShootingCallbackListener {
//        void unityDidFinishPhotoing(String pID, String path);
//
//        void unityDidStartRecording(String vID);
//
//        void unityDidFinishRecording(String vID, String path);
//    }
//
//    public interface WTModelHandlingCallbackListener {
//        void unityDidFinishLoadingModel(int modelType, String modelPath, String modelInfoPath);
//
//        void unityDidFailedLoadingModel(int modelType, String modelPath, String modelInfoPath, String description);
//
//        void unityDidPlaceModel(int modelType, String modelID);
//
//        void unityDidSelectModel(int modelType, String modelID);
//
//        void unityDidUnSelectModel(int modelType, String modelID);
//
//        void unityDidRemoveModel(int modelType, String modelID);
//
//        void unityDidFailedRemovingModel(String modelID, String description);
//    }
//}
//

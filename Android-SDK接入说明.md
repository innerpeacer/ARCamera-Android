# **AR元相机SDK接入说明**
********
## 一、项目地址

1、Unity项目
[https://git.crhlink.com/ar/arcamera](https://git.crhlink.com/ar/arcamera)

2、Android-SDK项目
[https://git.crhlink.com/ar/arcamera-android](https://git.crhlink.com/ar/arcamera-android)

Android-SDK项目包含SDK、Demo，接入时只需要添加com.weitech.ar.sdk包下代码。

本文档以一个AR相机的Unity场景为例，说明App+SDK对接方式，代码使用sdk-android分支。
具体相机API，后续陆续更新。

********
## 二、项目配置

1、加载Unity项目，选择ARCameraScene场景，切换Android平台，导出工程。

2、创建Android App工程，引入UnityLibrary，具体步骤可参考官方教程，这里暂不赘述。

********
## 三、SDK接入

### 1、SDK设置

Android平台可以直接继承UnityPlayerActivity，自行控制Overlay UI层，以及页面切换，这里暂不赘述。


### 2、App、Unity互调
统一术语，App调用Unity方法称为正向调用，Unity调用App方法称为回调。


#### （1） App调用Unity方法
通过WTUnitySDK类单例进行调用。
```
package com.weitech.ar.sdk;

import com.unity3d.player.UnityPlayer;

public class WTUnitySDK {

    public static WTUnitySDK SharedInstance();
    public void useMantisVisionModel(String modelPath);
    public void useCommon3DModel(String modelPath);
    public void takePhoto(String pID);
    public void startRecordingVideo(String vID);
    public void stopRecordingVideo();
    public void ChangeCubeColor(String color);
}

```

#### （2）Unity回调App方法
Unity回调目前主要通过WTUnityCallbackUtils注册监听方法实现。
通过
```
    public static void registerUnityShootingCallbackListener(WTUnityCallNativeProxy.WTUnityShootingCallbackListener listener);
```
监听相应的代理事件。代理方法将按业务模块进行分类。

SDK内声明如下，代参考：
```
package com.weitech.ar.sdk.bridge;

public class WTUnityCallNativeProxy {

    public interface WTUnityTestingCallbackListener {
        void unityDidChangeCubeScale(float xy, float z);
        void unityDidChangeCubeColor(String color);
    }

    public interface WTUnityShootingCallbackListener {
        void unityDidFinishPhotoing(String pID, String path);
        void unityDidStartRecording(String vID);
        void unityDidFinishRecording(String vID, String path);
    }
}
```


### 4、部分接口说明

#### （1） 模型加载

在相机页面，调用以下方法设置当前选用模型，参数为模型文件路径
```
    public void useMantisVisionModel(String modelPath); // 我的形象素材
    public void useCommon3DModel(String modelPath); // 热门3D素材
```


#### （2） 合拍功能
    
在相机页面，选定模型并放置完成后，调用以下方法。
    
##### 1、拍摄照片
```
    public void takePhoto(String pID);
``` 
pID自行定义，回调中对应返回。

```
    void unityDidFinishPhotoing(String pID, String path);
```
拍摄完成回调方法，返回拍摄传入pID，及照片存储路径。
默认路径为*/files/Photo，文件名格式photo_2022_4_7_16_13_6_977.png



##### 2、拍摄视频

```
    public void startRecordingVideo(String vID);
    public void stopRecordingVideo();
``` 
vID自行定义，回调中对应返回。


```
    void unityDidStartRecording(String vID);
    void unityDidFinishRecording(String vID, String path);
```
视频拍摄开始、及拍摄完成回调，返回拍摄传入vID，及视频存储路径。
默认路径为*/files/Video，文件名格式recording_2022_04_07_15_45_53_195.mp4


##### 3、拍摄参数

```
    public enum WTShootingParams {
        HD,
        SD
    }

    public void setShootingParams(WTShootingParams params);
```
目前支持高清(HD)与标清(SD)，默认为标清。

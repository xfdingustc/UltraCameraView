package com.xfdingustc.camera;

import android.content.Context;
import android.hardware.Camera;

/**
 * Created by Xiaofei on 2015/5/21.
 */
public class SimpleCameraHost implements UltraCameraHost {
  private Context mContext;
  private int mCameraId = -1;
  private DeviceProfile mDeviceProfile = null;

  public SimpleCameraHost(Context context) {
    this.mContext = context;
  }

  @Override
  public int getCameraId() {
    if (mCameraId == -1) {
      initCameraId();
    }

    return mCameraId;
  }

  private void initCameraId() {
    int ret = -1;
    int count = Camera.getNumberOfCameras();
    if (count > 0) {
      ret = 0;

      Camera.CameraInfo info = new Camera.CameraInfo();

      for (int i = 0; i < count; i++) {
        Camera.getCameraInfo(i, info);
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
          ret = i;
          break;
        }
      }
    }
    mCameraId = ret;
  }

  @Override
  public DeviceProfile getDeviceProfile() {
    if (mDeviceProfile == null) {
      initDeviceProfile(mContext);
    }
    return mDeviceProfile;
  }

  private void initDeviceProfile(Context mContext) {
    mDeviceProfile = DeviceProfile.getInstance();
  }

  @Override
  public void handleException(Exception e) {

  }
}

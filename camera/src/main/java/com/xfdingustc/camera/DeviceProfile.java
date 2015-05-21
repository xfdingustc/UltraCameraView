package com.xfdingustc.camera;

import android.content.Context;

/**
 * Created by Xiaofei on 2015/5/21.
 */
abstract public class DeviceProfile {
  abstract public boolean useSurfaceView();

  private static volatile DeviceProfile mSharedProfile = null;

  public static DeviceProfile getInstance() {
    if (mSharedProfile == null) {
      mSharedProfile = new SimpleDeviceProfile();
    }
    return mSharedProfile;
  }
}

package com.xfdingustc.camera;

import java.io.IOException;

/**
 * Created by Xiaofei on 2015/5/21.
 */
public interface UltraCameraHost {

  int getCameraId();

  DeviceProfile getDeviceProfile();

  void handleException(Exception e);
}

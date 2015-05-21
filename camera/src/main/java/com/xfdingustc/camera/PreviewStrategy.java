package com.xfdingustc.camera;

import android.hardware.Camera;
import android.view.View;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by Xiaofei on 2015/5/21.
 */
public interface PreviewStrategy {
  void attach(Camera camera) throws IOException;

  View getWidget();
}

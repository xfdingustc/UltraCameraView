package com.xfdingustc.camera;

import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

/**
 * Created by Xiaofei on 2015/5/21.
 */
public class SurfaceViewPreviewStrategy implements PreviewStrategy, SurfaceHolder.Callback {

  private UltraCameraView mCameraView = null;
  private SurfaceView mSurfaceView = null;
  private SurfaceHolder mSurfaceHolder;

  public SurfaceViewPreviewStrategy(UltraCameraView cameraView) {
    this.mCameraView = cameraView;
    mSurfaceView = new SurfaceView(mCameraView.getContext());
    mSurfaceHolder = mSurfaceView.getHolder();
    mSurfaceHolder.addCallback(this);
  }

  @Override
  public void attach(Camera camera) throws IOException {
    camera.setPreviewDisplay(mSurfaceHolder);
  }

  @Override
  public View getWidget() {
    return mSurfaceView;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    mCameraView.onPreviewCreated();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    mCameraView.initPreview(width, height);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    mCameraView.onPreviewDestroyed();
  }
}

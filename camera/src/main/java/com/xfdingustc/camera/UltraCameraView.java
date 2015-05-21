package com.xfdingustc.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by Xiaofei on 2015/5/14.
 */
public class UltraCameraView extends ViewGroup {
  private static final String TAG = "UltraCameraView";

  private Camera mCamera = null;
  private int mCameraId = -1;
  private UltraCameraHost mHost;
  private PreviewStrategy mPreviewStrategy;
  private boolean mInPreview = false;

  public UltraCameraView(Context context) {
    super(context);
  }

  public UltraCameraView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public UltraCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    if (context instanceof UltraCameraHostProvider) {
      UltraCameraHostProvider host_provider = (UltraCameraHostProvider)context;
      setHost(host_provider.getCameraHost());
    } else {
      throw new IllegalArgumentException(" Please make your activity implement " +
          "UltraCameraHostProvider interface!!!");
    }
  }

  public UltraCameraHost getHost() {
    return mHost;
  }

  public void setHost(UltraCameraHost host) {
    this.mHost = host;

    if (mHost.getDeviceProfile().useSurfaceView()) {
      mPreviewStrategy = new SurfaceViewPreviewStrategy(this);
    }
  }


  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
  public void onResume() {
    addView(mPreviewStrategy.getWidget());
    if (mCamera == null) {
      mCameraId = getHost().getCameraId();

      if (mCameraId >= 0) {
        Log.i(TAG, "Open camera id: " + mCameraId);
        mCamera = Camera.open(mCameraId);
      }


    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    Log.i(TAG, "onLayout changed = " + changed);
    if (changed && getChildCount() > 0) {
      Log.i(TAG, "onLayout 2");
      final View child = getChildAt(0);
      final int width = r - l;
      final int height = b - t;
      int preview_width = width;
      int preview_height = height;

      final int scaledChildHeight = preview_height * width / preview_width;
      child.layout(0, (height - scaledChildHeight) / 2, width, (height + scaledChildHeight) /2 );
    }
  }

  public void onPreviewCreated() {
    if (mCamera != null) {
      try {
        mPreviewStrategy.attach(mCamera);
      } catch (IOException e) {
        getHost().handleException(e);
      }
    }
  }

  public void initPreview(int width, int height) {
    initPreview(width, height, true);

  }

  private void initPreview(int width, int height, boolean first_run) {
    if (mCamera != null) {
      Camera.Parameters paraments = mCamera.getParameters();
      paraments.setPreviewSize(width, height);
      mCamera.setParameters(paraments);

      startPreview();
    }
  }

  private void startPreview() {
    mCamera.startPreview();
    mInPreview = true;
  }

  public void onPreviewDestroyed() {

  }
}

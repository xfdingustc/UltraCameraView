package com.xfdingustc.ultracameraview;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xfdingustc.camera.SimpleCameraHost;
import com.xfdingustc.camera.UltraCameraHost;
import com.xfdingustc.camera.UltraCameraHostProvider;
import com.xfdingustc.camera.UltraCameraView;


public class MainActivity extends ActionBarActivity implements UltraCameraHostProvider{
  private UltraCameraView mCameraview;
  private UltraCameraHost mCameraHost;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initViews();
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onResume() {
    super.onResume();
    mCameraview.onResume();
  }

  private void initViews() {
    setContentView(R.layout.activity_main);
    mCameraview = (UltraCameraView)findViewById(R.id.ultra_camera_view);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public UltraCameraHost getCameraHost() {
    return new MyCameraHost(this);
  }

  private class MyCameraHost extends SimpleCameraHost {
    public MyCameraHost(Context context) {
      super(context);
    }

  }
}

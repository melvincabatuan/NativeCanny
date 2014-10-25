package com.cabatuan.cannynative;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Main extends Activity {

    private CameraPreview camPreview;
    private ImageView MyCameraPreview = null;
    private RelativeLayout mainLayout;
    private int PreviewSizeWidth = 0;
    private int PreviewSizeHeight = 0;
    private int offset = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main);

        MyCameraPreview = new ImageView(this);
        SurfaceView camView = new SurfaceView(this);
        SurfaceHolder camHolder = camView.getHolder();
        mainLayout = (RelativeLayout) findViewById(R.id.container);

        getDisplaySize();
        camPreview = new CameraPreview(PreviewSizeWidth, PreviewSizeHeight, MyCameraPreview);
        camHolder.addCallback(camPreview);

        FrameLayout.LayoutParams parameters = new FrameLayout.LayoutParams(
                PreviewSizeWidth, PreviewSizeHeight);
        mainLayout.addView(camView, parameters);
        mainLayout.addView(MyCameraPreview, parameters);
    }

    private void getDisplaySize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        PreviewSizeHeight = metrics.heightPixels - offset;
        PreviewSizeWidth = metrics.widthPixels - offset;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

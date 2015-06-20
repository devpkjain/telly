package com.devpkjain.telly.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.devpkjain.telly.R;

public class SplashActivity extends Activity {

  private static int SPLASH_TIME_OUT = 3000;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_splash);
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        Intent intent = new Intent(SplashActivity.this, TrendingActivity.class);
        startActivity(intent);
        finish();
      }
    }, SPLASH_TIME_OUT);
  }
}

package com.devpkjain.telly.activity;

import android.app.Activity;
import android.os.Bundle;
import com.devpkjain.telly.R;

public class FullScheduleActivity extends Activity {

  private static int SPLASH_TIME_OUT = 3000;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_splash);
  }
}

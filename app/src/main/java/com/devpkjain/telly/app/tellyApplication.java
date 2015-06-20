package com.devpkjain.telly.app;

import android.app.Application;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class tellyApplication extends Application {
  @Override public void onCreate() {
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/PunkRockShow.ttf").build());
  }
}

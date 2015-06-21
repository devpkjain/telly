package com.devpkjain.telly.service.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentTVShowEnvelope implements Parcelable {
  public int num;
  public String img;
  public String lnk;
  public String name;
  public String air;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(num);
    dest.writeString(img);
    dest.writeString(lnk);
    dest.writeString(name);
    dest.writeString(air);
  }
}
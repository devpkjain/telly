package com.devpkjain.telly.service.vo;

import com.google.gson.annotations.SerializedName;

public class CurrentTVShowEnvelope {
  @SerializedName("num") public int num;
  @SerializedName("img") public String img;
  @SerializedName("lnk") public String lnk;
  @SerializedName("name") public String name;
  @SerializedName("air") public String air;
}
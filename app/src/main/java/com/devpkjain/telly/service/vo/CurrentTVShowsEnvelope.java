package com.devpkjain.telly.service.vo;

import com.google.gson.annotations.SerializedName;

public class CurrentTVShowsEnvelope {
  @SerializedName("num") public String num;
  @SerializedName("img") public String img;
  @SerializedName("lnk") public String lnk;
  @SerializedName("name") public String name;
  @SerializedName("air") public String air;
}
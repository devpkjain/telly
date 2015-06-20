package com.devpkjain.telly.model;

public class CurrentTVShow {
  private int num;
  private String img;
  private String lnk;
  private String name;
  private String air;

  public CurrentTVShow(int num, String img, String lnk, String name, String air) {
    this.num = num;
    this.img = img;
    this.lnk = lnk;
    this.name = name;
    this.air = air;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getLnk() {
    return lnk;
  }

  public void setLnk(String lnk) {
    this.lnk = lnk;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAir() {
    return air;
  }

  public void setAir(String air) {
    this.air = air;
  }
}
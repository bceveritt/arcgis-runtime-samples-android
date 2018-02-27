package com.esri.arcgisruntime.sampleviewer;

import android.graphics.Bitmap;

public class Category {

  private String mTitle;
  private Bitmap mIcon;
  private Bitmap mBackground;

  public Category(String title, Bitmap icon, Bitmap background) {
    this.mTitle = title;
    this.mIcon = icon;
    this.mBackground = background;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    this.mTitle = title;
  }

  public Bitmap getIcon() {
    return mIcon;
  }

  public void setIcon(Bitmap icon) {
    this.mIcon = icon;
  }

  public Bitmap getBackground() {
    return mBackground;
  }

  public void setBackground(Bitmap background) {
    this.mBackground = background;
  }
}

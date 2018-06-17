package com.ducdungdam.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable{

  private final int id;
  private final String shortDescription;
  private final String description;
  private final String videoURL;
  private final String thumbnailURL;

  private Step(Parcel in) {
    id = in.readInt();
    shortDescription = in.readString();
    description = in.readString();
    videoURL = in.readString();
    thumbnailURL = in.readString();
  }

  public static final Creator<Step> CREATOR = new Creator<Step>() {
    @Override
    public Step createFromParcel(Parcel in) {
      return new Step(in);
    }

    @Override
    public Step[] newArray(int size) {
      return new Step[size];
    }
  };

  public int getId() {
    return id;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getDescription() {
    return description;
  }

  public String getVideoURL() {
    return videoURL;
  }

  public String getThumbnailURL() {
    return thumbnailURL;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(shortDescription);
    dest.writeString(description);
    dest.writeString(videoURL);
    dest.writeString(thumbnailURL);
  }
}

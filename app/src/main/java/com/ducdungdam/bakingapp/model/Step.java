package com.ducdungdam.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 *
 * POJO of Recipe retrieving by {@link com.ducdungdam.bakingapp.data.RecipeRepository}
 */

public class Step implements Parcelable{

  private final int id;
  private final String shortDescription;
  private final String description;
  @SerializedName("videoURL")
  private final String videoUrl;
  @SerializedName("thumbnailURL")
  private final String thumbnailUrl;

  private Step(Parcel in) {
    id = in.readInt();
    shortDescription = in.readString();
    description = in.readString();
    videoUrl = in.readString();
    thumbnailUrl = in.readString();
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

  public String getVideoUrl() {
    return videoUrl;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
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
    dest.writeString(videoUrl);
    dest.writeString(thumbnailUrl);
  }
}

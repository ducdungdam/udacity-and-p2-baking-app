package com.ducdungdam.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {

  private final float quantity;
  private final String measure;
  @SerializedName("ingredient")
  private final String name;

  private Ingredient(Parcel in) {
    quantity = in.readFloat();
    measure = in.readString();
    name = in.readString();
  }

  public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
    @Override
    public Ingredient createFromParcel(Parcel in) {
      return new Ingredient(in);
    }

    @Override
    public Ingredient[] newArray(int size) {
      return new Ingredient[size];
    }
  };

  public float getQuantity() {
    return quantity;
  }

  public String getMeasure() {
    return measure;
  }

  public String getName() {
    return name;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeFloat(quantity);
    dest.writeString(measure);
    dest.writeString(name);
  }
}

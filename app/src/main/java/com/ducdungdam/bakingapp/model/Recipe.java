package com.ducdungdam.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducdungdam on 29.05.2018.
 *
 * POJO of Recipe retrieving by {@link com.ducdungdam.bakingapp.data.RecipeRepository}
 */

public class Recipe implements Parcelable{

  private final int id;
  private final String name;
  private final List<Ingredient> ingredients;
  private final List<Step> steps;
  private final int servings;
  private final String image;

  private Recipe(Parcel in) {
    id = in.readInt();
    name = in.readString();
    if (in.readByte() == 0x01) {
      ingredients = new ArrayList<>();
      in.readList(ingredients, Ingredient.class.getClassLoader());
    } else {
      ingredients = null;
    }
    if (in.readByte() == 0x01) {
      steps = new ArrayList<>();
      in.readList(steps, Step.class.getClassLoader());
    } else {
      steps = null;
    }
    servings = in.readInt();
    image = in.readString();
  }

  public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
    @Override
    public Recipe createFromParcel(Parcel in) {
      return new Recipe(in);
    }

    @Override
    public Recipe[] newArray(int size) {
      return new Recipe[size];
    }
  };

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public List<Step> getSteps() {
    return steps;
  }

  public Integer getServings() {
    return servings;
  }

  public String getImage() {
    return image;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
    if (ingredients == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeList(ingredients);
    }
    if (steps == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeList(steps);
    }
    dest.writeInt(servings);
    dest.writeString(image);
  }
}

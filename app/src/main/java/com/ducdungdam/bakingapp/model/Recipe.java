package com.ducdungdam.bakingapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by ducdungdam on 29.05.2018.
 *
 * POJO of Recipe retrieving by {@link com.ducdungdam.bakingapp.data.RecipeRepository}
 */

public class Recipe {

  private int id;
  private String name;
  private List<Ingredient> ingredients = null;
  private List<Step> steps = null;
  private Integer servings;
  private String image;

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

  class Ingredient {

    private float quantity;
    private String measure;
    @SerializedName("ingredient")
    private String name;

    public float getQuantity() {
      return quantity;
    }

    public String getMeasure() {
      return measure;
    }

    public String getName() {
      return name;
    }
  }

  class Step {

    private Integer id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Integer getId() {
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
  }
}

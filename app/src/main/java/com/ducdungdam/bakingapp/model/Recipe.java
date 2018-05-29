package com.ducdungdam.bakingapp.model;

/**
 *
 * Created by ducdungdam on 29.05.2018.
 *
 * POJO of Recipe retrieving by {@link com.ducdungdam.bakingapp.data.RecipeRepository}
 *
 */

public class Recipe {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
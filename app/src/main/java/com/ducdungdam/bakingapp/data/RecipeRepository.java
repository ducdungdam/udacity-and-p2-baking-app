package com.ducdungdam.bakingapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.ducdungdam.bakingapp.model.Recipe;
import java.util.List;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by ducdungdam on 29.05.2018.
 *
 * Repository to handles all necessary data retrieving processes
 */

public class RecipeRepository {

  private static final String TAG = RecipeRepository.class.getSimpleName();

  private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
  private static Retrofit retrofit = null;

  private interface RecipeService {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
  }

  private static Retrofit getRetrofitClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .callbackExecutor(Executors.newSingleThreadExecutor())
          .build();
    }
    return retrofit;
  }

  public static LiveData<List<Recipe>> getRecipes() {
    final MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();

    Call<List<Recipe>> call = getRetrofitClient().create(RecipeService.class)
        .getRecipes();
    call.enqueue(new Callback<List<Recipe>>() {
      @Override
      public void onResponse(@NonNull Call<List<Recipe>> call,
          @NonNull Response<List<Recipe>> response) {
        recipes.postValue(response.body());
      }

      @Override
      public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
      }
    });
    return recipes;
  }
}

package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.adapter.IngredientDetailAdapter;
import com.ducdungdam.bakingapp.databinding.FragmentDetailIngredientsBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.viewmodel.DetailViewModel;
import com.ducdungdam.bakingapp.widget.RecipeItemDecoration;

public class DetailIngredientsFragment extends Fragment {

  FragmentDetailIngredientsBinding rootView;

  public DetailIngredientsFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    DetailViewModel model = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);

    rootView = DataBindingUtil.inflate(
        inflater, R.layout.fragment_detail_ingredients, container, false);

    model.getRecipe().observe(this, new Observer<Recipe>() {
      @Override
      public void onChanged(@Nullable Recipe recipe) {
        IngredientDetailAdapter adapter = new IngredientDetailAdapter(
            recipe.getIngredients());
        rootView.rvIngredientList.setAdapter(adapter);
        rootView.rvIngredientList.addItemDecoration(new RecipeItemDecoration(getContext()));
      }
    });

    return rootView.getRoot();
  }
}

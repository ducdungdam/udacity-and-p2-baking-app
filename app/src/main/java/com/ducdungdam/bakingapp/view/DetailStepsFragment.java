package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.adapter.StepsDetailAdapter;
import com.ducdungdam.bakingapp.adapter.StepsDetailAdapter.OnClickListener;
import com.ducdungdam.bakingapp.databinding.FragmentDetailStepsBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.model.Step;
import com.ducdungdam.bakingapp.viewmodel.DetailViewModel;
import com.ducdungdam.bakingapp.widgets.StepItemDecoration;
import java.util.List;

public class DetailStepsFragment extends Fragment implements OnClickListener {

  private FragmentDetailStepsBinding rootView;
  private OnStepClickListener onStepClickListener;


  public DetailStepsFragment() {
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      onStepClickListener = (OnStepClickListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + "must implement OnStepClickListener");
    }
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    if (getActivity() == null) {
      return null;
    }

    final DetailViewModel model = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);

    rootView = DataBindingUtil.inflate(
        inflater, R.layout.fragment_detail_steps, container, false);

    model.getRecipe().observe(this, new Observer<Recipe>() {
      @Override
      public void onChanged(@Nullable Recipe recipe) {
        StepsDetailAdapter adapter = new StepsDetailAdapter(
            recipe != null ? recipe.getSteps() : null);
        adapter.setOnClickListener(DetailStepsFragment.this);
        rootView.rvStepsList.setAdapter(adapter);
        rootView.rvStepsList.addItemDecoration(new StepItemDecoration(getActivity()));
      }
    });

    return rootView.getRoot();
  }

  @Override
  public void onClick(Step step, int position) {
    if (onStepClickListener != null) {
      onStepClickListener
          .onStepClick(((StepsDetailAdapter) rootView.rvStepsList.getAdapter()).getSteps(),
              position);
    }
  }

  public void setOnStepClickListener(OnStepClickListener l) {
    onStepClickListener = l;
  }

  public interface OnStepClickListener {

    void onStepClick(List<Step> steps, int position);
  }
}

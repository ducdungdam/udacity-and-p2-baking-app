package com.ducdungdam.bakingapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.ViewDetailStepItemBinding;
import com.ducdungdam.bakingapp.model.Step;
import java.util.List;

/**
 * Adapter for List of Steps in {@link com.ducdungdam.bakingapp.view.DetailActivity}
 */

public class StepsDetailAdapter extends RecyclerView.Adapter<ViewHolder> {

  private final List<Step> steps;
  private OnClickListener onClickListener;

  public StepsDetailAdapter(List<Step> steps) {
    super();
    this.steps = steps;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new StepViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_detail_step_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ((StepViewHolder) holder).bind(steps.get(position));
  }

  @Override
  public int getItemCount() {
    if (steps == null) {
      return 0;
    }
    return steps.size();
  }

  public void setOnClickListener(OnClickListener l) {
    onClickListener = l;
  }

  public List<Step> getSteps() {
    return steps;
  }

  public interface OnClickListener {
    void onClick(Step step, int position);
  }

  private class StepViewHolder extends ViewHolder implements View.OnClickListener {

    final ViewDetailStepItemBinding rootView;

    StepViewHolder(View itemView) {
      super(itemView);
      rootView = DataBindingUtil.bind(itemView);
    }

    private void bind(Step step) {
      rootView.setStep(step);
      rootView.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if (onClickListener != null) {
        onClickListener.onClick(rootView.getStep(), getAdapterPosition());
      }
    }
  }
}

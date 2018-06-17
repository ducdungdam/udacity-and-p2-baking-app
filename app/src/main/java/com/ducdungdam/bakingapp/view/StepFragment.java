package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.FragmentStepBinding;
import com.ducdungdam.bakingapp.model.Step;
import com.ducdungdam.bakingapp.viewmodel.DetailViewModel;
import com.ducdungdam.bakingapp.viewmodel.StepViewModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepFragment extends Fragment implements Player.EventListener {

  private FragmentStepBinding rootView;
  private Context context;


  private SimpleExoPlayer exoPlayer;

  public StepFragment() {
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    if (getActivity() == null) {
      return null;
    }

    context = getContext();

    rootView = DataBindingUtil.inflate(
        inflater, R.layout.fragment_step, container, false);

    final DetailViewModel detailModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);
    detailModel.getCurrentPosition().observe(this, new Observer<Integer>() {
      @Override
      public void onChanged(@Nullable Integer currentPosition) {
        if (detailModel.getSteps().getValue() == null || currentPosition == null
            || detailModel.getSteps().getValue().get(currentPosition) == null) {
          return;
        }

        Step step = detailModel.getSteps().getValue().get(currentPosition);
        rootView.setStep(step);

        releasePlayer();
        if (step.getVideoURL() != null && !step.getVideoURL().isEmpty()) {
          initializePlayer(Uri.parse(step.getVideoURL()));
        }
      }
    });

    final StepViewModel stepModel = ViewModelProviders.of(getActivity()).get(StepViewModel.class);
    stepModel.getCurrentPosition().observe(this, new Observer<Integer>() {
      @Override
      public void onChanged(@Nullable Integer currentPosition) {
        if (stepModel.getSteps().getValue() == null || currentPosition == null
            || stepModel.getSteps().getValue().get(currentPosition) == null) {
          return;
        }

        Step step = stepModel.getSteps().getValue().get(currentPosition);
        rootView.setStep(step);

        releasePlayer();
        if (step.getVideoURL() != null && !step.getVideoURL().isEmpty()) {
          initializePlayer(Uri.parse(step.getVideoURL()));
        }
      }
    });

    return rootView.getRoot();
  }

  private void initializePlayer(Uri mediaUri) {
    if (exoPlayer == null) {
      // Create an instance of the ExoPlayer.
      TrackSelector trackSelector = new DefaultTrackSelector();
      LoadControl loadControl = new DefaultLoadControl();
      exoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()), trackSelector, loadControl);
      rootView.playerView.setPlayer(exoPlayer);

      // Set the ExoPlayer.EventListener to this activity.
      exoPlayer.addListener(this);

      // Prepare the MediaSource.
      String userAgent = Util.getUserAgent(getContext(), "BakingApp");

      DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(context, userAgent);
      MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(mediaUri);
      exoPlayer.prepare(mediaSource);
      exoPlayer.setPlayWhenReady(false);
    }
  }

  private void releasePlayer() {
    if (exoPlayer != null) {
      exoPlayer.stop();
      exoPlayer.release();
      exoPlayer = null;
    }
  }

  @Override
  public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
  }

  @Override
  public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
  }

  @Override
  public void onLoadingChanged(boolean isLoading) {
  }

  @Override
  public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
  }

  @Override
  public void onRepeatModeChanged(int repeatMode) {
  }

  @Override
  public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
  }

  @Override
  public void onPlayerError(ExoPlaybackException error) {
  }

  @Override
  public void onPositionDiscontinuity(int reason) {
  }

  @Override
  public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
  }

  @Override
  public void onSeekProcessed() {
  }

  @Override
  public void onDestroyView() {
    releasePlayer();
    super.onDestroyView();
  }
}

package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.FragmentStepBinding;
import com.ducdungdam.bakingapp.model.Step;
import com.ducdungdam.bakingapp.viewmodel.DetailViewModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepFragment extends Fragment implements ExoPlayer.EventListener {

  FragmentStepBinding rootView;


  private SimpleExoPlayer exoPlayer;

  public StepFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    rootView = DataBindingUtil.inflate(
        inflater, R.layout.fragment_step, container, false);


    final DetailViewModel viewModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);
    viewModel.getCurrentPosition().observe(this, new Observer<Integer>() {
      @Override
      public void onChanged(@Nullable Integer currentPosition) {
        if (viewModel.getSteps().getValue() == null || currentPosition == null
            || viewModel.getSteps().getValue().get(currentPosition) == null) {
          return;
        }

        Step step = viewModel.getSteps().getValue().get(currentPosition);
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
      exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
      rootView.playerView.setPlayer(exoPlayer);

      // Set the ExoPlayer.EventListener to this activity.
      exoPlayer.addListener(this);

      // Prepare the MediaSource.
      String userAgent = Util.getUserAgent(getContext(), "BakingApp");
      MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
          getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
      exoPlayer.prepare(mediaSource);
      exoPlayer.setPlayWhenReady(false);
    }
  }

  private void releasePlayer() {
    if (exoPlayer != null) {
      Log.d("DUNG", "releasePlayer: ");
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

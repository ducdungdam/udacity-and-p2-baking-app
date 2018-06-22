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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.FragmentStepBinding;
import com.ducdungdam.bakingapp.model.Step;
import com.ducdungdam.bakingapp.viewmodel.DetailViewModel;
import com.ducdungdam.bakingapp.viewmodel.StepViewModel;
import com.google.android.exoplayer2.C;
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

  public final static String KEY_AUTO_PLAY = "key_auto_play";
  public final static String KEY_POSITION = "key_position";
  public final static String KEY_WINDOW = "key_window";

  private FragmentStepBinding rootView;
  private Context context;

  private Step step;

  private SimpleExoPlayer exoPlayer;
  private boolean startPlaying = false;
  private long startPosition = 0;
  private int startWindow = 0;

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

    final DetailViewModel detailModel = ViewModelProviders.of(getActivity())
        .get(DetailViewModel.class);

    step = detailModel.getStep();

    detailModel.getCurrentPosition().observe(this, new Observer<Integer>() {
      @Override
      public void onChanged(@Nullable Integer currentPosition) {
        if (detailModel.getSteps().getValue() == null || currentPosition == null
            || detailModel.getSteps().getValue().get(currentPosition) == null) {
          return;
        }

        step = detailModel.getSteps().getValue().get(currentPosition);
        rootView.setStep(step);
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

        step = stepModel.getSteps().getValue().get(currentPosition);
        rootView.setStep(step);
      }
    });

    if (savedInstanceState != null) {
      startPlaying = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
      startPosition = savedInstanceState.getLong(KEY_POSITION);
      startWindow = savedInstanceState.getInt(KEY_WINDOW);
    }

    return rootView.getRoot();
  }

  private void initializePlayer() {
    if (exoPlayer == null &&  step != null && TextUtils.isEmpty(step.getVideoUrl())) {
      // Create an instance of the ExoPlayer.
      TrackSelector trackSelector = new DefaultTrackSelector();
      LoadControl loadControl = new DefaultLoadControl();
      exoPlayer = ExoPlayerFactory
          .newSimpleInstance(new DefaultRenderersFactory(getContext()), trackSelector, loadControl);
      rootView.playerView.setPlayer(exoPlayer);

      exoPlayer.addListener(this);

      // Prepare the MediaSource.
      String userAgent = Util.getUserAgent(getContext(), "BakingApp");

      DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(context, userAgent);
      Uri mediaUri = Uri.parse(step.getVideoUrl());
      MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
          .createMediaSource(mediaUri);
      exoPlayer.prepare(mediaSource);

      exoPlayer.setPlayWhenReady(startPlaying);
      if (startWindow != C.INDEX_UNSET) {
        exoPlayer.seekTo(startWindow, startPosition);
      }
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
  public void onSaveInstanceState(@NonNull Bundle outState) {
    outState.putBoolean(KEY_AUTO_PLAY, exoPlayer.getPlayWhenReady());
    outState.putLong(KEY_POSITION, Math.max(0, exoPlayer.getContentPosition()));
    outState.putInt(KEY_WINDOW, Math.max(0, exoPlayer.getCurrentWindowIndex()));
  }

  @Override
  public void onStart() {
    super.onStart();
    if (Util.SDK_INT > 23) {
      initializePlayer();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (Util.SDK_INT <= 23 || exoPlayer == null) {
      initializePlayer();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if (Util.SDK_INT <= 23) {
      releasePlayer();
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    if (Util.SDK_INT > 23) {
      releasePlayer();
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

package com.example.seek;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    public void play(View view){
        mediaPlayer.start();
    }
    public void pause(View view){
        mediaPlayer.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
      mediaPlayer = MediaPlayer.create(this,R.raw.followfire);
      SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeSeekBar);
      volumeControl.setMax(maxVolume);
      volumeControl.setProgress(currVolume);
      volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              Log.i("Seekbar changed", Integer.toString(progress));
              audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {

          }
      });

      SeekBar dragMusic = (SeekBar) findViewById(R.id.scrubSeekBar);
      dragMusic.setMax(mediaPlayer.getDuration());
      dragMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              Log.i("Music seeked", Integer.toString(progress));
              mediaPlayer.seekTo(progress);
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {

          }
      });
      new Timer().scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
              dragMusic.setProgress(mediaPlayer.getCurrentPosition());
          }
      },0 , 2440);
    }
}
package com.underthejava.igottagogo;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ryan on 2017-03-22.
 */

public class SettingsActivity extends Fragment implements
        SeekBar.OnSeekBarChangeListener
{
    private SeekBar radiusSeekBar;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Settings");

        initializeVariables();
    }

    private void initializeVariables() {
        int radius = User.getInstance().getRadius();

        radiusSeekBar = (SeekBar) getView().findViewById(R.id.seekBar);
        radiusSeekBar.setOnSeekBarChangeListener(this);

        textView = (TextView) getView().findViewById(R.id.tvRadiusNumber);
        radiusSeekBar.setProgress(radius);

        // @NOTE(Llewellin):
        // radiusSeekBar.setProgress() doesn't update when radius is 0, so must call updateRadius
        if(radius == 0) {
            updateRadiusText();
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        updateRadiusText();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        User.getInstance().setRadius(radiusSeekBar.getProgress());
        // Toast.makeText(getContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
    }

    private void updateRadiusText() {
        int radius = radiusSeekBar.getProgress();
        textView.setText(radius + " km");
    }
}

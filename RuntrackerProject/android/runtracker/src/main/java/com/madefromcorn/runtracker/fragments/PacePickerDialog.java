package com.madefromcorn.runtracker.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockDialogFragment;
import com.madefromcorn.runtracker.R;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 1/28/13
 * Time: 4:43 PM
 */
public class PacePickerDialog extends RoboSherlockDialogFragment {

    private int mHours;
    private int mMinutes;
    private int mSeconds;

    public PacePickerDialog(int hours, int minutes, int seconds) {
        mHours = hours;
        mMinutes = minutes;
        mSeconds = seconds;
    }

    public interface OnSetPaceClickListener {
        public void setPaceClicked(int hour, int minutes, int seconds);
    }

    private OnSetPaceClickListener mSetPaceClickListener;
    private NumberPicker mHourPicker;
    private NumberPicker mMinPicker;
    private NumberPicker mSecPicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = createView();

        return builder
                .setTitle("Choose your Pace")
                .setView(view)
                .setPositiveButton("Set", mSetPaceOnClick)
                .setNegativeButton("Cancel", null)
                .create();
    }

    private View createView() {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.pace_dialog_fragment, null);
        mHourPicker = (NumberPicker) view.findViewById(R.id.hour_picker);
        mMinPicker = (NumberPicker) view.findViewById(R.id.min_picker);
        mSecPicker = (NumberPicker) view.findViewById(R.id.sec_picker);

        mHourPicker.setMaxValue(10);
        mHourPicker.setValue(mHours);
        mMinPicker.setMaxValue(59);
        mMinPicker.setValue(mMinutes);
        mSecPicker.setMaxValue(59);
        mSecPicker.setValue(mSeconds);
        return view;
    }

    private Dialog.OnClickListener mSetPaceOnClick = new Dialog.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (mSetPaceClickListener != null) {
                mSetPaceClickListener.setPaceClicked(mHourPicker.getValue(), mMinPicker.getValue(), mSecPicker.getValue());
            }
        }
    };

    public OnSetPaceClickListener getOnSetPaceClickListener() {
        return mSetPaceClickListener;
    }

    public void setOnSetPaceClickListener(OnSetPaceClickListener listener) {
        this.mSetPaceClickListener = listener;
    }
}

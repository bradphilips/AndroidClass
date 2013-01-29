package com.madefromcorn.runtracker.uicommon;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.madefromcorn.runtracker.R;
import com.madefromcorn.runtracker.fragments.PacePickerDialog;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 1/28/13
 * Time: 8:22 PM
 */
public class PaceSpinner extends Spinner {

    private static final String DIALOG_TAG = PaceSpinner.class.getName() + ".DIALOG_TAG";

    private int mHours = 0;
    private int mMinutes = 0;
    private int mSeconds = 0;
    private ArrayAdapter<String> mAdapter;
    private PacePickerDialog.OnSetPaceClickListener mOnSetPaceClickListener;

    public PaceSpinner(Context context) {
        super(context);
        setupAdapter(context);
    }

    public PaceSpinner(Context context, int mode) {
        super(context, mode);
        setupAdapter(context);
    }

    public PaceSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupAdapter(context);
    }

    public PaceSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupAdapter(context);
    }

    public PaceSpinner(Context context, AttributeSet attrs, int defStyle, int mode) {
        super(context, attrs, defStyle, mode);
        setupAdapter(context);
    }

    @Override
    public boolean performClick() {
        FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
        PacePickerDialog pickerDialog = new PacePickerDialog(mHours, mMinutes, mSeconds);
        pickerDialog.show(fragmentManager, DIALOG_TAG);

        pickerDialog.setOnSetPaceClickListener(new PacePickerDialog.OnSetPaceClickListener() {
            @Override
            public void setPaceClicked(int hours, int minutes, int seconds) {
                mHours = hours;
                mMinutes = minutes;
                mSeconds = seconds;

                mAdapter.insert(String.format("%02d:%02d:%02d", mHours, mMinutes, mSeconds), 0);
                setSelection(0, true);
                if (mOnSetPaceClickListener != null) {
                    mOnSetPaceClickListener.setPaceClicked(hours, minutes, seconds);
                }
            }
        });
        return false;
    }

    public PacePickerDialog.OnSetPaceClickListener getOnSetPaceClickListener() {
        return mOnSetPaceClickListener;
    }

    public void setOnSetPaceClickListener(PacePickerDialog.OnSetPaceClickListener mOnSetPaceClickListener) {
        this.mOnSetPaceClickListener = mOnSetPaceClickListener;
    }

    private void setupAdapter(Context context) {
        mAdapter = new ArrayAdapter<String>(context, R.layout.sherlock_spinner_item);
        mAdapter.insert(String.format("%02d:%02d:%02d", mHours, mMinutes, mSeconds), 0);
        setAdapter(mAdapter);
    }
}

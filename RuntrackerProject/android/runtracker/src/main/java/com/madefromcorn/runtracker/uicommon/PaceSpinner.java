package com.madefromcorn.runtracker.uicommon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import com.madefromcorn.runtracker.R;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 1/28/13
 * Time: 8:22 PM
 */
public class PaceSpinner extends Spinner {

    private static final String DIALOG_TAG = PaceSpinner.class.getName() + ".DIALOG_TAG";

    private NumberPicker mMinPicker;
    private NumberPicker mSecPicker;
    private ArrayAdapter<String> mAdapter;
    private OnSetPaceClickListener mOnSetPaceClickListener;

    private static final int MAXIMUM_MINS = 59;
    private static final int MAXIMUM_SECS = 59;
    private static final int MINIMUM_MINS = 0;
    private static final int MINIMUM_SECS = 0;

    private int mMinutes;
    private int mSeconds;
    private int mMinimumMins;
    private int mMaximumMins;
    private int mMinimumSecs;
    private int mMaximumSecs;

    public PaceSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PaceSpinner);
        mMaximumMins = attributes.getInt(R.styleable.PaceSpinner_maxMinutes, MAXIMUM_MINS);
        mMaximumSecs = attributes.getInt(R.styleable.PaceSpinner_maxSeconds, MAXIMUM_SECS);
        mMinimumMins = attributes.getInt(R.styleable.PaceSpinner_minMinutes, MINIMUM_MINS);
        mMinimumSecs = attributes.getInt(R.styleable.PaceSpinner_minSeconds, MINIMUM_SECS);
        mMinutes = mMinimumMins;
        mSeconds = mMinimumSecs;
        setupAdapter(context);
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = createView();
        mMinPicker = (NumberPicker) view.findViewById(R.id.min_picker);
        mSecPicker = (NumberPicker) view.findViewById(R.id.sec_picker);

        mMinPicker.setMaxValue(mMaximumMins);
        mMinPicker.setMinValue(mMinimumMins);
        mSecPicker.setMaxValue(mMaximumSecs);
        mSecPicker.setMinValue(mMinimumSecs);

        mMinPicker.setValue(mMinutes);
        mSecPicker.setValue(mSeconds);
        AlertDialog dialog = builder
                .setTitle("Choose your Pace")
                .setView(view)
                .setPositiveButton("Set", mSetPaceOnClick)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();
        return false;
    }

    private Dialog.OnClickListener mSetPaceOnClick = new Dialog.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mMinutes = mMinPicker.getValue();
            mSeconds = mSecPicker.getValue();
            mAdapter.insert(String.format("%02d:%02d", mMinutes, mSeconds), 0);
            setSelection(0, true);

            if (mOnSetPaceClickListener != null) {
                mOnSetPaceClickListener.setPaceClicked(mMinutes, mSeconds);
            }
        }
    };

    private View createView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pace_dialog_fragment, null);
        return view;
    }

    public void setOnSetPaceClickListener(OnSetPaceClickListener mOnSetPaceClickListener) {
        this.mOnSetPaceClickListener = mOnSetPaceClickListener;
    }

    private void setupAdapter(Context context) {
        mAdapter = new ArrayAdapter<String>(context, R.layout.sherlock_spinner_item);
        mAdapter.insert(String.format("%02d:%02d", mMinimumMins, mMinimumSecs), 0);
        setAdapter(mAdapter);
    }

    public interface OnSetPaceClickListener {
        public void setPaceClicked(int minutes, int seconds);
    }
}

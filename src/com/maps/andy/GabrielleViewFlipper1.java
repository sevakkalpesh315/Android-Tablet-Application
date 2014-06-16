package com.maps.andy;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ViewFlipper;

public class GabrielleViewFlipper1 extends ViewFlipper {

	public GabrielleViewFlipper1(Context context) {
        super(context);
    }
    public GabrielleViewFlipper1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        }
        catch (IllegalArgumentException e) {
            stopFlipping();
        }
    }
}
package com.activity.gesture_app;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

//This class is use as reference, and not implemented

// In the SimpleOnGestureListener subclass you should override
// onDown and any other gesture that you want to detect.
public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final String DEBUG_TAG = "Gestures";
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;



    /*@Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: ");

        // don't return false here or else none of the other
        // gestures will work
        return true;
    }

    /*@Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i(DEBUG_TAG, "onSingleTapConfirmed: ");
        return true;
    }*/

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(DEBUG_TAG, "onLongPress: ");
    }

    /*@Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(DEBUG_TAG, "onDoubleTap: ");
        return true;
    }*/

    /*@Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        Log.i(DEBUG_TAG, "onScroll: ");
        return true;
    }*/

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY) {
        float x1 = e1.getX();
        float y1 = e1.getY();
        float x2 = e2.getX();
        float y2 = e2.getY();
        float diffX = x2 - x1;
        float diffY = y2 - y1;

        boolean result = false;

        Log.d("---onFling---", diffX + diffY + "");

        try {
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        Log.d(DEBUG_TAG, "onFling: Right Swipe");

                    } else {
                        Log.d(DEBUG_TAG, "onFling: Left Swipe");
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    Log.d(DEBUG_TAG, "onFling: Down Swipe");

                } else {
                    Log.d(DEBUG_TAG, "onFling: Up Swipe");

                }
                result = true;
            }
        } catch (Exception e) {
        // nothing
        }
        return result;
    }
}

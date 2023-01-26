package com.example.moveabletextview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // variables initaialized to the position of screen
    private TextView textView;

    float[] lastEvent = null;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private int mode = NONE;
    private PointF start = new PointF();
    private float xCoOrdinate, yCoOrdinate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.TxtHour);

        // after clicking the textview to drag it
        textView.setOnTouchListener((v, event) -> {

            TextView view = (TextView) v;
            // method called to drag the textview parameterised with view and event
            viewTransformation(view, event);
            return true;
        });
    }


    // method to drag the textview
    private void viewTransformation(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            // on action down gets the coordinates of view and the rew cordinates
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();

                // sets the textview at dragged position
                start.set(event.getX(), event.getY());
                isOutSide = false;
                mode = DRAG;
                lastEvent = null;
                break;

                // action to move the textview freely optimising the coordinates
            case MotionEvent.ACTION_MOVE:
                if (!isOutSide) {
                    if (mode == DRAG) {
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                    }
                }
                break;
        }
    }

}
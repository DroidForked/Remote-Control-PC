package me.varunon9.remotecontrolpc.touchpad;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.varunon9.remotecontrolpc.R;
import me.varunon9.remotecontrolpc.espusb.AppInstance;
import me.varunon9.remotecontrolpc.espusb.keybindings.AppMouseKeyBindings;

import static me.varunon9.remotecontrolpc.espusb.Utility.hideKeyboard;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouchpadFragment extends Fragment {
    private String TAG = "TouchpadFragment";

    private Button leftClickButton, rightClickButton;
    private TextView touchPadTextView;
    private int initX, initY, disX, disY;
    boolean mouseMoved = false, moultiTouch = false;
    private Button middleClickButton;
    private AppMouseKeyBindings appMouseKeyBindings = new AppMouseKeyBindings();

    public TouchpadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_touchpad, container, false);
        leftClickButton = (Button) rootView.findViewById(R.id.leftClickButton);
        middleClickButton = (Button) rootView.findViewById(R.id.middleClickButton);
        rightClickButton = (Button) rootView.findViewById(R.id.rightClickButton);
        touchPadTextView = (TextView) rootView.findViewById(R.id.touchPadTextView);
        leftClickButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if (MainActivity.clientSocket != null) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(-0x1f0b8adf, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        simulateLeftClick_down();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        simulateLeftClick_up();
                        break;
                }
//                }
                return true;
            }
        });
        middleClickButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if (MainActivity.clientSocket != null) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(-0x1f0b8adf, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        simulateMiddleClick_down();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        simulateMiddleClick_up();
                        break;
                }
//                }
                return true;
            }
        });
        rightClickButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if (MainActivity.clientSocket != null) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(-0x1f0b8adf, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        simulateRightClick_down();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        simulateRightClick_up();

                        break;
                }
//                }
                return true;
            }
        });
        touchPadTextView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if (MainActivity.clientSocket != null) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        //save X and Y positions when user touches the TextView
                        initX = (int) event.getX();
                        initY = (int) event.getY();
                        mouseMoved = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (moultiTouch == false) {
                            disX = (int) event.getX() - initX; //Mouse movement in x direction
                            disY = (int) event.getY() - initY; //Mouse movement in y direction
                                /*set init to new position so that continuous mouse movement
                                is captured*/
                            initX = (int) event.getX();
                            initY = (int) event.getY();
                            if (disX != 0 || disY != 0) {
                                Log.v(TAG, "MOUSE_MOVE " + disX + " " + disY);
                                sendMouseEvent(disX, disY);
                                mouseMoved = true;
                            }
                        } else {
                            disY = (int) event.getY() - initY; //Mouse movement in y direction
                            disY = (int) disY / 2;//to scroll by less amount
                            initY = (int) event.getY();
                            if (disY != 0) {
                                Log.v(TAG, "MOUSE_WHEEL " + disX + " " + disY);
                                mouseMoved = true;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_UP:
                        //consider a tap only if user did not move mouse after ACTION_DOWN
                        if (!mouseMoved) {
//                                simulateLeftClick();
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        initY = (int) event.getY();
                        mouseMoved = false;
                        moultiTouch = true;
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        if (!mouseMoved) {
//                                simulateLeftClick();
                        }
                        moultiTouch = false;
                        break;
                }
//                }
                return true;
            }
        });
        hideKeyboard(this.getActivity());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.touchpad));
    }

    private void simulateLeftClick_up() {
        Log.v(TAG, "Left up");
        appMouseKeyBindings.releaseleft();
        sendMouseEvent();
//        var msg = "CM1\t0\t0";
    }

    private void simulateLeftClick_down() {
        Log.v(TAG, "Left down");
        appMouseKeyBindings.pressleft();
        sendMouseEvent();
    }


    private void simulateMiddleClick_up() {
        Log.v(TAG, "Middle up");
        appMouseKeyBindings.releasemiddle();
        sendMouseEvent();

    }

    private void simulateMiddleClick_down() {
        Log.v(TAG, "Middle down");
        appMouseKeyBindings.pressmiddle();
        sendMouseEvent();

    }

    private void simulateRightClick_up() {
        Log.v(TAG, "Right up");
        appMouseKeyBindings.releaseright();
        sendMouseEvent();

    }

    private void simulateRightClick_down() {
        Log.v(TAG, "Right down");
        appMouseKeyBindings.pressright();
        sendMouseEvent();

    }

    private void sendMouseEvent() {
        sendMouseEvent(0, 0);
    }

    private void sendMouseEvent(int x, int y) {
        int modifier = appMouseKeyBindings.current_bindings();
        String msg = "CM" + modifier + "\t" + x + "\t" + y;
        AppInstance.getInstance().getEspusb().command_append(msg);

    }
}

package me.varunon9.remotecontrolpc.espusb.uimanager;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ButtonBackgroundManager {
    private Drawable shiftbutton_unpress;
    private Drawable shiftbutton_press;
    private boolean is_shiftbutton=false;

    private Drawable altbutton_unpress;
    private Drawable altbutton_press;
    private boolean is_altbutton=false;

    private Drawable ctrlbutton_unpress;
    private Drawable ctrlbutton_press;
    private boolean is_ctrlbutton=false;

    private Drawable homebutton_unpress;
    private Drawable homebutton_press;
    private boolean is_homebutton=false;

    final private int color = 0xff99cc00;

    public void press(Button button) {
        int id = button.getId();
        switch (id) {
            case me.varunon9.remotecontrolpc.R.id.shiftButton:
                is_shiftbutton=true;
                if (shiftbutton_unpress == null) {
                    shiftbutton_unpress = button.getBackground();
                }
                if (shiftbutton_press == null) {
                    button.setBackgroundColor(color);
                    shiftbutton_press = button.getBackground();
                } else {
                    button.setBackground(shiftbutton_press);
                }
                break;
            case me.varunon9.remotecontrolpc.R.id.altButton:
                is_altbutton=true;
                if (altbutton_unpress == null) {
                    altbutton_unpress = button.getBackground();
                }
                if (altbutton_press == null) {
                    button.setBackgroundColor(color);
                    altbutton_press = button.getBackground();
                } else {
                    button.setBackground(altbutton_press);
                }
                break;
            case me.varunon9.remotecontrolpc.R.id.ctrlButton:
                is_ctrlbutton=true;
                if (ctrlbutton_unpress == null) {
                    ctrlbutton_unpress = button.getBackground();
                }
                if (ctrlbutton_press == null) {
                    button.setBackgroundColor(color);
                    ctrlbutton_press = button.getBackground();
                } else {
                    button.setBackground(ctrlbutton_press);
                }
                break;
            case me.varunon9.remotecontrolpc.R.id.Home:
                is_homebutton=true;
                if (homebutton_unpress == null) {
                    homebutton_unpress = button.getBackground();
                }
                if (homebutton_press == null) {
                    button.setBackgroundColor(color);
                    homebutton_press = button.getBackground();
                } else {
                    button.setBackground(homebutton_press);
                }
                break;
        }
    }

    public void unpress(Button button) {
        int id = button.getId();
        switch (id) {
            case me.varunon9.remotecontrolpc.R.id.shiftButton:
                if (shiftbutton_unpress != null) {
                    is_shiftbutton=false;
                    button.setBackground(shiftbutton_unpress);
                }
                break;
            case me.varunon9.remotecontrolpc.R.id.altButton:
                if (altbutton_unpress != null) {
                    is_altbutton=false;
                    button.setBackground(altbutton_unpress);
                }
                break;
            case me.varunon9.remotecontrolpc.R.id.ctrlButton:
                if (ctrlbutton_unpress != null) {
                    is_ctrlbutton=false;
                    button.setBackground(ctrlbutton_unpress);
                }
                break;
            case me.varunon9.remotecontrolpc.R.id.Home:
                if (homebutton_unpress != null) {
                    is_homebutton=false;
                    button.setBackground(homebutton_unpress);
                }
                break;
        }
    }


    public void toggle(Button button, boolean state){
        if(state){
            unpress(button);
            return;
        }
        press(button);

    }

    ///////////////
    public boolean is_shiftbutton() {
        return is_shiftbutton;
    }

    public boolean is_altbutton() {
        return is_altbutton;
    }

    public boolean is_ctrlbutton() {
        return is_ctrlbutton;
    }

    public boolean is_homebutton() {
        return is_homebutton;
    }


}

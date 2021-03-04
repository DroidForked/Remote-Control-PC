package me.varunon9.remotecontrolpc.usbesp;

import com.neovisionaries.ws.client.WebSocket;

import java.util.ArrayList;

import me.varunon9.remotecontrolpc.usbesp.keybindings.AppKeyBindings;

public class AppInstance {
    // static variable single_instance of type Singleton 
    private static AppInstance single_instance = null;

    // variable of type String

    // private constructor restricted to this class itself
    EspUsb espusb;
    AppKeyBindings appKeyBindings;

    private AppInstance() {
        appKeyBindings = new AppKeyBindings();
    }

    // static method to create instance of Singleton class 
    public static AppInstance getInstance() {
        if (single_instance == null)
            single_instance = new AppInstance();
        return single_instance;
    }

    public EspUsb getEspusb() {
        return espusb;
    }


    public void setEspusb(EspUsb espusb) {
        this.espusb = espusb;
    }

    public AppKeyBindings getAppKeyBindings() {
        return appKeyBindings;
    }

}
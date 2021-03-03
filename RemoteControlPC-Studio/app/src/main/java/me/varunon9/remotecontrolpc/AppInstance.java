package me.varunon9.remotecontrolpc;

import com.neovisionaries.ws.client.WebSocket;

public class AppInstance {
    // static variable single_instance of type Singleton 
    private static AppInstance single_instance = null;

    // variable of type String

    WebSocket ws = null;

    // private constructor restricted to this class itself 
    private AppInstance() {

    }

    // static method to create instance of Singleton class 
    public static AppInstance getInstance() {
        if (single_instance == null)
            single_instance = new AppInstance();

        return single_instance;
    }

    public WebSocket getWebSocket() {
        return ws;
    }

    public void setWebSocket(WebSocket ws) {
        this.ws = ws;
    }
} 
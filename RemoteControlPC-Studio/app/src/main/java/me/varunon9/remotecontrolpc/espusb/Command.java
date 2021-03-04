package me.varunon9.remotecontrolpc.espusb;

public class Command {
    private final String request;
    private final Callback callback;

    public Command(String request, Callback callback) {
        this.request = request;
        this.callback = callback;
    }

    public String getRequest() {
        return request;
    }

    public Callback getCallback() {
        return callback;
    }
}


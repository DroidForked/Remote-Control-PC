package me.varunon9.remotecontrolpc.connect;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

import me.varunon9.remotecontrolpc.AppInstance;
import me.varunon9.remotecontrolpc.Completelistener;

public abstract class MakeWSConnection extends AsyncTask<Void, Void, Boolean> implements Completelistener {

    String address;

    MakeWSConnection(String address) {
        this.address = address;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        WebSocketFactory factory = new WebSocketFactory();
        WebSocket ws= null;
        try {
            ws = factory.createSocket("ws://192.168.29.247:8765", 5000);
            ws.connect();
            ws.sendText("Hello.");
            AppInstance.getInstance().setWebSocket(ws);
            return true;
        } catch (WebSocketException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        receiveData(success);
    }

    @Override
    public abstract void receiveData(boolean issuccess) ;
}

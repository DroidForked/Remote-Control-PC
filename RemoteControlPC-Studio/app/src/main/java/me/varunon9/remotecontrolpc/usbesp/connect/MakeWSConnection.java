package me.varunon9.remotecontrolpc.usbesp.connect;

import android.os.AsyncTask;


import me.varunon9.remotecontrolpc.Completelistener;
import me.varunon9.remotecontrolpc.usbesp.AppInstance;
import me.varunon9.remotecontrolpc.usbesp.EspUsb;

public abstract class MakeWSConnection extends AsyncTask<Void, Void, Boolean> implements Completelistener {

    String address;

    public MakeWSConnection(String address) {
        this.address = address;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        EspUsb old_espusb = AppInstance.getInstance().getEspusb();
        if (old_espusb!=null){
            old_espusb.stop();
        }

        EspUsb espusb = new EspUsb(address);
        espusb.tick();
        AppInstance.getInstance().setEspusb(espusb);
        try {
            espusb.wait_tillloaded();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return espusb.is_connected_towebsocket();
    }


    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        receiveData(success);
    }

    @Override
    public abstract void receiveData(boolean issuccess);
}

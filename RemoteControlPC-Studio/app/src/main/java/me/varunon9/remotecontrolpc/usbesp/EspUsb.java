package me.varunon9.remotecontrolpc.usbesp;

import android.util.Log;

import com.neovisionaries.ws.client.*;

import java.io.IOException;
import java.util.*;

public class EspUsb extends TimerTask implements Callback {
    private final String TAG ="EspUsb";

    private final String mWsUri;
    private WebSocket mWebSocket;
    private int commsup = 0;
    private ArrayList<Command> mWorkQueue = new ArrayList<>();
    private HashMap<String, Integer> mWork_hashmap = new HashMap<>();
    private Command mLastItem = null;

    private int msg = 0;
    private int tickmessasge = 0;
    private int time_since_hz = 10;

    private boolean is_connected_towebsocket=false;
    private boolean is_socket_conn_called=false;
    private Timer mtimer;

    public EspUsb(String host) {
        this.mWsUri = "ws://" + host + "/d/ws/issue";
    }

    private void reset_variable() {
        mWorkQueue = new ArrayList<>();
        mWork_hashmap = new HashMap<>();
        mLastItem = null;
    }

    private void process_lastitem(String message) {
        if (mLastItem != null) {
            mLastItem.getCallback().call(this.mLastItem, message);
            mLastItem = null;
        }
    }

    private boolean send_work_queue() {
        if (mWorkQueue.size() > 0) {
            Command elem = mWorkQueue.remove(0);
            mWork_hashmap.remove(elem.getRequest());
            doSend(elem.getRequest());
            mLastItem = elem;
            return true;
        }
        return false;
    }

    private void printme(String msg) {
        System.out.println(msg);
    }

    private void doSend(String message) {
//        printme("send: " + message);
        mWebSocket.sendText(message, false);
    }

    private void send_wx() {
        doSend("wx");
    }

    private void send_e() {
        doSend("e");
    }

    private int getlasthz() {
        int lasthz = msg - tickmessasge;
        tickmessasge = msg;
        return lasthz;
    }

    private void StartWebSocket() throws IOException, WebSocketException {
        if (mWebSocket != null) {
            mWebSocket.sendClose();
        }
        reset_variable();

        WebSocketFactory factory = new WebSocketFactory();
        mWebSocket = factory.createSocket(mWsUri, 5000);
        mWebSocket.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(WebSocket websocket, String message) throws Exception {
                on_message(message);
            }

            @Override
            public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                super.onConnected(websocket, headers);
                on_open();
            }

            @Override
            public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
                super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
                on_close();
            }

            @Override
            public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
                super.onError(websocket, cause);
                on_error();
            }
        });
        mWebSocket.connect();
    }

    private void on_message(String message) {
        msg++;
        if (commsup != 1) {
            commsup = 1;
            printme("Comm established");
        }
        process_lastitem(message);
        if (send_work_queue()) {
            return;
        }
        send_wx();
    }

    private void on_error() {
        commsup = 0;
    }

    private void on_close() {
        commsup = 0;
    }

    private void on_open() {
        send_e();
    }

    private void QueueOperation(String cmd, Callback callback) {
        if (mWork_hashmap.containsKey(cmd)) {
            if (mWork_hashmap.get(cmd) == 1) {
                return;
            }
        }
        mWork_hashmap.put(cmd, 1);
        Command comd_obj = new Command(cmd, callback);
        mWorkQueue.add(comd_obj);
    }

    @Override
    public void run() {
        ///////////////////
//        command_append("CK0\t8");// for testing send "e"
//        command_append("CK0\t0");
        ///////////////////

        if (getlasthz() == 0) {
            time_since_hz++;
            if (time_since_hz > 3) {
                if (commsup != 0) {
                    printme("Comms lost");
                }
                commsup = 0;
                try {
                    StartWebSocket();
                    is_connected_towebsocket=true;
                } catch (IOException | WebSocketException e) {
                    e.printStackTrace();
                    is_connected_towebsocket=false;
                }
                is_socket_conn_called=true;
            }
        } else {
            time_since_hz = 0;
        }

    }

    public void tick() {
        mtimer = new Timer();
        mtimer.schedule(this, 0, 1000);
    }

    public void command_append(String msg) {
        QueueOperation(msg, this);
        Log.v(TAG,"SendQeue: "+msg);
    }

    @Override
    public void call(Object... any) {
        printme("okey...");
    }

    public boolean is_connected_towebsocket() {
        return is_connected_towebsocket;
    }

    public void wait_tillloaded() throws InterruptedException {
        while (!is_socket_conn_called){
         Thread.sleep(100);
        }
    }

    public void stop() {
        //we have to handle closed for previous socket
        if(mtimer!=null){
            mtimer.cancel();
            this.cancel();//cancelling timer task
        }
        if (mWebSocket != null) {
            mWebSocket.sendClose();
        }

    }


//    public static void main(String[] args) {
////        EspUsb espusb = new EspUsb("192.168.4.1");
////        espusb.Ticker();
//    }
}

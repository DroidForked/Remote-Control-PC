

from threading import Timer
import websocket

class EspUsb:
    def __init__(self, host):
        self.wsUri = f"ws://{host}/d/ws/issue"
        self.mywebsocket = None
        self.commsup = 0
        self.workqueue = []
        self.work_dict = {}
        self.lastitem = None
        self.is_waiting_on_stations = False
        ##########
        self.msg = 0
        self.tickmessage = 0
        self.lasthz = 0
        self.time_since_hz = 10

    def reset_variable(self):
        self.work_dict = {}
        self.workqueue = []
        self.lastitem = None

    def increment_msg(self):
        self.msg = self.msg + 1
        pass

    def process_lastitem(self, message):
        if self.lastitem is not None:
            if self.lastitem.callback is not None:
                self.lastitem.callback(self.lastitem, message)
                self.lastitem = None
        pass

    def send_work_queue(self):
        if len(self.workqueue) > 0:
            elem = self.workqueue.pop(0)
            self.work_dict.pop(elem.request)
            if elem.request is not None:
                self.doSend(elem.request)
                self.lastitem = elem
                return True
        return False

    def doSend(self, message):
        print(f"send: {message}")
        self.mywebsocket.send(message)

    def send_wx(self):
        self.doSend('wx')

    def send_e(self):
        self.doSend('e')
        pass

    def getlasthz(self):
        self.lasthz = self.msg - self.tickmessage
        self.tickmessage = self.msg
        return self.lasthz

    def increment_time_since_hz(self):
        self.time_since_hz = self.time_since_hz + 1
        pass

    def StartWebSocket(self):
        if self.mywebsocket is not None:
            self.mywebsocket.close()
        self.reset_variable()

        # websocket.enableTrace(True)
        self.mywebsocket = websocket.WebSocketApp(self.wsUri,
                                                  on_open=self.on_open,
                                                  on_message=self.on_message,
                                                  on_error=self.on_error,
                                                  on_close=self.on_close)

        self.mywebsocket.run_forever()

    def on_message(self, ws, message):
        espData.increment_msg()
        if espData.commsup != 1:
            espData.commsup = 1
            print("Comm Establisheed.")

        espData.process_lastitem(message)

        if espData.send_work_queue():
            return  # return if message is send

        espData.send_wx()

    def on_error(self, ws, error):
        espData.commsup = 0

    def on_close(self, ws):
        espData.commsup = 0

    def on_open(self, ws):
        espData.send_e()

    def QueueOperation(self, command, callback):
        if command in espData.work_dict:
            if espData.work_dict[command] == 1:
                return
        espData.work_dict[command] = 1
        command_obj = Command(command, callback)
        espData.workqueue.append(command_obj)

    def Ticker(self):
        Timer(1.0, self.Ticker).start()


        if self.getlasthz() == 0:
            self.increment_time_since_hz()
            if self.time_since_hz > 3:
                if self.commsup != 0 and not self.is_waiting_on_stations:
                    print("Comms Lost")
                self.commsup = 0
                self.StartWebSocket()
        else:
            self.time_since_hz = 0


class Command:
    def __init__(self, command, callback):
        self.request = command
        self.callback = callback





def command_append(cmd):
    espData.QueueOperation(cmd, print)

def mesg_send_tick():
    Timer(5.0, mesg_send_tick).start()
    command_append("CK0\t8") # sending press "e"
    command_append("CK0\t0") # releasing key

    #hope I add another function to make string to "CK0\t...." converson... i will do when i need...

###########################
espData = EspUsb("192.168.4.1")

if __name__ == "__main__":
    mesg_send_tick()
    espData.Ticker()


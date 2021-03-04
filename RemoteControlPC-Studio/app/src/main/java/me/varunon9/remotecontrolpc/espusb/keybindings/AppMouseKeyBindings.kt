package me.varunon9.remotecontrolpc.espusb.keybindings

class AppMouseKeyBindings {
    companion object {
        val LEFT = 1
        val RIGHT = 2
        val MIDDLE = 4

    }

    val mLeft = MouseKey(LEFT)
    val mRight = MouseKey(RIGHT)
    val mMiddle = MouseKey(MIDDLE)

    fun current_bindings(): Int {
        return mLeft.keystate() + mRight.keystate() + mMiddle.keystate()
    }

    fun pressleft() {
        mLeft.press()
    }

    fun releaseleft() {
        mLeft.release()
    }

    fun pressright() {
        mRight.press()
    }

    fun releaseright() {
        mRight.release()
    }


    fun pressmiddle() {
        mMiddle.press()
    }

    fun releasemiddle() {
        mMiddle.release()
    }


}


class MouseKey(val value: Int) {
    var current_state = false;


    fun press() {
        current_state = true;
    }

    fun release() {
        current_state = false;
    }


    fun keystate(): Int {
        if (current_state) {
            return value
        }
        return 0
    }


}










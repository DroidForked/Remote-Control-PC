package me.varunon9.remotecontrolpc.usbesp.keybindings

import me.varunon9.remotecontrolpc.CallbackReceiver
import me.varunon9.remotecontrolpc.usbesp.UpdatViewCallback

class AppKeyBindings {
    companion object{
        val CTRL=1
        val SHIFT=2
        val ALT=4
        val HOME=8

    }
    val mCtrl= HotKey(CTRL)
    val mShift=HotKey(SHIFT)
    val mAlt=HotKey(ALT)
    val mHome=HotKey(HOME);

    fun current_bindings():Int{
        return mCtrl.keystate()+mShift.keystate()+mAlt.keystate()+mHome.keystate()
    }

    fun updateButtonViws(){
        mCtrl.updateView()
        mShift.updateView()
        mAlt.updateView()
        mHome.updateView()
    }

}


class HotKey(val value: Int) {
    var current_state = false;
    var continue_pressing = false;
    var button_id:Int=0
    var callbackReceiver: UpdatViewCallback?=null
    var lock_callback_call=true

    fun setCallback(id:Int,callbackReceiver: UpdatViewCallback){
        this.button_id=id
        this.callbackReceiver=callbackReceiver
    }

    fun press() {
        current_state = true;
    }

    fun current_state(press: Boolean){
        current_state=press
        continue_pressing(false)
    }

    fun continue_pressing(will_continue_press: Boolean) {
        continue_pressing = will_continue_press
    }

    fun keystate(): Int {
        if (current_state) {
            if (continue_pressing) {
                return value
            }
            current_state = false;
            //update view from callback
            lock_callback_call=false

            return value
        }
        return 0
    }
    fun updateView(){
        if (lock_callback_call){
            return
        }
        if (this.callbackReceiver!=null){
            this.callbackReceiver?.updateView(this.button_id)
        }
    }


}










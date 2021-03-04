package me.varunon9.remotecontrolpc.espusb;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utility {

    /**
     *
     * For checking if typed character is shifted
     *
     */
//    private static final CharSequence shift_characters =")!@#$%^&*(:+<_>?~{|}\\\"abcdefghijklmnopqrstuvwxyz";
    private static final CharSequence shift_characters =")!@#$%^&*(:+<_>?~{|}\\\"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int shiftcharacter_size=shift_characters.length();
    public static boolean check_is_shifted_character(int key) {
        for ( int i=0 ;i<shiftcharacter_size;i++){
            char c=shift_characters.charAt(i);
            if(c==key){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Hide keyboard
     *
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

package me.varunon9.remotecontrolpc.usbesp;

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
}

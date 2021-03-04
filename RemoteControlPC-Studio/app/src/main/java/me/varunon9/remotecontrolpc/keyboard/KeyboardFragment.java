package me.varunon9.remotecontrolpc.keyboard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import me.varunon9.remotecontrolpc.R;
import me.varunon9.remotecontrolpc.espusb.AppInstance;
import me.varunon9.remotecontrolpc.espusb.Constants;
import me.varunon9.remotecontrolpc.espusb.EspUsb;
import me.varunon9.remotecontrolpc.espusb.Keyboard;
import me.varunon9.remotecontrolpc.espusb.Keypair;
import me.varunon9.remotecontrolpc.espusb.UpdatViewCallback;
import me.varunon9.remotecontrolpc.espusb.keybindings.AppKeyBindings;
import me.varunon9.remotecontrolpc.espusb.uimanager.ButtonBackgroundManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeyboardFragment extends Fragment implements View.OnClickListener, TextWatcher, View.OnLongClickListener, UpdatViewCallback {

    private EditText typeHereEditText;
    private Button ctrlButton, altButton, shiftButton, enterButton, tabButton, escButton, printScrButton, backspaceButton;
    private Button deleteButton, clearTextButton;
    private Button homeButton, tButton, wButton, rButton, fButton, zButton;
    private Button cButton, xButton, vButton, aButton, oButton, sButton;
    private Button HomeTButton, ctrlShiftZButton, altF4Button;
    private Keyboard keyboard = new Keyboard();
    private String TAG = "KeyboardFragment";
    private boolean flag_edittext_clear = false;
    private ButtonBackgroundManager buttonBackgroundManager = new ButtonBackgroundManager();


    public KeyboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_keyboard, container, false);
        initialization(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.keyboard));
    }

    private void initialization(View rootView) {
        typeHereEditText = (EditText) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.typeHereEditText);
        ctrlButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.ctrlButton);
        altButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.altButton);
        shiftButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.shiftButton);
        homeButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.Home);
        enterButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.enterButton);
        tabButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.tabButton);
        escButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.escButton);
        printScrButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.printScrButton);
        backspaceButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.backspaceButton);
        deleteButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.deleteButton);
        clearTextButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.clearTextButton);
        tButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.tButton);
        wButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.wButton);
        rButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.rButton);
        fButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.fButton);
        zButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.zButton);
        cButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.cButton);
        xButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.xButton);
        vButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.vButton);
        aButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.aButton);
        oButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.oButton);
        sButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.sButton);
        HomeTButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.HomeTbutton);
        ctrlShiftZButton = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.ctrlShiftZButton);
        altF4Button = (Button) rootView.findViewById(me.varunon9.remotecontrolpc.R.id.altF4Button);
        //////////////////////////////////////
        ctrlButton.setOnClickListener(this);
        altButton.setOnClickListener(this);
        shiftButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);

        ctrlButton.setOnLongClickListener(this);
        altButton.setOnLongClickListener(this);
        shiftButton.setOnLongClickListener(this);
        homeButton.setOnLongClickListener(this);
        //////////////////////////////////////
        backspaceButton.setOnClickListener(this);
        enterButton.setOnClickListener(this);
        tabButton.setOnClickListener(this);
        escButton.setOnClickListener(this);
        printScrButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        clearTextButton.setOnClickListener(this);
        tButton.setOnClickListener(this);
        wButton.setOnClickListener(this);
        rButton.setOnClickListener(this);
        fButton.setOnClickListener(this);
        zButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        xButton.setOnClickListener(this);
        vButton.setOnClickListener(this);
        aButton.setOnClickListener(this);
        oButton.setOnClickListener(this);
        sButton.setOnClickListener(this);
        HomeTButton.setOnClickListener(this);
        ctrlShiftZButton.setOnClickListener(this);
        altF4Button.setOnClickListener(this);
        typeHereEditText.addTextChangedListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == me.varunon9.remotecontrolpc.R.id.clearTextButton) {
            flag_edittext_clear = true;
            typeHereEditText.setText("");
        } else if (id == me.varunon9.remotecontrolpc.R.id.HomeTbutton || id == me.varunon9.remotecontrolpc.R.id.ctrlShiftZButton || id == me.varunon9.remotecontrolpc.R.id.altF4Button) {
            EspUsb espUsb;
            String msg;
            switch (id) {
                case me.varunon9.remotecontrolpc.R.id.HomeTbutton:
                    msg = "CK8\t23";
                    espUsb = AppInstance.getInstance().getEspusb();
                    espUsb.command_append(msg);
                    addtoKeyPressQueue(null);
                    break;
                case me.varunon9.remotecontrolpc.R.id.ctrlShiftZButton:
                    msg = "CK3\t29";
                    espUsb = AppInstance.getInstance().getEspusb();
                    espUsb.command_append(msg);
                    addtoKeyPressQueue(null);
                    break;
                case me.varunon9.remotecontrolpc.R.id.altF4Button:
                    msg = "CK4\t22";
                    espUsb = AppInstance.getInstance().getEspusb();
                    espUsb.command_append(msg);
                    addtoKeyPressQueue(null);
                    break;
            }
//            MainActivity.sendMessageToServer(message);
        } else {
            String action = "TYPE_KEY";
            switch (id) {
                case me.varunon9.remotecontrolpc.R.id.enterButton:
                    addtoKeyPressQueue(Constants.ENTER);
                    break;
                case me.varunon9.remotecontrolpc.R.id.tabButton:
                    addtoKeyPressQueue(Constants.TAB);
                    break;
                case me.varunon9.remotecontrolpc.R.id.escButton:
                    addtoKeyPressQueue(Constants.ESC);
                    break;
                case me.varunon9.remotecontrolpc.R.id.printScrButton:
                    addtoKeyPressQueue(Constants.PRINT_SCRN);
                    break;
                case me.varunon9.remotecontrolpc.R.id.deleteButton:
                    addtoKeyPressQueue(Constants.DELETE);///need to ix as it send "."
                    break;

                case me.varunon9.remotecontrolpc.R.id.tButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.wButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.rButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.fButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.zButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.cButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.xButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.vButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.aButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.oButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.sButton:
                    break;
                case me.varunon9.remotecontrolpc.R.id.backspaceButton:
                    addtoKeyPressQueue(Constants.BACKSPACE);///
                    break;
                //////////////////////////////////////////////
                case me.varunon9.remotecontrolpc.R.id.shiftButton:
                    buttonBackgroundManager.toggle(shiftButton, buttonBackgroundManager.is_shiftbutton());
                    AppInstance.getInstance().getAppKeyBindings().getMShift().current_state(buttonBackgroundManager.is_shiftbutton());
                    AppInstance.getInstance().getAppKeyBindings().getMShift().setCallback(id, this);
                    if(!buttonBackgroundManager.is_shiftbutton()){
                        addtoKeyPressQueue(null);
                    }
                    break;
                case me.varunon9.remotecontrolpc.R.id.altButton:
                    buttonBackgroundManager.toggle(altButton, buttonBackgroundManager.is_altbutton());
                    AppInstance.getInstance().getAppKeyBindings().getMAlt().current_state(buttonBackgroundManager.is_altbutton());
                    AppInstance.getInstance().getAppKeyBindings().getMAlt().setCallback(id, this);
                    if(!buttonBackgroundManager.is_altbutton()){
                        addtoKeyPressQueue(null);
                    }
                    break;
                case me.varunon9.remotecontrolpc.R.id.ctrlButton:
                    buttonBackgroundManager.toggle(ctrlButton, buttonBackgroundManager.is_ctrlbutton());
                    AppInstance.getInstance().getAppKeyBindings().getMCtrl().current_state(buttonBackgroundManager.is_ctrlbutton());
                    AppInstance.getInstance().getAppKeyBindings().getMCtrl().setCallback(id, this);
                    if(!buttonBackgroundManager.is_ctrlbutton()){
                        addtoKeyPressQueue(null);
                    }
                    break;
                case me.varunon9.remotecontrolpc.R.id.Home:
                    buttonBackgroundManager.toggle(homeButton, buttonBackgroundManager.is_homebutton());
                    AppInstance.getInstance().getAppKeyBindings().getMHome().current_state(buttonBackgroundManager.is_homebutton());
                    AppInstance.getInstance().getAppKeyBindings().getMHome().setCallback(id, this);
                    if(!buttonBackgroundManager.is_homebutton()){
                        addtoKeyPressQueue(null);
                    }
                    break;
            }
        }

    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        switch (id) {
            //////////////////////////////////////////////
            case me.varunon9.remotecontrolpc.R.id.shiftButton:
                buttonBackgroundManager.toggle(shiftButton, buttonBackgroundManager.is_shiftbutton());
                AppInstance.getInstance().getAppKeyBindings().getMShift().current_state(buttonBackgroundManager.is_shiftbutton());
                AppInstance.getInstance().getAppKeyBindings().getMShift().continue_pressing(buttonBackgroundManager.is_shiftbutton());
                AppInstance.getInstance().getAppKeyBindings().getMShift().setCallback(id, this);

                break;
            case me.varunon9.remotecontrolpc.R.id.altButton:
                buttonBackgroundManager.toggle(altButton, buttonBackgroundManager.is_altbutton());
                AppInstance.getInstance().getAppKeyBindings().getMAlt().current_state(buttonBackgroundManager.is_altbutton());
                AppInstance.getInstance().getAppKeyBindings().getMAlt().continue_pressing(buttonBackgroundManager.is_altbutton());
                AppInstance.getInstance().getAppKeyBindings().getMAlt().setCallback(id, this);
                break;
            case me.varunon9.remotecontrolpc.R.id.ctrlButton:
                buttonBackgroundManager.toggle(ctrlButton, buttonBackgroundManager.is_ctrlbutton());
                AppInstance.getInstance().getAppKeyBindings().getMCtrl().current_state(buttonBackgroundManager.is_ctrlbutton());
                AppInstance.getInstance().getAppKeyBindings().getMCtrl().continue_pressing(buttonBackgroundManager.is_ctrlbutton());
                AppInstance.getInstance().getAppKeyBindings().getMCtrl().setCallback(id, this);

                break;
            case me.varunon9.remotecontrolpc.R.id.Home:
                buttonBackgroundManager.toggle(homeButton, buttonBackgroundManager.is_homebutton());
                AppInstance.getInstance().getAppKeyBindings().getMHome().current_state(buttonBackgroundManager.is_homebutton());
                AppInstance.getInstance().getAppKeyBindings().getMHome().continue_pressing(buttonBackgroundManager.is_homebutton());
                AppInstance.getInstance().getAppKeyBindings().getMHome().setCallback(id, this);

                break;
            default:
                return true;
        }
        addtoKeyPressQueue(null);
        return true;
    }

    @Override
    public void updateView(int buttonId) {
        switch (buttonId) {
            //////////////////////////////////////////////
            case me.varunon9.remotecontrolpc.R.id.shiftButton:
                buttonBackgroundManager.unpress(shiftButton);
                addtoKeyPressQueue(null);
//                AppInstance.getInstance().getEspusb().command_append("CK0\t0");//sending keypad release// sudo aPt GetUpdate~~
                break;
            case me.varunon9.remotecontrolpc.R.id.altButton:
                buttonBackgroundManager.unpress(altButton);
                addtoKeyPressQueue(null);
//                AppInstance.getInstance().getEspusb().command_append("CK0\t0");//sending keypad release// sudo aPt GetUpdate~~
                break;
            case me.varunon9.remotecontrolpc.R.id.ctrlButton:
                buttonBackgroundManager.unpress(ctrlButton);
                addtoKeyPressQueue(null);
//                AppInstance.getInstance().getEspusb().command_append("CK0\t0");//sending keypad release// sudo aPt GetUpdate~~
                break;
            case me.varunon9.remotecontrolpc.R.id.Home:
                buttonBackgroundManager.unpress(homeButton);
                addtoKeyPressQueue(null);
//                AppInstance.getInstance().getEspusb().command_append("CK0\t0");//sending keypad release// sudo aPt GetUpdate~~
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }


    private void addtoKeyPressQueue(String key) {
        Integer value;
        if (key == null) {
            value = 0;
        } else {
            Keypair keypair = keyboard.lookup(key);
            if (keypair == null) {
                Log.d(TAG, "Keypair is null /addtoKeyPressQueue");
                return;
            }

            value = keypair.keyvalue(keyboard.getEn_usblookup());

            if (null == value) {
                Log.d(TAG, "Keyvalue is null /addtoKeyPressQueue");
                return;
            }
        }

        int modifeir = resolve_modiefier(0);
        String msg = "CK" + modifeir + "\t" + value;
        EspUsb espUsb = AppInstance.getInstance().getEspusb();
        espUsb.command_append(msg);
        if (key != null) {
//            espUsb.command_append("CK0\t0");//sending keypad release// sudo aPt GetUpdate~~
            addtoKeyPressQueue(null);
        }
    }

    private int resolve_modiefier(int modifier) {

        AppKeyBindings keybinding = AppInstance.getInstance().getAppKeyBindings();
        int keybinding_modifier = keybinding.current_bindings();
        if (keybinding_modifier != 0) {
            modifier = keybinding_modifier;
        }
        return modifier;
    }

    private void addtoMessageQueue(char ch) {
        Keypair keypair = new Keypair(null, (int) ch);
        if (keypair == null) {
            Log.d(TAG, "Keypair is null /addtoMessageQueue");
            return;
        }
        Integer value = keypair.keyvalue(keyboard.getEn_usblookup());

        if (value == null) {
            Log.d(TAG, "Keyvalue is null /addtoMessageQueue");
            return;
        }
        int modifeir;
        ///resolve modifeir///.##_____`||||SDH
        if (keypair.is_shifted_charactor()) {
            modifeir = keyboard.getMODIFIER_SHIFT();
        } else {
            modifeir = keyboard.getMODIFIER_NONE();
        }
        ///////////////////////
        modifeir = resolve_modiefier(modifeir);

        String msg = "CK" + modifeir + "\t" + value;
        EspUsb espUsb = AppInstance.getInstance().getEspusb();
        espUsb.command_append(msg);
        addtoKeyPressQueue(null);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (flag_edittext_clear) {
            flag_edittext_clear = false;
            Log.v(TAG, "Dont proceed as clear text");
            return;
        }
        Log.v(TAG, "start: " + start + " before: " + before + " count: " + count + " Char:" + s.toString());
        int difference = count - before;
        if (count > before) {
            for (int i = 0; i < difference; i++) {
                char ch = s.charAt(count + start - 1 - i);
                Log.v(TAG, "typed: " + ch);
                addtoMessageQueue(ch);
            }
            AppInstance.getInstance().getAppKeyBindings().updateButtonViws();//updte the viewttt
        } else {
            Log.v(TAG, "typed: delete");
            addtoKeyPressQueue(Constants.BACKSPACE);///
        }

    }

    @Override
    public void afterTextChanged(Editable s) {
    }


}


/**
 * ctrl: 17
 * alt: 18
 * shift: 16
 * enter: 10
 * tab: 9
 * esc: 27
 * prntScr: 154
 * backspace: 524
 * delete: 127
 * backspace: 8
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 * <p>
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83
 */
/**
 * N: 78
 * T: 84
 * W: 87
 * R: 82
 * F: 70
 * Z: 90
 * C: 67
 * X: 88
 * V: 86
 * A: 65
 * O: 79
 * S: 83

 */

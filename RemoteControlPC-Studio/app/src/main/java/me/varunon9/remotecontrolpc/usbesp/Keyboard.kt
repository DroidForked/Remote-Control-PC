package me.varunon9.remotecontrolpc.usbesp

import me.varunon9.remotecontrolpc.usbesp.Constants.*


class Keyboard {
    val en_usblookup = HashMap<Int, Int>()
    val keymap = HashMap<CharSequence, Keypair>()

    val MODIFIER_NONE = 0
    val MODIFIER_CTRL = 1
    val MODIFIER_SHIFT = 2
    val MODIFIER_ALT = 4
    val MODIFIER_HOME = 8

    ////////CONSTANT//////////


    init {
        this.keymap.put(BACKSPACE, Keypair(8, null));
        this.keymap.put(TAB, Keypair(9, null));
        this.keymap.put(ENTER, Keypair(13, null));
        this.keymap.put(SHIFT, Keypair(16, null));
        this.keymap.put(CTRL, Keypair(17, null));
        this.keymap.put(ALT, Keypair(18, null));
        this.keymap.put(PAUSE, Keypair(19, null));
        this.keymap.put(CAPS_LOCK, Keypair(20, null));
        this.keymap.put(ESC, Keypair(27, null));
        this.keymap.put(" "/*Space bar*/, Keypair(32, null));
        this.keymap.put(PAGE_UP, Keypair(33, null));
        this.keymap.put(PAGE_DOWN, Keypair(34, null));
        this.keymap.put(END, Keypair(35, null));
        this.keymap.put(HOME, Keypair(36, null));
        this.keymap.put(ARROW_LEFT, Keypair(37, null));
        this.keymap.put(ARROW_UP, Keypair(38, null));
        this.keymap.put(ARROW_RIGHT, Keypair(39, null));
        this.keymap.put(ARROW_DOWN, Keypair(40, null));
        this.keymap.put(PRINT_SCRN, Keypair(44, null));
        this.keymap.put(INSERT, Keypair(45, null));
        this.keymap.put(DELETE, Keypair(46, null));
        this.keymap.put("0", Keypair(48, 48));
        this.keymap.put("1", Keypair(49, 49));
        this.keymap.put("2", Keypair(50, 50));
        this.keymap.put("3", Keypair(51, 51));
        this.keymap.put("4", Keypair(52, 52));
        this.keymap.put("5", Keypair(53, 53));
        this.keymap.put("6", Keypair(54, 54));
        this.keymap.put("7", Keypair(55, 55));
        this.keymap.put("8", Keypair(56, 56));
        this.keymap.put("9", Keypair(57, 57));
        this.keymap.put("A", Keypair(65, 65));
        this.keymap.put("B", Keypair(66, 66));
        this.keymap.put("C", Keypair(67, 67));
        this.keymap.put("D", Keypair(68, 68));
        this.keymap.put("E", Keypair(69, 69));
        this.keymap.put("F", Keypair(70, 70));
        this.keymap.put("G", Keypair(71, 71));
        this.keymap.put("H", Keypair(72, 72));
        this.keymap.put("I", Keypair(73, 73));
        this.keymap.put("J", Keypair(74, 74));
        this.keymap.put("K", Keypair(75, 75));
        this.keymap.put("L", Keypair(76, 76));
        this.keymap.put("M", Keypair(77, 77));
        this.keymap.put("N", Keypair(78, 78));
        this.keymap.put("O", Keypair(79, 79));
        this.keymap.put("P", Keypair(80, 80));
        this.keymap.put("Q", Keypair(81, 81));
        this.keymap.put("R", Keypair(82, 82));
        this.keymap.put("S", Keypair(83, 83));
        this.keymap.put("T", Keypair(84, 84));
        this.keymap.put("U", Keypair(85, 85));
        this.keymap.put("V", Keypair(86, 86));
        this.keymap.put("W", Keypair(87, 87));
        this.keymap.put("X", Keypair(88, 88));
        this.keymap.put("Y", Keypair(89, 89));
        this.keymap.put("Z", Keypair(90, 90));
        this.keymap.put(NUMPAD_ZERO, Keypair(96, null));
        this.keymap.put(NUMPAD_ONE, Keypair(97, null));
        this.keymap.put(NUMPAD_TWO, Keypair(98, null));
        this.keymap.put(NUMPAD_THREE, Keypair(99, null));
        this.keymap.put(NUMPAD_FOUR, Keypair(100, null));
        this.keymap.put(NUMPAD_FIVE, Keypair(101, null));
        this.keymap.put(NUMPAD_SIX, Keypair(102, null));
        this.keymap.put(NUMPAD_SEVEN, Keypair(103, null));
        this.keymap.put(NUMPAD_EIGHT, Keypair(104, null));
        this.keymap.put(NUMPAD_NINE, Keypair(105, null));
        this.keymap.put(NUMPAD_STAR, Keypair(106, null));
        this.keymap.put(NUMPAD_PLUS, Keypair(107, null));
        this.keymap.put(NUMPAD_MINUS, Keypair(109, null));
        this.keymap.put(NUMPAD_DOT, Keypair(110, null));
        this.keymap.put(NUMPAD_SLASH, Keypair(111, null));
        this.keymap.put(F1, Keypair(112, null));
        this.keymap.put(F2, Keypair(113, null));
        this.keymap.put(F3, Keypair(114, null));
        this.keymap.put(F4, Keypair(115, null));
        this.keymap.put(F5, Keypair(116, null));
        this.keymap.put(F6, Keypair(117, null));
        this.keymap.put(F7, Keypair(118, null));
        this.keymap.put(F8, Keypair(119, null));
        this.keymap.put(F9, Keypair(120, null));
        this.keymap.put(F10, Keypair(121, null));
        this.keymap.put(F11, Keypair(122, null));
        this.keymap.put(F12, Keypair(123, null));
        this.keymap.put(NUM_LOCK, Keypair(144, null));
        this.keymap.put(SCROLL_LOCK, Keypair(145, null));
        this.keymap.put(";", Keypair(186, 186));
        this.keymap.put("=", Keypair(187, 187));
        this.keymap.put(",", Keypair(188, 188));
        this.keymap.put("-", Keypair(189, 189));
        this.keymap.put(".", Keypair(190, 190));
        this.keymap.put("/", Keypair(191, 191));
        this.keymap.put("`", Keypair(192, 192));
        this.keymap.put("[", Keypair(219, 219));
        this.keymap.put("\\", Keypair(220, 220));
        this.keymap.put("]", Keypair(221, 221));
        this.keymap.put("'", Keypair(222, 222));


        /////////////shift/////////

        this.keymap.put(")", Keypair(48, 41));
        this.keymap.put("!", Keypair(49, 33));
        this.keymap.put("@", Keypair(50, 64));
        this.keymap.put("#", Keypair(51, 35));
        this.keymap.put("$", Keypair(52, 36));
        this.keymap.put("%", Keypair(53, 37));
        this.keymap.put("^", Keypair(54, 94));
        this.keymap.put("&", Keypair(55, 38));
        this.keymap.put("*", Keypair(56, 42));
        this.keymap.put("(", Keypair(57, 40));
        this.keymap.put(":", Keypair(186, 58));
        this.keymap.put("+", Keypair(187, 43));
        this.keymap.put("<", Keypair(188, 60));
        this.keymap.put("_", Keypair(189, 95));
        this.keymap.put(">", Keypair(190, 62));
        this.keymap.put("?", Keypair(191, 63));
        this.keymap.put("~", Keypair(192, 126));
        this.keymap.put("{", Keypair(219, 123));
        this.keymap.put("|", Keypair(220, 124));
        this.keymap.put("}", Keypair(221, 125));
        this.keymap.put("\"", Keypair(222, 34));

        //////////////////////////

        this.keymap.put("a", Keypair(65, 97));
        this.keymap.put("b", Keypair(66, 98));
        this.keymap.put("c", Keypair(67, 99));
        this.keymap.put("d", Keypair(68, 100));
        this.keymap.put("e", Keypair(69, 101));
        this.keymap.put("f", Keypair(70, 102));
        this.keymap.put("g", Keypair(71, 103));
        this.keymap.put("h", Keypair(72, 104));
        this.keymap.put("i", Keypair(73, 105));
        this.keymap.put("j", Keypair(74, 106));
        this.keymap.put("k", Keypair(75, 107));
        this.keymap.put("l", Keypair(76, 108));
        this.keymap.put("m", Keypair(77, 109));
        this.keymap.put("n", Keypair(78, 110));
        this.keymap.put("o", Keypair(79, 111));
        this.keymap.put("p", Keypair(80, 112));
        this.keymap.put("q", Keypair(81, 113));
        this.keymap.put("r", Keypair(82, 114));
        this.keymap.put("s", Keypair(83, 115));
        this.keymap.put("t", Keypair(84, 116));
        this.keymap.put("u", Keypair(85, 117));
        this.keymap.put("v", Keypair(86, 118));
        this.keymap.put("w", Keypair(87, 119));
        this.keymap.put("x", Keypair(88, 120));
        this.keymap.put("y", Keypair(89, 121));
        this.keymap.put("z", Keypair(90, 122));


        this.en_usblookup.put(8, 42)
        this.en_usblookup.put(9, 43)
        this.en_usblookup.put(12, 40)
        this.en_usblookup.put(13, 40)
        this.en_usblookup.put(16, 225)
        this.en_usblookup.put(17, 224)
        this.en_usblookup.put(18, 226)
        this.en_usblookup.put(20, 57)
        this.en_usblookup.put(27, 41)
        this.en_usblookup.put(32, 44)
        this.en_usblookup.put(33, 30)
        this.en_usblookup.put(34, 52)
        this.en_usblookup.put(35, 32)
        this.en_usblookup.put(36, 33)
        this.en_usblookup.put(37, 34)
        this.en_usblookup.put(38, 36)
        this.en_usblookup.put(39, 52)
        this.en_usblookup.put(40, 38)
        this.en_usblookup.put(41, 39)
        this.en_usblookup.put(42, 37)
        this.en_usblookup.put(43, 46)
        this.en_usblookup.put(44, 54)
        this.en_usblookup.put(45, 45)
        this.en_usblookup.put(46, 55)
        this.en_usblookup.put(47, 56)
        this.en_usblookup.put(48, 39)
        this.en_usblookup.put(49, 30)
        this.en_usblookup.put(50, 31)
        this.en_usblookup.put(51, 32)
        this.en_usblookup.put(52, 33)
        this.en_usblookup.put(53, 34)
        this.en_usblookup.put(54, 35)
        this.en_usblookup.put(55, 36)
        this.en_usblookup.put(56, 37)
        this.en_usblookup.put(57, 38)
        this.en_usblookup.put(58, 51)
        this.en_usblookup.put(59, 51)
        this.en_usblookup.put(60, 54)
        this.en_usblookup.put(61, 46)
        this.en_usblookup.put(62, 55)
        this.en_usblookup.put(63, 56)
        this.en_usblookup.put(64, 31)
        this.en_usblookup.put(65, 4)
        this.en_usblookup.put(66, 5)
        this.en_usblookup.put(67, 6)
        this.en_usblookup.put(68, 7)
        this.en_usblookup.put(69, 8)
        this.en_usblookup.put(70, 9)
        this.en_usblookup.put(71, 10)
        this.en_usblookup.put(72, 11)
        this.en_usblookup.put(73, 12)
        this.en_usblookup.put(74, 13)
        this.en_usblookup.put(75, 14)
        this.en_usblookup.put(76, 15)
        this.en_usblookup.put(77, 16)
        this.en_usblookup.put(78, 17)
        this.en_usblookup.put(79, 18)
        this.en_usblookup.put(80, 19)
        this.en_usblookup.put(81, 20)
        this.en_usblookup.put(82, 21)
        this.en_usblookup.put(83, 22)
        this.en_usblookup.put(84, 23)
        this.en_usblookup.put(85, 24)
        this.en_usblookup.put(86, 25)
        this.en_usblookup.put(87, 26)
        this.en_usblookup.put(88, 27)
        this.en_usblookup.put(89, 28)
        this.en_usblookup.put(90, 29)
        this.en_usblookup.put(91, 47)
        this.en_usblookup.put(92, 49)
        this.en_usblookup.put(93, 48)
        this.en_usblookup.put(94, 35)
        this.en_usblookup.put(95, 45)
        this.en_usblookup.put(96, 53)
        this.en_usblookup.put(97, 4)
        this.en_usblookup.put(98, 5)
        this.en_usblookup.put(99, 6)
        this.en_usblookup.put(100, 7)
        this.en_usblookup.put(101, 8)
        this.en_usblookup.put(102, 9)
        this.en_usblookup.put(103, 10)
        this.en_usblookup.put(104, 11)
        this.en_usblookup.put(105, 12)
        this.en_usblookup.put(106, 13)
        this.en_usblookup.put(107, 14)
        this.en_usblookup.put(108, 15)
        this.en_usblookup.put(109, 16)
        this.en_usblookup.put(110, 17)
        this.en_usblookup.put(111, 18)
        this.en_usblookup.put(112, 19)
        this.en_usblookup.put(113, 20)
        this.en_usblookup.put(114, 21)
        this.en_usblookup.put(115, 22)
        this.en_usblookup.put(116, 23)
        this.en_usblookup.put(117, 24)
        this.en_usblookup.put(118, 25)
        this.en_usblookup.put(119, 26)
        this.en_usblookup.put(120, 27)
        this.en_usblookup.put(121, 28)
        this.en_usblookup.put(122, 29)
        this.en_usblookup.put(123, 47)
        this.en_usblookup.put(124, 49)
        this.en_usblookup.put(125, 48)
        this.en_usblookup.put(126, 53)
        this.en_usblookup.put(173, 127)

    }


    fun lookup(keyboard_value: CharSequence): Keypair? {
        return this.keymap.get(keyboard_value)
    }


    fun lookupforchar(ch: Char): Keypair? {
        val keypair = lookup(ch.toString())
        return keypair?.let { Keypair(it.presskey, ch.toInt()) }
    }
}

class Keypair(val presskey: Int?, val actualkey: Int?) {
    var is_caplocked_charactor = false
    var is_shifted_charactor = false;

    init {

        if (actualkey != null) {
            if (actualkey >= 65 && actualkey <= 90) {
                is_caplocked_charactor = true
            }
            is_shifted_charactor=Utility.check_is_shifted_character(actualkey)
        }

    }

    fun keyvalue(Usblookup: HashMap<Int, Int>): Int? {
        if (actualkey == null) {
            return Usblookup.get(presskey);
        }
        return Usblookup.get(actualkey);
    }
}


package com.maarek.kosar;

import com.maarek.kosar.ConstHolder;

/**
 * Created by jlyman on 10/13/15.
 */
public class Const {
    public static void constDemo() {
        int value = ConstHolder.MY_CONST;

        switch (value) {
            case ConstHolder.MY_CONST:
                System.out.println("MY_CONST: "+value);
                break;
            default:
                break;
        }
    }
}

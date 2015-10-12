package com.maarek.kosar;

import com.maarek.kosar.scope.VisibleScope;

/**
 * Created by jlyman on 10/12/15.
 */
public class Scope extends VisibleScope {
    public static class C4 extends NestedStaticClass {}

    public Scope() {
        new NestedStaticClass();
    }
}

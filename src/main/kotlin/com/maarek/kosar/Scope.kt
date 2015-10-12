package com.maarek.kosar

import com.maarek.kosar.scope.HiddenScope
import com.maarek.kosar.scope.VisibleScope

/**
 * Created by jlyman on 10/12/15.
 */

class KotlinScope() : VisibleScope() {
    class C1() : HiddenScope.NestedStaticClass()

    init {
        val v = HiddenScope.NestedStaticClass()
    }
}

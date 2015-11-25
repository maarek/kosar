package com.maarek.kosar

import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.async
import nl.komponents.kovenant.combine.combine
import nl.komponents.kovenant.then

/**
 * Created by jlyman on 11/25/15.
 */
public object Promises {

    public fun kAsyncAddition(): Promise<Int, Exception> = async { 1 + 1 }

    public fun kAsyncMultiply(): Promise<Int, Exception> = async { (1..20).sumBy {it -> it * 5} }

    public fun kAsyncDemo() {
        kAsyncAddition().then {
            i -> "Kovenant Async Result: $i"
        } success {
            msg -> println(msg)
        }
    }

    public fun kCombineDemo() {
        combine(kAsyncAddition(), kAsyncMultiply()) success {
            val (add, mul) = it

            println("Combine Result: $add, $mul")
        }
    }

}

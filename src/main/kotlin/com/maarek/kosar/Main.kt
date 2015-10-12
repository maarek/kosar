package com.maarek.kosar

import co.paralleluniverse.kotlin.fiber
import co.paralleluniverse.strands.Strand
import co.paralleluniverse.strands.dataflow.Val

/**
 * Created by jlyman on 10/1/15.
 */
public object Main {
    fun main(args: Array<String>) {

        Fibers.fibersDemo()
//        Fibers.channelsDemo()
        Fibers.reactiveDemo()
        Actors.actorsDemo()
        Reactive.observerDemo()
        Futures.futuresDemo()
        Futures.completableFuturesDemo()

        stringExtensionDemo()
    }

    fun stringExtensionDemo() {
        System.out.println("Hello World!".replace(Pair("e","w"),Pair("o","a")))
    }

    fun String.replace(vararg parameters: Pair<String, String>) =
            this.replace("", "", parameters)

    fun String.replace(prefix: String, suffix: String, parameters: Array<out Pair<String, String>>) =
            parameters.fold(this, { result, pair -> result.replace(prefix + pair.first + suffix, pair.second) })
}

fun main(args: Array<String>) = Main.main(args)

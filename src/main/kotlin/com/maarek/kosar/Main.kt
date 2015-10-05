package com.maarek.kosar

import co.paralleluniverse.kotlin.fiber
import co.paralleluniverse.strands.Strand
import co.paralleluniverse.strands.dataflow.Val

/**
 * Created by jlyman on 10/1/15.
 */
public object Main {
    fun main(args: Array<String>) {
        Fibers.futuresDemo()
        Fibers.channelsDemo()
        Fibers.reactiveDemo()
        Actors.actorsDemo()
        Reactive.observerDemo()
        Futures.futuresDemo()
        Futures.completableFuturesDemo()
    }
}

fun main(args: Array<String>) = Main.main(args)

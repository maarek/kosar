package com.maarek.kosar

import co.paralleluniverse.actors.ActorRef
import co.paralleluniverse.actors.BasicActor
import co.paralleluniverse.fibers.Suspendable
import co.paralleluniverse.kotlin.spawn

/**
 * Created by jlyman on 10/1/15.
 */
public object Actors {

    class MyActor: BasicActor<String, Unit>() {
        @Suspendable
        override fun doRun() {
            var running: Boolean  = true

            while (running) {
                receive {
                    when (it) {
                        "Hello" -> System.out.println("$it World")
                        "Exit" -> running = false
                        else -> System.out.println("$it Error")
                    }
                }
            }
        }
    }

    fun actorsDemo() {
        val pid = spawn(MyActor())

        pid.send("Hello")
        pid.send("Exit")
        pid.send("Cheese")
    }
}

package com.maarek.kosar

import co.paralleluniverse.fibers.Fiber
import co.paralleluniverse.kotlin.fiber
import co.paralleluniverse.strands.Strand
import co.paralleluniverse.strands.channels.Channel
import co.paralleluniverse.strands.channels.Channels
import co.paralleluniverse.strands.channels.ReceivePort
import co.paralleluniverse.strands.channels.Topic
import co.paralleluniverse.strands.dataflow.Val
import co.paralleluniverse.strands.dataflow.Var
import java.util.concurrent.ThreadLocalRandom

/**
 * Created by jlyman on 10/1/15.
 */
public object Fibers {

    public fun fibersDemo() {
        val x: Val<Int> = Val()
        val y: Var<Int> = Var()
        val z: Val<String> = Val({ -> "0) x + y = ${x.get() + y.get()}"})

        fiber {
            System.out.println("1) x + y = ${x.get() + y.get()}")
        }

        fiber {
            for (i in 1..10) System.out.println("${i+1}) x + y = ${x.get() + y.get()}")
        }

        fiber {
            Strand.sleep(1000)
            x.set(5)
        }

        fiber {
            Strand.sleep(400)
            y.set(12)
        }

        fiber {
            Strand.sleep(1008)
            y.set(15)
        }

        fiber {
            System.out.println(z.get())
        }
    }

    public fun channelsDemo() {
        val x: Val<Int> = Val()
        val t: Topic<Int> = Topic()

        fiber {
            val c: Channel<Int> = t.subscribe(Channels.newChannel(0))
            do {
                val m = c.receive()
                System.out.println("Channel => " + (m + x.get()))
            } while (m != null)
        }

        x.set(13)

        t.send(5)
        t.close()
    }

    public fun reactiveDemo() {
        val t: Topic<Int> = Topic()

        fiber {
            for (i in 1..10) {
                Strand.sleep(100)
                t.send(ThreadLocalRandom.current().nextInt(100))
            }
            t.close()
        }

        fiber {
            val c: ReceivePort<Int> = t.subscribe(Channels.newChannel(0))
            do {
                val m = c.receive()
                System.out.println("-> " + m)
            } while (m != null)
        }

        fiber {
            val c: ReceivePort<String> = Channels.map(t.subscribe(Channels.newChannel(0)),
                    { x: Int -> "my number is: " + x}) // transform the channel
            do {
                val m = c.receive()
                System.out.println("=> " + m)
            } while (m != null)
        }
    }
}

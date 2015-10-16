package com.maarek.kosar

/**
 * Created by jlyman on 10/14/15.
 */
object Singleton {
    private var myData: String = ""

    fun init(data: String)  {
        myData = data
    }

    fun singletonDemo() {
        System.out.println("Singleton Data: ${myData}")
    }
}

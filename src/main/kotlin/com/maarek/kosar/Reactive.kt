package com.maarek.kosar

import rx.Observable
import rx.lang.kotlin.observable
import rx.lang.kotlin.toObservable
import kotlin.concurrent.thread

/**
 * Created by jlyman on 10/1/15.
 */
public object Reactive {

    val printIt = { it: String -> println(it) }

    public fun syncObservable(): Observable<String> =
            observable { subscriber ->
                (0..5).toObservable()
                        .map { "Sync value_$it" }
                        .subscribe(subscriber)
            }

    public fun asyncObservable(): Observable<String> =
            observable { subscriber ->
                thread {
                    (0..5).toObservable()
                            .map { "Async value_$it" }
                            .subscribe(subscriber)
                }
            }

    public fun observerDemo() {

        syncObservable().subscribe(printIt)
        asyncObservable().subscribe(printIt)
    }
}


package org.example
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withTimeoutOrNull

/**
 * A suspending function asynchronously returns a single value, but how can we return multiple asynchronously computed values? This is where Kotlin Flows come in.
 */

class AsynchronousFlow {

    // run on main thread
    fun simple (): List<Int> {
        return listOf(1,2,3,4)
    }

    // return with Sequences : run on main thread
    fun sequencesFn ():Sequence<Int> = sequence {
        for (i in 0..3) {
            Thread.sleep(100)
            yield(i)
        }
    }

    // Suspending functions run in asynchronous thread so this is non blocking main thread
    suspend fun asynchronousFn (): List<Int> {
        delay(1000)
        return listOf(1,2,3,4,5,6)
    }

    // Flows : run on asynchronous thread
    fun flowFn (): Flow<Int> = flow {
        for (i in 0..3) {
            delay(1000)
            emit(i)
        }
    }

    // utils
    suspend fun performRequest(request: Int): String {
        delay(1000) // imitate long-running asynchronous work
        return "response $request"
    }

    fun numbers(): Flow<Int> = flow {
        try {
            emit(1)
            emit(2)
            println("This line will not execute")
            emit(3)
        } finally {
            println("Finally in numbers")
        }
    }
}

suspend fun main() {
    val flow = AsynchronousFlow()
    // flow.simple().forEach { value -> println(value) }
    // flow.sequencesFn().forEach { it -> println(it) }
    // flow.asynchronousFn().forEach { it -> println(it) }
    // flow.flowFn().collect { it -> println(it) }

    // Flow cancellation
    // withTimeoutOrNull (2000) { // cancel after 2000ms
    //    flow.flowFn().collect { it -> println(it) }
    //}

    // Flow builders
    // (1..3).asFlow().collect { it -> println(it) }

    // Intermediate flow operators
    // (1..3).asFlow().map { it -> flow.performRequest(it) }.collect { value -> println(value) }

    // Transform operator
    // (1..3).asFlow().transform { req ->
     //   emit("Making request $req")
    //    emit(flow.performRequest(req))
    // }.collect { it -> println(it) }

    // Size-limiting operators
    // flow.numbers().take(2).collect { it -> println(it) }

    // Terminal flow operators
    // val sum = (1..3).asFlow().map { it * it }.reduce { a,b -> a+b }
    // println(sum)

    // Flows are sequential
    (1..5).asFlow().filter { it % 2 == 0 }.map { "String$it" }.collect { println("Collect $it") }
}

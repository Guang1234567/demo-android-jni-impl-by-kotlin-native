package sample

import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.waitForMultipleFutures

actual class Sample {
    actual fun checkMe() = 7
}

actual object Platform {
    actual val name: String = "Macos"
}


data class WorkerArgument(val intParam: Int, val stringParam: String)
data class WorkerResult(val intResult: Int, val stringResult: String)

fun main() {
    val COUNT = 5
    val workers = Array(COUNT) { Worker.start() }

    for (attempt in 1..3) {
        println("start stattempt : $attempt")
        val futures = Array(workers.size) { workerIndex ->
            workers[workerIndex].execute(TransferMode.SAFE, {
                WorkerArgument(workerIndex, "attempt $attempt")
            }) { input ->
                var sum = 0
                for (i in 0..input.intParam * 1000) {
                    sum += i
                }
                WorkerResult(sum, input.stringParam + " result")
            }
        }
        val futureSet = futures.toSet()
        var consumed = 0
        while (consumed < futureSet.size) {
            println("consumed : $consumed")
            val ready = waitForMultipleFutures(futureSet, 10000)
            println("waitForMultipleFutures $ready")
            ready.forEach {
                it.consume { result ->
                    if (result.stringResult != "attempt $attempt result") throw Error("Unexpected $result")
                    consumed++
                }
            }
        }
        println("end stattempt : $attempt")
    }
    workers.forEach {
        it.requestTermination().result
    }
    println("Workers: OK")
}
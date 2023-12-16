package bfs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend inline fun IntArray.parallelFor(
    from: Int = 0,
    to: Int = size,
    batchSize: Int = 1000,
    crossinline f: CoroutineScope.(Int) -> Unit
) {
    coroutineScope {
        for (batchLeft in from until to step batchSize) {
            val batchRight = kotlin.math.min(to, batchLeft + batchSize)
            launch {
                for (index in batchLeft until batchRight) {
                    f(index)
                }
            }
        }
    }
}
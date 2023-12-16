package bfs

import array.AtomicBooleanArray
import graph.Graph
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger

@OptIn(ExperimentalCoroutinesApi::class)
fun Graph.parallelBFS(node: Int, parallelism: Int, f: Graph.(Int, Int) -> Unit) {
    runBlocking(context = Dispatchers.Default.limitedParallelism(parallelism)) {
        coroutineScope {
            val used = AtomicBooleanArray(numberOfNodes)
            used.compareAndSet(node, false, true)
            val frontier = IntArray(numberOfNodes)
            frontier[0] = node
            val pNext = AtomicInteger(1)
            var pPrev = 0

            while (!pNext.compareAndSet(pPrev, pPrev)) {
                val pNextCopy = pNext.get()
                frontier.parallelFor(from = pPrev, to = pNextCopy) { i ->
                    val cur = frontier[i]

                    for (next in neighbours(cur)) {
                        if (used.compareAndSet(next, false, true)) {
                            f(cur, next)
                            frontier[pNext.getAndIncrement()] = next
                        }
                    }
                }
                pPrev = pNextCopy
            }
        }
    }
}
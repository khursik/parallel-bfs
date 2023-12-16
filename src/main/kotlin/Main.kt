import bfs.parallelBFS
import bfs.sequentialBFS
import graph.GridGraph
import kotlin.system.measureTimeMillis

fun main() {
    val graph = GridGraph(500, dim = 3)
    val startNode = 0
    val seqDistance = IntArray(graph.numberOfNodes) { Int.MAX_VALUE }
    seqDistance[startNode] = 0
    val seqAvgTime = measureAverageTime {
        graph.sequentialBFS(startNode) { cur, next ->
            seqDistance[next] = seqDistance[cur] + 1
        }
    }
    println("Sequential BFS average time: $seqAvgTime")

    val parDistance = IntArray(graph.numberOfNodes) { Int.MAX_VALUE }
    parDistance[startNode] = 0
    val parAvgTime = measureAverageTime {
        graph.parallelBFS(startNode, parallelism = 4) { u, v ->
            parDistance[v] = parDistance[u] + 1
        }
    }
    println("Parallel BFS average time: $parAvgTime")
    println("Speedup: ${seqAvgTime.toDouble() / parAvgTime}")

    check(seqDistance.contentEquals(parDistance))
}

private fun measureAverageTime(
    iterations: Int = 5,
    f: () -> Unit
): Long {
    var totalSeqTime = 0L
    repeat(iterations) {
        val seqTime = measureTimeMillis {
            f()
        }
        totalSeqTime += seqTime
    }
    return totalSeqTime / iterations
}
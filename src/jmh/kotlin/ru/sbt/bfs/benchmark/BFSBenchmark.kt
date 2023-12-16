package ru.sbt.bfs.benchmark

import graph.GridGraph
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Suppress("unused")
@Fork(3)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
open class BFSBenchmark {
    protected lateinit var graph: GridGraph
    protected lateinit var seqDistance: IntArray
    protected lateinit var parDistance: IntArray

    @Param(value = ["500"])
    var sideLength: Int = 0

    @Param(value = ["3"])
    var dim = 0

    @Param(value=["0"])
    var startNode = 0

    @Setup(Level.Iteration)
    fun init() {
        graph = GridGraph(500, dim = 3)

        seqDistance = IntArray(graph.numberOfNodes) { Int.MAX_VALUE }
        seqDistance[startNode] = 0

        parDistance = IntArray(graph.numberOfNodes) { Int.MAX_VALUE }
        parDistance[startNode] = 0
    }
}
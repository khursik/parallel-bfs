package ru.sbt.bfs.benchmark

import bfs.parallelBFS
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param

@Suppress("unused")
open class ParallelBFSBenchmark : BFSBenchmark() {
    @Param(value = ["4"])
    var parallelism = 0

    @Benchmark
    fun parallel() {
        graph.parallelBFS(startNode, parallelism) { u, v ->
            parDistance[v] = parDistance[u] + 1
        }
    }
}
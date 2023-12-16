package ru.sbt.bfs.benchmark

import bfs.sequentialBFS
import org.openjdk.jmh.annotations.Benchmark

@Suppress("unused")
open class SequentialBFSBenchmark : BFSBenchmark() {

    @Benchmark
    fun sequential() {
        graph.sequentialBFS(startNode) { cur, next ->
            seqDistance[next] = seqDistance[cur] + 1
        }
    }
}
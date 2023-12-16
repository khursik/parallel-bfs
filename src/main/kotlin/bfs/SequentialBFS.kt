package bfs

import graph.Graph

fun Graph.sequentialBFS(node: Int, f: Graph.(Int, Int) -> Unit) {
    val used = BooleanArray(numberOfNodes)
    val deque = ArrayDeque<Int>()
    deque.addLast(node)
    used[node] = true

    while (deque.isNotEmpty()) {
        val cur = deque.removeFirst()

        for (next in neighbours(cur)) {
            if (!used[next]) {
                used[next] = true
                f(cur, next)
                deque.addLast(next)
            }
        }
    }
}
package graph

class GridGraph(sideLength: Int, dim: Int) : Graph {
    private val n = (sideLength + 1)
    override val numberOfNodes: Int = (sideLength + 1).pow(dim)
    override val neighbours: (node: Int) -> Neighbours = { node ->
        buildList {
            for (i in 0 until dim) {
                val shift = n pow i
                val next1 = node + shift
                val next2 = node - shift

                if ((next1 / shift) % n != 0 && next1 in 0 until numberOfNodes) {
                    add(next1)
                }
                if ((next2 / shift) % n != n - 1 && next2 in 0 until numberOfNodes) {
                    add(next2)
                }
            }
        }.toIntArray()
    }
}
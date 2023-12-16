package graph

class AdjacencyListGraph(override val numberOfNodes: Int, private val edges: Edges) : Graph {
    override val neighbours: (node: Int) -> Neighbours = edges::get
}

fun Graph.asAdjacencyList() = AdjacencyListGraph(numberOfNodes, Array(numberOfNodes, neighbours))
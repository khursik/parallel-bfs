package graph

typealias Neighbours = IntArray
typealias Edges = Array<Neighbours>

interface Graph {
    val numberOfNodes: Int
    val neighbours: (node: Int) -> Neighbours
}
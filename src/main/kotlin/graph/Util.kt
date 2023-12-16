package graph

import kotlin.math.pow

infix fun Int.pow(b: Int): Int {
    return toDouble().pow(b).toInt()
}
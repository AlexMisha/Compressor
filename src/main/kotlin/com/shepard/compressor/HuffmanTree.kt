package com.shepard.compressor

import java.util.*

fun huffmanTree(src: PriorityQueue<Node>, length: Int): HuffmanTree {
    val (left, right) = listOf<Node>(*src.toTypedArray()).take(2)
    val huffmanTree = HuffmanTree(left, right, length)

    src.asSequence().forEachIndexed { index, node -> if (index > 1) huffmanTree.put(node) }
    return huffmanTree.apply { check() }
}

class HuffmanTree(
        left: Node, right: Node,
        val length: Int,
        var top: Node = Node(count = left.count + right.count, left = left, right = right)) {

    fun put(node: Node) {
        val left = top
        top = Node(count = left.count + node.count, left = left, right = node)
    }

    fun check(): Boolean {
        var sum = 0
        val queue = PriorityQueue<Node>()
        queue.add(top)
        while (!queue.isEmpty()) {
            val node = queue.poll()
            node.apply {
                if (left != null) queue.add(left)
                if (right != null) queue.add(right)
            }
            if (node.char != null)
                sum += node.count
        }
        if (sum != length) throw HuffmanBuildException(length, sum)
        return sum == length
    }

    override fun toString(): String {
        return "HuffmanTree(top=$top)"
    }
}

data class Node(
        val char: Char? = null,
        val count: Int,
        val left: Node? = null,
        val right: Node? = null) : Comparable<Node> {
    override fun compareTo(other: Node) = count - other.count
}

data class HuffmanBuildException(
        val expectedCount: Int,
        val actualCount: Int
) : IllegalStateException()
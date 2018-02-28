package com.shepard.compressor

class HuffmanTree(
        left: Node, right: Node,
        val length: Int,
        var top: Node = Node(count = left.count + right.count, left = left, right = right)) {

    fun put(node: Node) {
        val left = top
        top = Node(count = left.count + node.count, left = left, right = node)
    }

    fun check(): Boolean {
        if (top.count != length) throw HuffmanBuildException(length, top.count)
        return top.count == length
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
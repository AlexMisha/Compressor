package com.shepard.compressor

import java.util.*

class HuffmanTree(
        left: Node, right: Node,
        val length: Int,
        var top: Node = Node(count = left.count + right.count, left = left, right = right)) {

    fun put(node: Node, provider: () -> Node) {
        val right =
                if (node.count <= top.count && provider().count <= node.count)
                    onConflict(node, provider)
                else node

        val left = top
        top = Node(count = left.count + node.count, left = left, right = right)
    }

    fun check(): Boolean {
        if (top.count != length) throw HuffmanBuildException(length, top.count)
        return top.count == length
    }

    override fun toString(): String {
        return "HuffmanTree(top=$top)"
    }

    fun contains(src: Node): Boolean {
        if (src == top) return true
        val queue = PriorityQueue<Node>().apply { add(top) }
        while (!queue.isEmpty()) {
            val node = queue.poll()
            if (node.left == src || node.right == src) return true
            if (node.left != null) queue.add(node.left)
            if (node.right != null) queue.add(node.right)
        }
        return false
    }

    private inline fun onConflict(node: Node, provider: () -> Node) =
            Node(count = node.count + provider().count, left = node, right = provider())
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
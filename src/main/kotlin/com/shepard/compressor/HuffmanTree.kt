package com.shepard.compressor

inline fun String.countChars(countProvider: (count: Int) -> Unit): MutableList<Node> {
    var count = 0;
    val result = srcSet
            .asSequence()
            .map { src -> Node(src, count { c -> c == src }) }
            .filter { it.count != 0 }
            .onEach { count += it.count }
            .toMutableList()
            .apply { sort() }
    countProvider(count)
    return result
}

fun buildTree(list: MutableList<Node>): Node {
    while (list.size > 1) {
        list.twoMin { first, second -> list.add(0, first bind second) }
        list.sort()
    }
    return list.single()
}

fun Node.createKeys(): List<CharKey> {
    fun preOrder(node: Node, code: String, set: MutableSet<CharKey>) {
        set.add(CharKey(node.char, code))
        if (node.left == null && node.right == null) {
            set.add(CharKey(node.char, code))
            return
        }
        if (node.left != null) preOrder(node.left, code + 0, set)
        if (node.right != null) preOrder(node.right, code + 1, set)
    }

    val set = mutableSetOf<CharKey>()
    preOrder(this, "", set)
    return set.filter { it.char != null }
}

inline fun <T : Comparable<T>> MutableList<T>.twoMin(result: (first: T, second: T) -> Unit) {
    val first = this.min() ?: throw IllegalArgumentException("Can't find minimal element of list [$this]")
    this.remove(first)
    val second = this.min() ?: throw IllegalArgumentException("Can't find minimal element of list [$this]")
    this.remove(second)
    result(first, second)
}

infix fun Node.bind(node: Node): Node = Node(count = this.count + node.count, left = this, right = node)

infix fun Node.correspondsTo(count: Int) = this.count == count

data class CharKey(
        val char: Char? = null,
        val code: String
) {
    override fun toString() = "[$char] - [$code]"
}

data class Node(
        val char: Char? = null,
        val count: Int,
        val left: Node? = null,
        val right: Node? = null) : Comparable<Node> {
    override fun compareTo(other: Node) = count - other.count
}

class HuffmanTreeBuildException(top: Node, count: Int, text: String) :
        IllegalStateException("Top doesn't correspond to text \n top [$top] \n chars count [$count] \n text [$text]")

val srcSet = setOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
        'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ',
        ',', '.', '!', '?', '\u0410', '\u0411', '\u0412', '\u0413', '\u0414', '\u0415', '\u0416', '\u0417',
        '\u0418', '\u0419', '\u041A', '\u041B', '\u041C', '\u041D', '\u041E', '\u041F', '\u0420', '\u0421',
        '\u0422', '\u0423', '\u0424', '\u0425', '\u0426', '\u0427', '\u0428', '\u0429', '\u042A', '\u042B',
        '\u042C', '\u042D', '\u042E', '\u042F', '\u0430', '\u0431', '\u0432', '\u0433', '\u0434', '\u0435',
        '\u0436', '\u0437', '\u0438', '\u0439', '\u043A', '\u043B', '\u043C', '\u043D', '\u043E', '\u043F',
        '\u0440', '\u0441', '\u0442', '\u0443', '\u0444', '\u0445', '\u0446', '\u0447', '\u0448', '\u0449',
        '\u044A', '\u044B', '\u044C', '\u044D', '\u044E', '\u044F', '\u23CE')
package com.shepard.compressor

import java.io.File
import java.nio.charset.Charset
import java.util.*

fun read(path: String) = File(path).readText(Charset.forName("windows-1251"))

fun countedQueue(text: String): Pair<PriorityQueue<Node>, Int> {
    val charSequence = text.toList().asSequence()
    var length = 0
    val list: List<Node> = srcSet
            .asSequence()
            .map { src -> Node(src, charSequence.count { c -> c == src }) }
            .filter { it.count != 0 }
            .onEach { length += it.count }
            .toList()
    return Pair(PriorityQueue(list.sorted()), length)
}

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
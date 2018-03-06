package com.shepard

import com.shepard.compressor.*
import java.io.File
import java.nio.charset.Charset

fun main(args: Array<String>) {
    val charset = Charset.forName("windows-1251")
    File("src/main/resources/").walkTopDown().forEachIndexed { index, file ->
        if (index == 0) return@forEachIndexed
        val resultMatcher = ResultMatcher()

        val text = file.readText(charset)

        var count = 0
        val tree = buildTree(text.countChars { count = it })

        resultMatcher.apply {
            this.text = text
            top = tree
            ensure(count) { tree correspondsTo it }
        }

        val keys = tree.createKeys()
        println(keys)
    }
}

class ResultMatcher {
    lateinit var text: String
    lateinit var top: Node

    fun ensure(count: Int, matcher: (count: Int) -> Boolean) {
        if (!matcher(count)) throw HuffmanTreeBuildException(top, count, text)
    }

}
package com.shepard

import com.shepard.compressor.buildTree
import com.shepard.compressor.correspondsTo
import com.shepard.compressor.countChars
import com.shepard.compressor.createKeys
import java.io.File
import java.nio.charset.Charset

fun main(args: Array<String>) {
    val charset = Charset.forName("windows-1251")
    File("src/main/resources/").walkTopDown().forEachIndexed { index, file ->
        if (index == 0) return@forEachIndexed

        val text = file.readText(charset)
        var count = 0
        val tree = buildTree(text.countChars { count = it })
        tree correspondsTo text

        val keys = tree.createKeys()
        println(keys)
    }
}
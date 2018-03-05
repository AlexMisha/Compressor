package com.shepard

import com.shepard.compressor.buildTree
import com.shepard.compressor.countChars
import com.shepard.compressor.createKeys
import java.io.File
import java.nio.charset.Charset

fun main(args: Array<String>) {
    val charset = Charset.forName("windows-1251")
    File("src/main/resources/").walkTopDown().forEachIndexed { index, file ->
        if (index == 0) return@forEachIndexed
        val keys = buildTree(file.readText(charset).countChars()).createKeys()
        println(keys)
    }
}
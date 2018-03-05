package com.shepard

import com.shepard.compressor.buildTree
import com.shepard.compressor.countChars
import com.shepard.compressor.createKeys
import java.io.File
import java.nio.charset.Charset

fun main(args: Array<String>) {
    val tree = buildTree(read("src/main/resources/1.txt").countChars())
    println(tree.createKeys())
}

fun read(path: String) = File(path).readText(Charset.forName("windows-1251"))
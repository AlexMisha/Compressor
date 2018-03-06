package com.shepard

import com.shepard.compressor.*
import java.io.File
import java.nio.charset.Charset
import java.util.*

fun main(args: Array<String>) {
    val charset = Charset.forName("windows-1251")
    File("src/main/resources/").walkTopDown().forEachIndexed { index, file ->
        if (index == 0) return@forEachIndexed

        println("File #$index")

        val text = file.readText(charset)
        val chars = text.countChars()

        val tree = buildTree(mutableListOf(*Arrays.copyOf(chars.toTypedArray(), chars.size)))
        tree correspondsTo text

        val keys = tree.createKeys()
        println(keys)

        val inputSize: Float = (text.length * 8).toFloat()
        val outputSize: Float = keys.sumBy { it.code.length * (chars findBy it).count }.toFloat()

        println("Output size: [$outputSize]")
        println("K: [${inputSize / outputSize}]")
        println()
    }
}

infix fun List<Node>.findBy(charKey: CharKey): Node {
    return this.find { it.char == charKey.char }
            ?: throw IllegalArgumentException("Can't find by given charKey [$charKey] \n list is $this")
}
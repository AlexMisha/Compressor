package com.shepard

import com.shepard.compressor.buildTree
import com.shepard.compressor.correspondsTo
import com.shepard.compressor.countChars
import com.shepard.compressor.createKeys
import org.junit.Test
import java.io.File
import java.util.*

class TestAlgorythm {

    @Test
    fun test() {
        val text = File("src/main/resources/test.txt").readText()
        val chars = text.countChars()

        val tree = buildTree(mutableListOf(*Arrays.copyOf(chars.toTypedArray(), chars.size)))
        tree correspondsTo text

        val keys = tree.createKeys()
        println(keys)

        val inputSize: Float = (text.length * 8).toFloat()
        val outputSize: Float = keys.sumBy { it.code.length * (chars findBy it).count }.toFloat()

        println("Output size: [$outputSize]")
        println("K: [${inputSize / outputSize}]")
    }
}
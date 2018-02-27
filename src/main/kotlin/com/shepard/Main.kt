package com.shepard

import com.shepard.compressor.countedQueue
import com.shepard.compressor.huffmanTree
import com.shepard.compressor.read

fun main(args: Array<String>) {
    val countedQueue = countedQueue(read("src/main/resources/1.txt"))
    println(countedQueue.first)
    val huffmanTree = huffmanTree(countedQueue.first, countedQueue.second)
    println(huffmanTree)
}
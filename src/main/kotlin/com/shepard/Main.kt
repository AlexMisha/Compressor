package com.shepard

import com.shepard.compressor.countedQueue
import com.shepard.compressor.read
import com.shepard.compressor.toHuffmanTree

fun main(args: Array<String>) {
    val countedQueue = countedQueue(read("src/main/resources/1.txt"))
    println(countedQueue.first)
    val huffmanTree = countedQueue.toHuffmanTree()
    println(huffmanTree)
}
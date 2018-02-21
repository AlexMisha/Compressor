package com.shepard.compressor

import java.io.File
import java.nio.charset.Charset

fun read(path: String) = File(path).inputStream().bufferedReader(Charset.defaultCharset())


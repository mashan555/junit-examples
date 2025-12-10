/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project

/** Returns numbers from 1 through [n] (inclusive). */
fun numbersUpTo(n: Int): List<Int> {
    require(n >= 0) { "n must be non-negative" }
    return if (n == 0) emptyList() else (1..n).toList()
}

/** A tiny message used by the app. */
fun banner(): String = "It is a Kotlin file"

fun main() {
    println(banner())
    numbersUpTo(10).forEach(::println)
    // prints the single value from the old example
    println(1)
}

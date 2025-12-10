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

/**
 * Utility that prints numbers from 1 to 100, one per line.
 */
object Printer {

    @JvmStatic
    fun printNumbers1To100() {
        for (i in 1..100) {
            println(i)
        }
    }
}

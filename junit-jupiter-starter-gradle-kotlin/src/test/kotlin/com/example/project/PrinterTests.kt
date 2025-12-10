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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class PrinterTests {

    @Test
    fun `prints numbers from 1 to 100`() {
        val originalOut = System.out
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        System.setOut(ps)
        try {
            Printer.printNumbers1To100()
        } finally {
            System.setOut(originalOut)
        }
        val expected = (1..100).joinToString(System.lineSeparator()) + System.lineSeparator()
        assertEquals(expected, baos.toString())
    }
}

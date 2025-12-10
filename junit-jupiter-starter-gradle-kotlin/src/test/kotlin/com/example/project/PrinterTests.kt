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
    fun `prints 1 through 100 each on its own line`() {
        val originalOut = System.out
        val baos = ByteArrayOutputStream()
        System.setOut(PrintStream(baos, true))
        try {
            Printer.printNumbers1To100()
        } finally {
            System.setOut(originalOut)
        }
        val output = baos.toString().trim()
        val lines = output.split(Regex("\\r?\\n"))
        assertEquals(100, lines.size, "Should print 100 lines")
        assertEquals("1", lines.first())
        assertEquals("100", lines.last())
        for (i in 1..100) {
            assertEquals(i.toString(), lines[i - 1])
        }
    }
}

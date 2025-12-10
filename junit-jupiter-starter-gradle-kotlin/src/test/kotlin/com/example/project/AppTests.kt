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
import org.junit.jupiter.api.assertThrows

class AppTests {

    @Test
    fun numbersUpTo_returns_1_to_10() {
        assertEquals((1..10).toList(), numbersUpTo(10))
    }

    @Test
    fun numbersUpTo_rejects_negative() {
        val ex = assertThrows<IllegalArgumentException> { numbersUpTo(-1) }
        assertEquals("n must be non-negative", ex.message)
    }

    @Test
    fun banner_text_is_stable() {
        assertEquals("It is a Kotlin file", banner())
    }
}

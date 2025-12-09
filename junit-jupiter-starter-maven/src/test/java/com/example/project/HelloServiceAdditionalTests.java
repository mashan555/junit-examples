/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HelloServiceAdditionalTests {

	@Test
	@DisplayName("hello() is non-null and has expected length")
	void helloIsNotNullAndHasExpectedLength() {
		HelloService service = new HelloService();
		String msg = service.hello();
		assertAll(
			() -> assertNotNull(msg, "greeting should not be null"),
			() -> assertEquals("Hello, World!".length(), msg.length(), "unexpected greeting length")
		);
	}

	@Test
	@DisplayName("hello() starts with 'Hello' and ends with '!' ")
	void helloStartsAndEndsProperly() {
		String msg = new HelloService().hello();
		assertAll(
			() -> assertTrue(msg.startsWith("Hello"), "should start with 'Hello'"),
			() -> assertTrue(msg.endsWith("!"), "should end with '!' ")
		);
	}

	@Test
	@DisplayName("hello() contains comma and space after 'Hello'")
	void helloContainsCommaAndSpace() {
		assertTrue(new HelloService().hello().contains(", "), "should contain a comma and a space");
	}

	@RepeatedTest(3)
	@DisplayName("hello() returns the same value across repeated invocations")
	void helloIsStableAcrossInvocations() {
		assertEquals("Hello, World!", new HelloService().hello());
	}

	@ParameterizedTest(name = "equalsIgnoreCase matches for variant: {0}")
	@ValueSource(strings = {"Hello, World!", "hello, world!", "HELLO, WORLD!"})
	@DisplayName("hello() matches expected value ignoring case for common variants")
	void helloEqualsIgnoringCaseVariants(String expectedVariant) {
		String msg = new HelloService().hello();
		assertTrue(msg.equalsIgnoreCase(expectedVariant), () -> "expected '" + expectedVariant + "' (ignore case)");
	}

	@Test
	@DisplayName("hello() executes quickly")
	void helloExecutesQuickly() {
		assertTimeout(Duration.ofMillis(200), () -> new HelloService().hello());
	}

	@Test
	@DisplayName("Multiple HelloService instances behave consistently")
	void multipleInstancesReturnSameGreeting() {
		String expected = "Hello, World!";
		for (int i = 0; i < 5; i++) {
			assertEquals(expected, new HelloService().hello());
		}
	}
}

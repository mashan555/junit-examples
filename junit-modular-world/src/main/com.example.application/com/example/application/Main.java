/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.example.application;

import com.example.tool.Calculator;

/**
 * Application entry point that demonstrates simple modular usage.
 * <p>Behavior:
 * - Reads up to two integer arguments: a and b.
 * - If missing, defaults to a=1 and b=2.
 * - Uses com.example.tool.Calculator to compute a+b and prints "a + b = sum".
 * <p>Examples:
 *   java com.example.application.Main 3 5   -> "3 + 5 = 8"
 *   java com.example.application.Main       -> "1 + 2 = 3"
 */
class Main {

	/**
	 * Parses optional integer arguments and prints their sum using {@link com.example.tool.Calculator}.
	 */
	public static void main(String... args) {
		int a = args.length > 0 ? (Integer.valueOf(args[0])) : 1;
		int b = args.length > 1 ? (Integer.valueOf(args[1])) : 2;
		System.out.printf("%d + %d = %d%n", a, b, new Calculator().add(a, b));
	}

}

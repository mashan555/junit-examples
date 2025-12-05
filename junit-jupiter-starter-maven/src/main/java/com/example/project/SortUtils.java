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

import java.util.Objects;

/**
 * Utility class providing simple sorting functionality for demo purposes.
 * <p>
 * The {@link #sort(int[])} method returns a new array containing the elements of
 * the input array in ascending order using an in-memory insertion sort
 * implementation. The input array is never mutated.
 */
public final class SortUtils {

	private SortUtils() {
		// utility
	}

	/**
	 * Return a new array containing all values from {@code input} in ascending order.
	 * <p>
	 * This method intentionally implements insertion sort to keep the example
	 * self-contained and readable without relying on {@link java.util.Arrays#sort(int[])}.
	 *
	 * @param input the source array; must not be {@code null}
	 * @return a new array sorted in ascending order (never {@code null})
	 * @throws NullPointerException if {@code input} is {@code null}
	 */
	public static int[] sort(int[] input) {
		Objects.requireNonNull(input, "input must not be null");

		// create a copy so we don't mutate the caller's array
		int[] a = input.clone();

		// insertion sort (stable, O(n^2) worst/avg, O(1) extra space)
		for (int i = 1; i < a.length; i++) {
			int key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
		return a;
	}
}

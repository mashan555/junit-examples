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

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SortUtilsTests {

	@Test
	@DisplayName("sort() returns empty array when given empty array")
	void sortEmpty() {
		int[] input = {};
		int[] sorted = SortUtils.sort(input);
		assertArrayEquals(new int[]{}, sorted);
		// ensure original not mutated
		assertArrayEquals(new int[]{}, input);
	}

	@Test
	@DisplayName("sort() keeps single element array unchanged (non-mutating)")
	void sortSingle() {
		int[] input = {42};
		int[] copyBefore = input.clone();
		int[] sorted = SortUtils.sort(input);
		assertArrayEquals(new int[]{42}, sorted);
		assertArrayEquals(copyBefore, input, "input array must not be mutated");
	}

	@Test
	@DisplayName("sort() sorts reverse order, handles negatives and duplicates")
	void sortReverseWithNegativesAndDuplicates() {
		int[] input = {5, 4, 3, 2, 1, 0, -1, -2, 3, 2, 2};
		int[] sorted = SortUtils.sort(input);
		assertArrayEquals(new int[]{-2, -1, 0, 1, 2, 2, 2, 3, 3, 4, 5}, sorted);
		assertEquals(11, sorted.length);
		// original must remain unchanged
		assertArrayEquals(new int[]{5, 4, 3, 2, 1, 0, -1, -2, 3, 2, 2}, input);
	}

	@Test
	@DisplayName("sort() throws NPE for null input")
	void sortNull() {
		assertThrows(NullPointerException.class, () -> SortUtils.sort(null));
	}

	@ParameterizedTest(name = "randomized case #{index}")
	@MethodSource("randomIntArrays")
	void randomized(int[] input) {
		int[] expected = input.clone();
		Arrays.sort(expected);
		int[] actual = SortUtils.sort(input);
		assertArrayEquals(expected, actual, "should match Arrays.sort()");
		assertFalse(input == actual, "must return a new array instance");
	}

	static int[][] randomIntArrays() {
		Random rnd = new Random(123456L);
		int[][] data = new int[10][];
		for (int i = 0; i < data.length; i++) {
			int n = rnd.nextInt(50); // 0..49 length
			int[] a = new int[n];
			for (int j = 0; j < n; j++) {
				a[j] = rnd.nextInt(200) - 100; // values in [-100, 99]
			}
			data[i] = a;
		}
		return data;
	}
}

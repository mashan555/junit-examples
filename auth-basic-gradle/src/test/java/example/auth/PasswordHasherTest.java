/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package example.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHasherTest {
	@Test
	void hashAndVerify() {
		var hasher = new PasswordHasher();
		var hash = hasher.hash("s3cr3t".toCharArray());
		assertTrue(hasher.verify("s3cr3t".toCharArray(), hash));
	}

	@Test
	void negativeVerify() {
		var hasher = new PasswordHasher();
		var hash = hasher.hash("s3cr3t".toCharArray());
		assertFalse(hasher.verify("not-it".toCharArray(), hash));
	}
}

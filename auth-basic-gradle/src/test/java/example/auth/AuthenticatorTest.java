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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatorTest {
	private PasswordHasher hasher;
	private InMemoryUserStore store;
	private Authenticator authenticator;

	@BeforeEach
	void setup() {
		hasher = new PasswordHasher();
		store = new InMemoryUserStore();
		authenticator = new Authenticator(store, hasher);
	}

	@Test
	void registerAndAuthenticateSuccessfully() {
		var user = new User("alice");
		store.register(user, hasher.hash("password".toCharArray()));

		var result = authenticator.authenticate(new Credentials("alice", "password".toCharArray()));
		assertTrue(result.isSuccess());
		assertEquals("alice", result.getUser().getUsername());
	}

	@Test
	void wrongPasswordFails() {
		var user = new User("bob");
		store.register(user, hasher.hash("password".toCharArray()));

		var result = authenticator.authenticate(new Credentials("bob", "nope".toCharArray()));
		assertFalse(result.isSuccess());
		assertEquals("Bad credentials", result.getMessage());
	}

	@Test
	void unknownUserFails() {
		var result = authenticator.authenticate(new Credentials("who", "ever".toCharArray()));
		assertFalse(result.isSuccess());
		assertEquals("Unknown user", result.getMessage());
	}
}

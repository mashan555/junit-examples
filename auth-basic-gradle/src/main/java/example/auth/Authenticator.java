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

import java.util.Objects;

/**
 * Authenticates username/password pairs against a {@link UserStore} using a {@link PasswordHasher}.
 */
public final class Authenticator {
	private final UserStore store;
	private final PasswordHasher hasher;

	public Authenticator(UserStore store, PasswordHasher hasher) {
		this.store = Objects.requireNonNull(store, "store");
		this.hasher = Objects.requireNonNull(hasher, "hasher");
	}

	public AuthResult authenticate(Credentials credentials) {
		var username = credentials.getUsername();
		var userOpt = store.findUser(username);
		if (userOpt.isEmpty()) {
			return AuthResult.failure("Unknown user");
		}
		var hashOpt = store.passwordHashOf(username);
		if (hashOpt.isEmpty()) {
			return AuthResult.failure("No credentials on record");
		}
		var ok = hasher.verify(credentials.getPassword(), hashOpt.get());
		return ok ? AuthResult.success(userOpt.get()) : AuthResult.failure("Bad credentials");
	}
}

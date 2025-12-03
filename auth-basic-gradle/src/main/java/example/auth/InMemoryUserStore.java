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

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe in-memory user store for demos; not for production use.
 */
public final class InMemoryUserStore implements UserStore {
	private final Map<String, User> users = new ConcurrentHashMap<>();
	private final Map<String, String> hashes = new ConcurrentHashMap<>();

	@Override
	public void register(User user, String passwordHash) throws AuthException {
		var username = user.getUsername();
		if (users.putIfAbsent(username, user) != null) {
			throw new AuthException("User already exists: " + username);
		}
		hashes.put(username, passwordHash);
	}

	@Override
	public Optional<User> findUser(String username) {
		return Optional.ofNullable(users.get(username));
	}

	@Override
	public Optional<String> passwordHashOf(String username) {
		return Optional.ofNullable(hashes.get(username));
	}
}

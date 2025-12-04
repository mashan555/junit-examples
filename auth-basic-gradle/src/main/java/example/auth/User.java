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
 * Minimal user representation for demonstration purposes.
 */
public final class User {
	private final String username;

	public User(String username) {
		this.username = Objects.requireNonNull(username, "username");
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return username.equals(user.username);
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + '}';
	}
}

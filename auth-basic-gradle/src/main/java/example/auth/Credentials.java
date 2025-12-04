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

import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable credentials holder. The password is stored as a char[] for demonstration
 * purposes and can be cleared by the caller after use.
 */
public final class Credentials {
	private final String username;
	private final char[] password;

	public Credentials(String username, char[] password) {
		this.username = Objects.requireNonNull(username, "username");
		this.password = Objects.requireNonNull(password, "password");
	}

	public String getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Credentials{" + "username='" + username + '\'' + ", password=[PROTECTED]" + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Credentials)) return false;
		var that = (Credentials) o;
		return username.equals(that.username) && Arrays.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + Arrays.hashCode(password);
		return result;
	}
}

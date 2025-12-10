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

/**
 * Result of an authentication attempt.
 */
public final class AuthResult {
	private final boolean success;
	private final User user;
	private final String message;

	private AuthResult(boolean success, User user, String message) {
		this.success = success;
		this.user = user;
		this.message = message;
	}

	public static AuthResult success(User user) {
		return new AuthResult(true, user, "OK");
	}

	public static AuthResult failure(String message) {
		return new AuthResult(false, null, message);
	}

	public boolean isSuccess() {
		return success;
	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}
}

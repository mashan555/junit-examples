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

import java.util.Optional;

/**
 * Simple user store abstraction for demonstration.
 */
public interface UserStore {
	void register(User user, String passwordHash) throws AuthException;
	Optional<User> findUser(String username);
	Optional<String> passwordHashOf(String username);
}

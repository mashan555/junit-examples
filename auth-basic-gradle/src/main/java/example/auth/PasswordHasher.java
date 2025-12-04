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

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Objects;

/**
 * PBKDF2 password hasher using JDK crypto only.
 * Format: pbkdf2$<iterations>$<saltB64>$<hashB64>
 */
public final class PasswordHasher {
	private static final String ALGO = "PBKDF2WithHmacSHA256";
	private final int iterations;
	private final int saltLength;
	private final int keyLength;
	private final SecureRandom random;

	public PasswordHasher() {
		this(120_000, 16, 32, new SecureRandom());
	}

	public PasswordHasher(int iterations, int saltLength, int keyLength, SecureRandom random) {
		this.iterations = iterations;
		this.saltLength = saltLength;
		this.keyLength = keyLength;
		this.random = Objects.requireNonNull(random, "random");
	}

	public String hash(char[] password) {
		Objects.requireNonNull(password, "password");
		byte[] salt = new byte[saltLength];
		random.nextBytes(salt);
		byte[] derived = deriveKey(password, salt, iterations, keyLength);
		return String.format("pbkdf2$%d$%s$%s", iterations,
				Base64.getEncoder().encodeToString(salt),
				Base64.getEncoder().encodeToString(derived));
	}

	public boolean verify(char[] password, String hash) {
		Objects.requireNonNull(password, "password");
		Objects.requireNonNull(hash, "hash");
		var parts = hash.split("\\$");
		if (parts.length != 4 || !parts[0].equals("pbkdf2")) {
			throw new IllegalArgumentException("Unsupported hash format");
		}
		int iters = Integer.parseInt(parts[1]);
		byte[] salt = Base64.getDecoder().decode(parts[2]);
		byte[] expected = Base64.getDecoder().decode(parts[3]);
		byte[] actual = deriveKey(password, salt, iters, expected.length);
		return constantTimeEquals(expected, actual);
	}

	private static byte[] deriveKey(char[] password, byte[] salt, int iterations, int keyLen) {
		try {
			var spec = new PBEKeySpec(password, salt, iterations, keyLen * 8);
			var factory = SecretKeyFactory.getInstance(ALGO);
			return factory.generateSecret(spec).getEncoded();
		}
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new IllegalStateException("PBKDF2 not available", e);
		}
	}

	private static boolean constantTimeEquals(byte[] a, byte[] b) {
		if (a.length != b.length) return false;
		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result |= a[i] ^ b[i];
		}
		return result == 0;
	}
}

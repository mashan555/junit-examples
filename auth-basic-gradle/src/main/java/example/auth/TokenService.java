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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * Issues and verifies simple signed tokens using HMAC-SHA256.
 * Token format: v1.<b64url(username)><'.'><b64url(nonce)><'.'><b64url(signature)>
 * where signature = HMAC-SHA256(secret, username + '\n' + nonce)
 */
public final class TokenService {
	private static final String HMAC = "HmacSHA256";
	private static final Base64.Encoder B64 = Base64.getUrlEncoder().withoutPadding();
	private static final Base64.Decoder B64D = Base64.getUrlDecoder();

	private final byte[] secret;
	private final SecureRandom random;

	public TokenService(byte[] secret) {
		this(secret, new SecureRandom());
	}

	public TokenService(byte[] secret, SecureRandom random) {
		this.secret = Objects.requireNonNull(secret, "secret").clone();
		this.random = Objects.requireNonNull(random, "random");
	}

	public String issue(User user) {
		byte[] username = user.getUsername().getBytes(StandardCharsets.UTF_8);
		byte[] nonce = new byte[16];
		random.nextBytes(nonce);
		byte[] sig = sign(username, nonce);
		return "v1." + B64.encodeToString(username) + '.' + B64.encodeToString(nonce) + '.' + B64.encodeToString(sig);
	}

	/**
	 * Verify token and return the username if valid; otherwise throws {@link AuthException}.
	 */
	public String verify(String token) {
		Objects.requireNonNull(token, "token");
		if (!token.startsWith("v1.")) {
			throw new AuthException("Unsupported token format");
		}
		String[] parts = token.substring(3).split("\\.");
		if (parts.length != 3) {
			throw new AuthException("Malformed token");
		}
		byte[] username = B64D.decode(parts[0]);
		byte[] nonce = B64D.decode(parts[1]);
		byte[] expected = B64D.decode(parts[2]);
		byte[] actual = sign(username, nonce);
		if (!constantTimeEquals(expected, actual)) {
			throw new AuthException("Invalid token signature");
		}
		return new String(username, StandardCharsets.UTF_8);
	}

	private byte[] sign(byte[] username, byte[] nonce) {
		try {
			Mac mac = Mac.getInstance(HMAC);
			mac.init(new SecretKeySpec(secret, HMAC));
			mac.update(username);
			mac.update((byte) '\n');
			mac.update(nonce);
			return mac.doFinal();
		} catch (Exception e) {
			throw new AuthException("HMAC not available", e);
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

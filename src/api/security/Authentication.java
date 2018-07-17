package api.security;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Authentication {

    /**
     * The amount of iterations when hashing the password.
     */
    private static final int HASH_ITERATIONS = 64000;

    /**
     * The name of the hashing algorithm.
     */
    private static final String HASHING_ALGORITHM = "PBKDF2WithHmacSHA1";

    /**
     * The name of the salting algorithm.
     */
    private static final String SALTING_ALGORITHM = "SHA1PRNG";

    /**
     * The length of the has.
     */
    private static final int HASH_LENGTH = 256;

    /**
     * The length of the salt.
     */
    private static final int SALT_LENGTH = 32;

    /**
     * Takes the specified token then hashes it with the salt. (Uses the format: "token$salt")
     *
     * @param token The token to hash.
     * @return The hashed token with the salt. (Uses the format: "token$salt")
     */
    public static String getSaltedHashedToken(char[] token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final byte[] SALT = SecureRandom.getInstance(SALTING_ALGORITHM).generateSeed(SALT_LENGTH);
        return getHashedToken(token, SALT) + "$" + Base64.getEncoder().encodeToString(SALT);
    }

    /**
     * Takes the specified token then hashes it with the salt.
     *
     * @param token The token to hash.
     * @param salt  The byte array containing the salt.
     * @return The hashed token.
     */
    private static String getHashedToken(char[] token, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (token == null || token.length <= 0)
            return null;

        final SecretKeyFactory SECRET_KEY_FACTORY = SecretKeyFactory.getInstance(HASHING_ALGORITHM);
        final SecretKey SECRET_KEY = SECRET_KEY_FACTORY.generateSecret(new PBEKeySpec(token, salt, HASH_ITERATIONS, HASH_LENGTH));
        return Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded());
    }

    /**
     * Authenticates the the hashed token against the un-hashed token.
     *
     * @param token_to_check The un-hashed token to check.
     * @param stored_token   The hashed token to check against.
     * @return True if the token was successfully authenticated; false otherwise.
     */
    public static boolean authenticateToken(char[] token_to_check, String stored_token) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final String[] TOKEN = stored_token.split("\\$");
        if (TOKEN.length != 2)
            return false;

        final String HASH = getHashedToken(token_to_check, Base64.getDecoder().decode(TOKEN[1]));
        return HASH.equals(TOKEN[0]);
    }

}


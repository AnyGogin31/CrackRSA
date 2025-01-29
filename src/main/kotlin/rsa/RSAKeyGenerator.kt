package neilt.mobile.rsa

import java.math.BigInteger

// Generates RSA keys
object RSAKeyGenerator {

    /**
     * Generates a short RSA key.
     *
     * @return A Triple containing:
     *         - n: The modulus.
     *         - e: The public exponent.
     *         - d: The private exponent.
     */
    fun generateShortRSAKey(): Triple<BigInteger, BigInteger, BigInteger> {
        // Generate two prime numbers
        val p = PrimeGenerator.generatePrime(PRIME_BIT_LENGTH)
        val q = PrimeGenerator.generatePrime(PRIME_BIT_LENGTH)

        // Compute the modulus n
        val n = p * q

        // Compute Euler's totient function φ(n)
        val phi = (p - BigInteger.ONE) * (q - BigInteger.ONE)

        // Use the default public exponent
        val e = BigInteger(DEFAULT_PUBLIC_EXPONENT)

        // Ensure e and φ(n) are coprime
        if (MathUtils.gcd(e, phi) != BigInteger.ONE) {
            throw IllegalStateException("e and φ(n) are not coprime. Try again.")
        }

        // Compute the private exponent d
        val d = MathUtils.modInverse(e, phi)

        return Triple(n, e, d)
    }
}

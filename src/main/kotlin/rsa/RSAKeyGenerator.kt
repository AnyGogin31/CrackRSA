package neilt.mobile.rsa

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
    fun generateShortRSAKey(): Triple<Long, Long, Long> {
        // Generate two prime numbers
        val p = PrimeGenerator.generatePrime(PRIME_BIT_LENGTH)
        val q = PrimeGenerator.generatePrime(PRIME_BIT_LENGTH)

        // Compute the modulus n
        val n = p * q

        // Compute Euler's totient function φ(n)
        val phi = (p - 1) * (q - 1)

        // Use the default public exponent
        val e = DEFAULT_PUBLIC_EXPONENT

        // Ensure e and φ(n) are coprime
        if (MathUtils.gcd(e, phi) != 1L) {
            throw IllegalStateException("e and φ(n) are not coprime. Try again.")
        }

        // Compute the private exponent d
        val d = MathUtils.modInverse(e, phi)

        return Triple(n, e, d)
    }
}

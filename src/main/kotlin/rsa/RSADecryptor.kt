package neilt.mobile.rsa

import java.math.BigInteger

// Decrypts RSA-encrypted messages
object RSADecryptor {

    /**
     * Decrypts an RSA-encrypted message.
     *
     * @param encrypted The encrypted message.
     * @param n The modulus.
     * @param d The private exponent.
     * @return The decrypted message.
     */
    fun decrypt(encrypted: BigInteger, n: BigInteger, d: BigInteger): BigInteger {
        return MathUtils.modPow(encrypted, d, n)
    }

    /**
     * Attempts to crack RSA by factorizing n and computing the private exponent d.
     *
     * @param n The modulus.
     * @param e The public exponent.
     * @return The private exponent d, or null if cracking is impossible.
     */
    fun crackRSA(n: BigInteger, e: BigInteger): BigInteger? {
        println("Starting factorization of n...")
        val (p, q) = factorize(n) ?: run {
            println("Factorization failed: n is not a product of two primes.")
            return null
        }
        println("Found prime factors: p = $p, q = $q")

        // Verify that p and q are prime
        if (!PrimeGenerator.isProbablePrime(p) || !PrimeGenerator.isProbablePrime(q)) {
            println("Error: Found factors are not prime.")
            return null
        }

        // Compute Euler's totient function φ(n)
        val phi = (p - BigInteger.ONE) * (q - BigInteger.ONE)
        println("Computed Euler's totient function: φ(n) = $phi")

        // Compute the private exponent d
        println("Computing private exponent d...")
        return MathUtils.modInverse(e, phi)
    }

    /**
     * Factorizes a number n into two prime factors.
     *
     * @param n The number to factorize.
     * @return A pair of prime factors (p, q), or null if factorization fails.
     */
    private fun factorize(n: BigInteger): Pair<BigInteger, BigInteger>? {
        // Check for small divisors
        for (i in 2L..100L) {
            if (n % i.toBigInteger() == BigInteger.ZERO) {
                val p = i.toBigInteger()
                val q = n / p
                return Pair(p, q)
            }
        }

        // Pollard's Rho algorithm for larger numbers
        fun pollardsRho(n: BigInteger): BigInteger {
            var x = BigInteger.TWO
            var y = x
            var d = BigInteger.ONE
            val c = BigInteger.ONE

            while (d == BigInteger.ONE) {
                x = (x * x + c) % n
                y = (y * y + c) % n
                y = (y * y + c) % n
                d = MathUtils.gcd((x - y).abs(), n)
            }
            return d
        }

        val p = pollardsRho(n)
        if (p == n) return null // n is prime or cannot be factorized
        val q = n / p
        return Pair(p, q)
    }
}

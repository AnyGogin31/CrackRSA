package neilt.mobile.rsa

import java.math.BigInteger
import java.util.Random

// Generates prime numbers for RSA key generation
object PrimeGenerator {

    /**
     * Generates a prime number of the specified bit lengths.
     *
     * @param bitLength The bit length of the prime number.
     * @return A prime number.
     */
    fun generatePrime(bitLength: Int): BigInteger {
        val random = Random()
        return BigInteger.probablePrime(bitLength, random)
    }

    /**
     * Checks if a number is probably prime using the Miller-Rabin test.
     *
     * @param n The number to check.
     * @return true if the number is probably prime, false otherwise.
     */
    fun isProbablePrime(n: BigInteger): Boolean {
        return n.isProbablePrime(MILLER_RABIN_ITERATIONS)
    }
}

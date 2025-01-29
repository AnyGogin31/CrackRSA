package neilt.mobile.rsa

import kotlin.random.Random

// Generates prime numbers for RSA key generation
object PrimeGenerator {

    /**
     * Generates a prime number of the specified bit lengths.
     *
     * @param bitLength The bit length of the prime number.
     * @return A prime number.
     */
    fun generatePrime(bitLength: Int): Long {
        val min = 1L shl (bitLength - 1)
        val max = (1L shl bitLength) - 1

        while (true) {
            val candidate = Random.nextLong(min, max)
            if (isProbablePrime(candidate)) {
                return candidate
            }
        }
    }

    /**
     * Checks if a number is probably prime using the Miller-Rabin test.
     *
     * @param n The number to check.
     * @return true if the number is probably prime, false otherwise.
     */
    private fun isProbablePrime(n: Long): Boolean {
        if (n < 2) return false
        for (p in listOf(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L)) {
            if (n % p == 0L) return n == p
        }
        return millerRabinTest(n)
    }

    /**
     * Performs the Miller-Rabin primality test.
     *
     * @param n The number to test.
     * @param k The number of iterations.
     * @return true if the number is probably prime, false otherwise.
     */
    private fun millerRabinTest(n: Long, k: Int = MILLER_RABIN_ITERATIONS): Boolean {
        if (n < 2) return false
        var d = n - 1
        var s = 0
        while (d % 2 == 0L) {
            d /= 2
            s++
        }

        for (i in 0 until k) {
            val a = Random.nextLong(2, n - 2)
            var x = MathUtils.modPow(a, d, n)
            if (x == 1L || x == n - 1) continue
            for (j in 0 until s - 1) {
                x = MathUtils.modPow(x, 2, n)
                if (x == n - 1L) break
            }
            if (x != n - 1L) return false
        }
        return true
    }
}

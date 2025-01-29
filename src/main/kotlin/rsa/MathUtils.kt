package neilt.mobile.rsa

import java.math.BigInteger

// Utility functions for mathematical operations
object MathUtils {

    /**
     * Computes the greatest common divisor (GCD) of two numbers.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The GCD of a and b.
     */
    tailrec fun gcd(a: BigInteger, b: BigInteger): BigInteger = if (b == BigInteger.ZERO) a else gcd(b, a % b)

    /**
     * Computes the modular inverse of a number.
     *
     * @param a The number for which the inverse is computed.
     * @param m The modulus.
     * @return The modular inverse of a modulo m.
     * @throws ArithmeticException if the inverse does not exist.
     */
    fun modInverse(a: BigInteger, m: BigInteger): BigInteger {
        return a.modInverse(m)
    }

    /**
     * Computes modular exponentiation (base^exponent % mod).
     *
     * @param base The base.
     * @param exponent The exponent.
     * @param mod The modulus.
     * @return The result of (base^exponent) % mod.
     */
    fun modPow(base: BigInteger, exponent: BigInteger, mod: BigInteger): BigInteger {
        return base.modPow(exponent, mod)
    }
}

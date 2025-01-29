package neilt.mobile.rsa

// Utility functions for mathematical operations
object MathUtils {

    /**
     * Computes the greatest common divisor (GCD) of two numbers.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The GCD of a and b.
     */
    tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

    /**
     * Computes the modular inverse of a number.
     *
     * @param a The number for which the inverse is computed.
     * @param m The modulus.
     * @return The modular inverse of a modulo m.
     * @throws ArithmeticException if the inverse does not exist.
     */
    fun modInverse(a: Long, m: Long): Long {
        var t = 0L
        var newt = 1L
        var r = m
        var newr = a

        while (newr != 0L) {
            val quotient = r / newr
            t = newt.also { newt = t - quotient * newt }
            r = newr.also { newr = r - quotient * newr }
        }

        if (r > 1) throw ArithmeticException("Inverse does not exist")
        return if (t < 0) t + m else t
    }

    /**
     * Computes modular exponentiation (base^exponent % mod).
     *
     * @param base The base.
     * @param exponent The exponent.
     * @param mod The modulus.
     * @return The result of (base^exponent) % mod.
     */
    fun modPow(base: Long, exponent: Long, mod: Long): Long {
        var result = 1L
        var a = base % mod
        var exp = exponent

        while (exp > 0) {
            if (exp % 2 == 1L) {
                result = (result * a) % mod
            }
            a = (a * a) % mod
            exp /= 2
        }
        return result
    }
}

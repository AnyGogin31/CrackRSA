package neilt.mobile

import neilt.mobile.rsa.RSAKeyGenerator

// Entry point for the program
fun main() {
    val (n, e, d) = RSAKeyGenerator.generateShortRSAKey()
    println("Generated RSA Key:")
    println("Modulus (n): $n")
    println("Public Exponent (e): $e")
    println("Private Exponent (d): $d")
}

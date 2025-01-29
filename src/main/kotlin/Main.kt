package neilt.mobile

import java.math.BigInteger
import kotlin.system.measureTimeMillis
import neilt.mobile.rsa.MathUtils
import neilt.mobile.rsa.RSADecryptor
import neilt.mobile.rsa.RSAKeyGenerator

// Entry point for the program
fun main() {
    val (n, e, d) = RSAKeyGenerator.generateShortRSAKey()
    println("Generated RSA Key:")
    println("Modulus (n): $n")
    println("Public Exponent (e): $e")
    println("Private Exponent (d): $d")

    // Example messages to encrypt and decrypt
    val examples = listOf(
        BigInteger("123"), // Small message
        BigInteger("123456789"), // Medium message
        BigInteger("9223372036854775807") // Large message
    )

    examples.forEach { message ->
        println("\n--- Example: Message = $message ---")

        // Encrypt the message
        val encrypted = MathUtils.modPow(message, e, n)
        println("Encrypted Message (C): $encrypted")

        // Decrypt the message
        val decrypted = RSADecryptor.decrypt(encrypted, n, d)
        println("Decrypted Message: $decrypted")

        // Attempt to crack RSA
        val crackTime = measureTimeMillis {
            val crackedD = RSADecryptor.crackRSA(n, e)
            if (crackedD != null) {
                println("Cracked Private Exponent (d): $crackedD")
                val crackedMessage = RSADecryptor.decrypt(encrypted, n, crackedD)
                println("Cracked Decrypted Message: $crackedMessage")
            } else {
                println("RSA cracking failed.")
            }
        }
        println("Cracking Time: $crackTime ms")
    }
}

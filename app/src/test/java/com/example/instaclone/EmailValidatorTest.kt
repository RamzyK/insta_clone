package com.example.instaclone

import org.junit.Assert.*
import org.junit.Test

class EmailValidatorTest {
    private val validator = EmailValidator()

    @Test
    fun `Test should return true if email has more or equal to 15 characters`() {
        // Préparation
        val email = "toto2@gmail.com"

        // Execution du test
        val testResult = validator.isValidEmail(email)

        // Verification du resultat
        assertTrue(testResult)
    }


    @Test
    fun `Test should return true if email contains @ character`() {
        // Préparation
        val email = "toto2@gmail.com"

        // Execution du test
        val testResult = validator.isValidEmail(email)

        // Verification du resultat
        assertTrue(testResult)
    }

    @Test
    fun `Test should return true if email has valid domain`() {
        // Préparation
        val email1 = "toto2@gmail.com"
        val email2 = "toto2@gmail.net"
        val email3 = "toto2@gmail.gov"

        // Execution du test
        val testResult1 = validator.isValidEmail(email1)
        val testResult2 = validator.isValidEmail(email2)
        val testResult3 = validator.isValidEmail(email3)

        // Verification du resultat
        assertTrue(testResult1)
        assertTrue(testResult2)
        assertFalse(testResult3)
    }

    @Test
    fun `Test should return false if email has nothing before @ character`() {
        // Préparation
        val email1 = "@gmailozazoieazoiejzaoi.com"

        // Execution du test
        val testResult1 = validator.isValidEmail(email1)

        // Verification du resultat
        assertFalse(testResult1)
    }

    @Test
    fun `Test should return false if email contains space`() {
        // Préparation
        val email1 = "toto2 @gmail.com"

        // Execution du test
        val testResult1 = validator.isValidEmail(email1)

        // Verification du resultat
        assertFalse(testResult1)
    }


    @Test
    fun `Test should return true domain is valid and the string respects the validator regex`() {
        // Préparation
        val email1 = "toto2@gmail.com"

        // Execution du test
        val isValidDomain = validator.hasValidDomain(email1)
        val fullEmailIsValid = validator.isValidEmail(email1)

        // Verification du resultat
        assertTrue(isValidDomain)
        assertTrue(fullEmailIsValid)
    }




}
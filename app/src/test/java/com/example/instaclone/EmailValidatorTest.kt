package com.example.instaclone

import org.junit.Assert.*
import org.junit.Test

class EmailValidatorTest {

    private val validator = EmailValidator()

    @Test
    fun `validation function should return true`() {
        // Préparation
        val email = "username@superlongdomainnamevalue.com"

        // Execution
        val result = validator.isValidEmail(email)

        // Verification
        assertTrue(result)
    }

    @Test
    fun `validation function should return false`() {
        // Préparation
        val email = "toto@exemple.com"

        // Execution
        val result = validator.isValidEmail(email)

        // Verification
        assertTrue(result)
    }

    @Test
    fun `valid email should return true`() {
        assertTrue(validator.isValidEmail("valid.email@example.com"))
    }

    @Test
    fun `email without @ should return false`() {
        assertFalse(validator.isValidEmail("invalidemail.com"))
    }

    @Test
    fun `email without domain should return false`() {
        assertFalse(validator.isValidEmail("user@"))
    }

    @Test
    fun `email with spaces should return false`() {
        assertFalse(validator.isValidEmail("invalid email@example.com"))
    }

    @Test
    fun `email with special characters should return false`() {
        assertFalse(validator.isValidEmail("invalid#email@example.com"))
    }

    @Test
    fun `email too short should return false`() {
        assertFalse(validator.isValidEmail("a@b.c"))
    }

    @Test
    fun `email with valid domain should return true`() {
        assertTrue(validator.hasValidDomain("user@example.com"))
        assertTrue(validator.hasValidDomain("user@example.net"))
        assertTrue(validator.hasValidDomain("user@example.org"))
    }

    @Test
    fun `email with invalid domain should return false`() {
        assertFalse(validator.hasValidDomain("user@example.xyz"))
    }

    @Test
    fun `corporate email should return true`() {
        assertTrue(validator.isCorporateEmail("employee@company.com"))
        assertTrue(validator.isCorporateEmail("staff@enterprise.org"))
    }

    @Test
    fun `non-corporate email should return false`() {
        assertFalse(validator.isCorporateEmail("user@gmail.com"))
    }

    @Test
    fun `Gmail address should return true`() {
        assertTrue(validator.isGmailAddress("user@gmail.com"))
    }

    @Test
    fun `non-Gmail address should return false`() {
        assertFalse(validator.isGmailAddress("user@yahoo.com"))
    }
}
package com.example.instaclone

class EmailValidator {

    fun isValidEmail(email: String) : Boolean {
        // Code the function to check if an email is valid
        return email.length > 10 &&
                email.contains("@") &&
                email.substringBefore("@").isNotEmpty() &&
                email.matches(Regex("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,5}$"))


    }

    fun hasValidDomain(email: String): Boolean {
        val domains = listOf(".com", ".fr", ".net", ".org")
        return  domains.any { email.endsWith(it)}
    }

    fun isCorporateEmail(email: String): Boolean {
        return email.endsWith("@sncf.fr") || email.endsWith("@sncf.com")
    }

    fun isGmailAddress(email: String): Boolean {
        return email.endsWith("gmail.com")
    }

}
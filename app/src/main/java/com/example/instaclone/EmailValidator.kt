package com.example.instaclone

class EmailValidator {

    fun isValidEmail(email: String) : Boolean {
        // Ecrire les vérifications liées à des règles métier
        
        return email.length >= 15 &&
                //email.contains("@") &&
                hasValidDomain(email) &&
                //email.substringBefore("@").isNotEmpty() &&
                //!email.contains(" ") &&
                                            // oziejazo_i-ejzaio@aoz_i-ejazie.{------}
                email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
    }

    fun hasValidDomain(email: String): Boolean {
        val domains = listOf(".com", ".fr", ".org", ".net")

        return domains.any { domain ->
            email.endsWith(domain)
        }
    }


}
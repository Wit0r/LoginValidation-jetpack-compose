package com.example.loginvalidation.login.domain.use_cases

class ValidatePassword {

    fun execute(password: String) : ValidationResult {
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLetterAndDigits = password.any { it.isLetter() } && password.any { it.isDigit() }

        if (!containsLetterAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and digit"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
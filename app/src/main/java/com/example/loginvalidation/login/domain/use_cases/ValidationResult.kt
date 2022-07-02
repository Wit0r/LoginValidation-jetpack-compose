package com.example.loginvalidation.login.domain.use_cases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)

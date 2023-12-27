package com.example.securityscreen.presentation.security_screen

import java.util.UUID

data class Number(
    val id: UUID = UUID.randomUUID(),
    val number: String
)

package com.sample

import java.util.regex.Matcher
import java.util.regex.Pattern

object Validator {
    private const val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    private val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
    private var matcher: Matcher? = null

    fun validateEmail(hex: String?): Boolean {
        matcher = pattern.matcher(hex)
        return matcher!!.matches()
    }
}
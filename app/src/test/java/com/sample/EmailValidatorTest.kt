package com.sample

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorTest {

    @Test
    fun emailValidator_CorrectEmail_ReturnsTrue() {
        assertThat(Validator.validateEmail("name@email.com")).isTrue()
    }

    @Test
    fun emailValidator_WrongEmail_ReturnsFalse() {
        assertThat(Validator.validateEmail("@email.com")).isFalse()
    }
}
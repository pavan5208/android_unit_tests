package com.sample

interface LoginRepo {
     fun validateLoginDetails(username: String, pass: String): LoginResponse?
}
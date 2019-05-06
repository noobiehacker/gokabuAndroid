package com.example.gokabu

import java.io.Serializable

data class LoginResponse(val code: String, val message: String, var data: HashMap<String,String>, var meta: String, var debugMessage: String) : Serializable
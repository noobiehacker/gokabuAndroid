package com.example.gokabu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val response = intent.extras.getSerializable(getString(R.string.LoginResponseKey)) as LoginResponse
        val data = response.data
        findViewById<TextView>(R.id.loggedInText).setText(getDisplayText(data))
    }

    fun getDisplayText(data: HashMap<String,String>): String {
        val username = data.get("username")
        val address_1 = data.get("address_1")
        val address_2 = data.get("address_2")
        return "Hey welcome back username <-> " + username + ", you are logged in :) " +
                "Your address_1 is <-> " + address_1 + " and Your address 2 is <-> " + address_2
    }
}

package com.example.gokabu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var loading = false
    private var observable : Observable<LoginResponse>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginBtn = findViewById<Button>(R.id.loginButton)
        val userNameField = findViewById<EditText>(R.id.userIdEditText)
        val passwordField = findViewById<EditText>(R.id.passwordEditText)
        loginBtn.setOnClickListener {
            Toast.makeText(this, "Connecting to server...", Toast.LENGTH_SHORT).show()
            if (!loading) {
                this@MainActivity.loading = true
                this@MainActivity.login(userNameField.text.toString(), passwordField.text.toString())
            }
        }
    }

    fun login(userName: String, password: String) {
        //Use Retro fit
        var observable = LoginViewModel().login(userName, password)
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<LoginResponse> {
            override fun onComplete() {
                this@MainActivity.loading = false
            }

            override fun onNext(response: LoginResponse) {
                Toast.makeText(applicationContext, "Network Call Succeeded My Friend", Toast.LENGTH_LONG).show()
                if (response.code != "Error_InvalidLogin") {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra(getString(R.string.LoginResponseKey), response)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, response.message, Toast.LENGTH_LONG).show()
                }

            }

            override fun onError(e: Throwable) {
                Toast.makeText(applicationContext, "Network Call Failed Unfortunately", Toast.LENGTH_LONG).show()
            }

            override fun onSubscribe(d: Disposable) {
                //Do nothing
            }
        })
        this.observable = observable
    }
}

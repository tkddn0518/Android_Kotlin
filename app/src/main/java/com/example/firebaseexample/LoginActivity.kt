package com.example.firebaseexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseexample.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Login Button
        binding.btnLogin.setOnClickListener{
            val email = binding.loginID.text.toString()
            val password = binding.loginPassword.text.toString()
            logIn(email, password)
        }

    }

    // LogIn function
    private fun logIn(id: String, password: String) {
        if (id.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(id, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "로그인 성공", Toast.LENGTH_LONG).show()
                        goToMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    // 유저 정보 확인 후 메인 페이지로 이동하는 함수
    private fun goToMainPage(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
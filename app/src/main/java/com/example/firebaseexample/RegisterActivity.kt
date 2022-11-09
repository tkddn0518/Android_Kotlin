package com.example.firebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseexample.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    // FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // CreateAccount Button
        binding.btnCreateAccount.setOnClickListener {
            val email = binding.registerID.text.toString()
            val password = binding.registerPassword.text.toString()

            createAccount(email, password)
        }
    }

    // 계정 생성 함수
    private fun createAccount(id: String, password: String) {
        if (id.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(id, password)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "계정 생성 완료", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this, "계정 생성 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
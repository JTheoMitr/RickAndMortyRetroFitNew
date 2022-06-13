package com.example.rickandmortyretrofitnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmortyretrofitnew.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    companion object {
        const val CONST_SIGN_IN = 34
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleAuth: GoogleSignInClient

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        binding.loginBtn.setOnClickListener {

            googleSignIn()

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }
    }

    private fun googleSignIn() {
        TODO("Not yet implemented")
    }
}
package com.example.rickandmortyretrofitnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.rickandmortyretrofitnew.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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


        }

        // Configure google sign in
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        googleAuth = GoogleSignIn.getClient(this, gso)
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's info
                    Log.d("SSO", "signInWithCredential:success")
                    val user = auth.currentUser
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    // If sign-in fails, display a message to the user
                    Log.w("SSO", "signInWithCredential:failure", task.exception)
                    //updateUI(null)
                }

            }
    }

    private fun googleSignIn() {

        auth.signOut()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        Log.d("SSO", "last account email is: ${account?.email}")
        if (account == null) {
            val signInIntent = googleAuth.signInIntent
            startActivityForResult(signInIntent, CONST_SIGN_IN)
        } else {
            firebaseAuthWithGoogle(account.idToken)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CONST_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)
            }
            catch (e:ApiException) {
                Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
            }
        }
    }
}


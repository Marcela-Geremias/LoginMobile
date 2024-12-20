package com.example.loginmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Referenciando os elementos do layout
        val emailInput = findViewById<EditText>(R.id.username) // ID do campo de e-mail
        val passwordInput = findViewById<EditText>(R.id.password) // ID do campo de senha
        val loginButton = findViewById<Button>(R.id.login_button) // ID do botão de login
        val createAccountButton = findViewById<Button>(R.id.create_account_button) // ID do botão de criar conta

        // Configura o botão de login
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                signInWithEmailAndPassword(email, password)
            }
        }

        // Configura o botão de criar conta
        createAccountButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                createUserWithEmailAndPassword(email, password)
            }
        }
    }

    private fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmailAndPassword: Success")
                    Toast.makeText(this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.w(TAG, "createUserWithEmailAndPassword: Failure", task.exception)
                    Toast.makeText(this, "Erro ao registrar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmailAndPassword: Success")
                    Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                    // Ir para a próxima tela (adicione a lógica da próxima tela aqui)
                } else {
                    Log.w(TAG, "signInWithEmailAndPassword: Failure", task.exception)
                    Toast.makeText(this, "Erro ao logar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {
        private const val TAG = "EmailAndPassword"
    }
}

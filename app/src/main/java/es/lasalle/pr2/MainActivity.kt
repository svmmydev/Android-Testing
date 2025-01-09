package es.lasalle.pr2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.lasalle.pr2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val emailRegex = Regex("^[A-Za-z0-9](.*)([@])(.+)(\\.)(.+)")
    private val passwordRegex = Regex("^.{6,}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupBinding()
        configureUi()
        setupUi()
    }

    private fun setupUi() = with(binding) {

        registerButton.setOnClickListener {
            val email = emailFieldEditText.text.toString()
            val password = passwordFieldEditText.text.toString()
            val confirmPassword = confirmPasswordFieldEditText.text.toString()

            if (email.matches(emailRegex) && password.isNotEmpty()
                && password.matches(passwordRegex) && password == confirmPassword) {

                startActivity(Intent(applicationContext, ResultActivity::class.java).apply {
                    putExtra("email", email)
                })

                finish()
            }
        }
        needHelpButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(getString(R.string.url)))
            startActivity(intent)
        }
    }

    private fun configureUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
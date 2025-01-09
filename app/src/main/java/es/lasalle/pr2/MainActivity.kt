package es.lasalle.pr2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import es.lasalle.pr2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //TODO: Revisar la captura en WORD del metodo nullPassword, hay que hacer nueva captura con variable validEmail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupBinding()
        configureUi()
        setupUi()
    }

    private fun setupUi() = with(binding) {
        registerButton.setOnClickListener {
            if (emailFieldEditText.text.toString().isNotEmpty()) {
                startActivity(Intent(applicationContext, ResultActivity::class.java).apply {
                    putExtra("email", emailFieldEditText.text.toString())
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
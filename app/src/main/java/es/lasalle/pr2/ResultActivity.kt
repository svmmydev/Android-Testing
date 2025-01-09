package es.lasalle.pr2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.lasalle.pr2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        configureUi()
        setupUi()
    }

    private fun setupUi() = with(binding) {
        buttonFinish.setOnClickListener {
            finish()
        }
        textViewEmail.text = intent.getStringExtra("email")
    }

    private fun configureUi() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupBinding() {
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.example.biblioteca.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.biblioteca.R
import com.example.biblioteca.databinding.ActivityGeneroDetailBinding
import com.example.biblioteca.ui.viewmodels.GeneroDetailViewModel

class GeneroDetailActivity:AppCompatActivity() {

    private var id:Int=0
    lateinit var binding: ActivityGeneroDetailBinding
    private val model:GeneroDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGeneroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

        id = intent.getIntExtra("generoId", -1)
        if (id != -1) {
            model.loadGenero(id)
        }
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
        model.genero.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.txtGeneroName.editText?.setText(it.nombre)
        }
    }

    private fun setupEventListeners() {
        binding.btnSaveCategory.setOnClickListener {
            model.saveGenero(binding.txtGeneroName.editText?.text.toString(), id)
        }
    }
}
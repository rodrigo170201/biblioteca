package com.example.biblioteca.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biblioteca.R
import com.example.biblioteca.databinding.ActivityMainBinding
import com.example.biblioteca.models.Genero
import com.example.biblioteca.models.Generos
import com.example.biblioteca.repositories.GeneroRepository
import com.example.biblioteca.ui.adapters.GeneroAdapter
import com.example.biblioteca.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), GeneroAdapter.OnGeneroClickListener {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaLibros()
    }

    private fun setupEventListeners() {
        binding.fabAddGenero.setOnClickListener {
            val intent = Intent(this, GeneroDetailActivity::class.java)
            startActivity(intent)
        }
    }



    private fun setupViewModelListeners() {
        model.generoList.observe(this){
            val adapter = (binding.lstGeneros.adapter as GeneroAdapter)
            adapter.updateData(it)
        }
    }

    private fun setupRecyclerView() {
        binding.lstGeneros.apply {
            this.adapter = GeneroAdapter(Generos(),this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onGeneroClick(genero: Genero) {
        val intent = Intent(this, GeneroDetailActivity::class.java)
        intent.putExtra("generoId", genero.id)
        startActivity(intent)

    }

    override fun onGeneroDelete(genero: Genero) {
        GeneroRepository.deleteGenero(genero.id!!,
            success = {
                model.fetchListaLibros()
            },
            failure = {
                it.printStackTrace()
            }

        )
    }
}
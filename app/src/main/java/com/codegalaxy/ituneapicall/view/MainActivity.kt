package com.codegalaxy.ituneapicall.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codegalaxy.ituneapicall.R
import com.codegalaxy.ituneapicall.databinding.ActivityMainBinding
import com.codegalaxy.ituneapicall.viewmodel.TuneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : TuneViewModel by viewModels()
    private lateinit var adapter: TuneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TuneAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        intiView()
    }

    private fun intiView() {
        binding.btnSearch.setOnClickListener {
            val data = binding.etSearch.text.toString().trim()
            if(data.isNotEmpty()){
                viewModel.getDataViewModel(data)
            }
            else{
                Toast.makeText(this,"Please enter a search term",Toast.LENGTH_LONG).show()
            }
        }

        lifecycleScope.launch {
            viewModel.tune.collect { artistList ->
                adapter = TuneAdapter(artistList)
                binding.recyclerView.adapter = adapter

//                if (artistList.isNotEmpty()) {
//                    Log.d("MainActivity", "Artists: ${artistList.joinToString { it.artistName }}")
//                } else {
//                    Log.d("MainActivity", "No results found.")
//                }
            }
        }
    }
}
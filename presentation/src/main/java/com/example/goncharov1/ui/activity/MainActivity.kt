package com.example.goncharov1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goncharov1.R
import com.example.goncharov1.databinding.ActivityMainBinding
import com.example.goncharov1.ui.fragment.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, MainFragment())
            .commit()
    }
}
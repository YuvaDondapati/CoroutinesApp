package com.yuvademos.coroutinesapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuvademos.coroutinesapp.R
import com.yuvademos.coroutinesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlayListFragment.newInstance())
                .commit()
        }

    }
}
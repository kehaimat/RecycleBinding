package com.example.databinding

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.databinding.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel by viewModel<UserViewModel>()
    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = userViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userAdapter = UserAdapter()

        binding.adapter = userAdapter

        userViewModel.data.observe(this, Observer {
            Log.i("MainActivity", it.toString())
            it.let(userAdapter::submitList)
        })
    }
}

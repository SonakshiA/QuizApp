package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.quizapp.databinding.ActivityFinalScoreBinding

class FinalScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityFinalScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val final_score = intent.extras?.getInt("finalScore")

        binding.scoreTextview.text = getString(R.string.final_score,final_score)


    }
}
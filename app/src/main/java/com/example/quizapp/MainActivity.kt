package com.example.quizapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.quizapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var count = 0

    private val viewModel: ScoreViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initial score is 0
        viewModel.score.observe(this@MainActivity, { score ->
            binding.score.text = getString(R.string.score, score)
        })

        getData()

    }

    private fun getData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait")
        progressDialog.show()

        RetrofitObject.apiInterface.getData().enqueue(object : Callback<ResponseDataClass?> {
            override fun onResponse(
                call: Call<ResponseDataClass?>,
                response: Response<ResponseDataClass?>
            ) {
                val s: MutableSet<Int> = mutableSetOf()
                while (s.size < 4) {
                    s.add((0..3).random())
                }
                val randomList = s.toList() //to get random list of numbers
                var index = 0
                if (count < response.body()?.size!!) {
                    binding.question.text = response.body()?.get(count)!!.question
                    progressDialog.dismiss()


                    if (randomList[index] == 0) {
                        binding.opt1.text = response.body()?.get(count)!!.correctAnswer
                        binding.opt2.text = response.body()?.get(count)!!.incorrectAnswers[0]
                        binding.opt3.text = response.body()?.get(count)!!.incorrectAnswers[1]
                        binding.opt4.text = response.body()?.get(count)!!.incorrectAnswers[2]
                    } else if (randomList[index] == 1) {
                        binding.opt2.text = response.body()?.get(count)!!.correctAnswer
                        binding.opt1.text = response.body()?.get(count)!!.incorrectAnswers[0]
                        binding.opt3.text = response.body()?.get(count)!!.incorrectAnswers[1]
                        binding.opt4.text = response.body()?.get(count)!!.incorrectAnswers[2]
                    } else if (randomList[index] == 2) {
                        binding.opt3.text = response.body()?.get(count)!!.correctAnswer
                        binding.opt1.text = response.body()?.get(count)!!.incorrectAnswers[0]
                        binding.opt2.text = response.body()?.get(count)!!.incorrectAnswers[1]
                        binding.opt4.text = response.body()?.get(count)!!.incorrectAnswers[2]
                    } else {
                        binding.opt4.text = response.body()?.get(count)!!.correctAnswer
                        binding.opt1.text = response.body()?.get(count)!!.incorrectAnswers[0]
                        binding.opt2.text = response.body()?.get(count)!!.incorrectAnswers[1]
                        binding.opt3.text = response.body()?.get(count)!!.incorrectAnswers[2]
                    }

                    val answer: String = response.body()?.get(count)!!.correctAnswer
                    //Toast.makeText(this@MainActivity,"${binding.opt1.text}",Toast.LENGTH_LONG).show()
                    //Toast.makeText(this@MainActivity,"${response.body()?.get(count)!!.correctAnswer}",Toast.LENGTH_LONG).show()
                    binding.opt1.setOnClickListener {
                        if (binding.opt1.text.toString().equals(answer)) {
                            Toast.makeText(this@MainActivity, "Correct!", Toast.LENGTH_SHORT).show()
                            viewModel.increaseScore()
                        } else {
                            Toast.makeText(this@MainActivity, "Wrong!", Toast.LENGTH_SHORT).show()
                            viewModel.decreaseScore()
                        }
                        viewModel.score.observe(this@MainActivity, { newScore ->
                            binding.score.text = getString(R.string.score, newScore)
                        })
                        getData()
                    }

                    binding.opt2.setOnClickListener {
                        if (binding.opt2.text.equals(answer)) {
                            Toast.makeText(this@MainActivity, "Correct!", Toast.LENGTH_SHORT).show()
                            viewModel.increaseScore()
                        } else {
                            Toast.makeText(this@MainActivity, "Wrong!", Toast.LENGTH_SHORT).show()
                            viewModel.decreaseScore()
                        }
                        viewModel.score.observe(this@MainActivity, { newScore ->
                            binding.score.text = getString(R.string.score, newScore)
                        })
                        getData()
                    }

                    binding.opt3.setOnClickListener {
                        if (binding.opt3.text.equals(answer)) {
                            Toast.makeText(this@MainActivity, "Correct!", Toast.LENGTH_SHORT).show()
                            viewModel.increaseScore()
                        } else {
                            Toast.makeText(this@MainActivity, "Wrong!", Toast.LENGTH_SHORT).show()
                            viewModel.decreaseScore()
                        }
                        viewModel.score.observe(this@MainActivity, { newScore ->
                            binding.score.text = getString(R.string.score, newScore)
                        })
                        getData()
                    }

                    binding.opt4.setOnClickListener {
                        if (binding.opt4.text.equals(answer)) {
                            Toast.makeText(this@MainActivity, "Correct!", Toast.LENGTH_SHORT).show()
                            viewModel.increaseScore()
                        } else {
                            Toast.makeText(this@MainActivity, "Wrong!", Toast.LENGTH_SHORT).show()
                            viewModel.decreaseScore()
                        }
                        viewModel.score.observe(this@MainActivity, { newScore ->
                            binding.score.text = getString(R.string.score, newScore)
                        })
                        getData()
                    }
                    count++
                } else {

                    val intent = Intent(this@MainActivity, FinalScoreActivity::class.java)
                    intent.putExtra("finalScore", viewModel.score.value)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ResponseDataClass?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
        })
    }
}


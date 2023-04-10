package com.example.quizapp

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScoreViewModel : ViewModel() {
    var question:String = ""
    var count = 0
    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    fun increaseScore() {
        _score.value = (_score.value)?.plus(1)
    }

    fun decreaseScore() {
        if (_score.value != 0)
            _score.value = (_score.value)?.plus(-1)
    }

}
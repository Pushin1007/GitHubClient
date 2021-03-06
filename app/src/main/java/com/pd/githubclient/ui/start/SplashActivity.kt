package com.pd.githubclient.ui.start

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pd.githubclient.databinding.ActivitySplashBinding
import com.pd.githubclient.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val handler: Handler by lazy { // получаем ссылку на главный поток
        Handler(mainLooper)
    }
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animator =// анимируем картинку
            ObjectAnimator.ofFloat(binding.imageView, View.SCALE_Y, -1f)
        animator.duration = 500 // длительность
        animator.repeatCount = 1 // количество повторений
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()

        handler.postDelayed(Runnable {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2000)// через 2 сек стартуем главную активити
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) //по завершению работы активити, удаляем все callback
    }
}
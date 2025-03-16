package com.example.baumanactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.myTextView)
        val receivedValue = intent.getStringExtra(EXTRA_DATA)
        textView.text = receivedValue

        println("SecondActivity onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        println("SecondActivity onRestart")
    }

    override fun onStart() {
        super.onStart()
        println("SecondActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        println("SecondActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        println("SecondActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        println("SecondActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("SecondActivity onDestroy")
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"

        fun createIntent(context: Context, data: String): Intent {
            return Intent(context, SecondActivity::class.java)
                .putExtra(EXTRA_DATA, data)
        }
    }
}

package com.example.baumanactivity

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

/**
 * Learn more about activities
 * https://developer.android.com/guide/components/activities/activity-lifecycle
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alertButton = findViewById<Button>(R.id.alert_button)
        val permissionButton = findViewById<Button>(R.id.permission_button)
        val activityButton = findViewById<Button>(R.id.activity_button)

        alertButton.setOnClickListener {
            startAlertDialog()
        }
        permissionButton.setOnClickListener {
            requestLocationPermission()
        }
        activityButton.setOnClickListener {
            startSecondActivity()
        }
        println("MainActivity onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        println("MainActivity onRestart")
    }

    override fun onStart() {
        super.onStart()
        println("MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        println("MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        println("MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        println("MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity onDestroy")
    }

    private fun startAlertDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Hello")
            setMessage("I just wanted to greet you. I hope you are doing great!")
            setPositiveButton("Positive") { _: DialogInterface?, _: Int ->
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Negative") { _, _ ->
                Toast.makeText(context, "Negative", Toast.LENGTH_SHORT).show()
            }
            setNeutralButton("Neutral") { _, _ ->
                Toast.makeText(context, "Neutral", Toast.LENGTH_SHORT).show()
            }
        }.create().show()
    }

    private fun requestLocationPermission() {
        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        ActivityCompat.requestPermissions(this, locationPermissions, 1)
        //search for ActivityTaskManager in Logcat
    }

    private fun startSecondActivity() {
        // starting explicit intents
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun startSecondActivityWithData() {
        // starting explicit intents with data
        val intent = SecondActivity.createIntent(this, "данные из первой активити")
        startActivity(intent)
    }


    // Read more about explicit and implicit intents here
    // https://developer.android.com/guide/components/activities/intro-activities
    private fun sendImplicitIntent() {
        // Create the text message with a string.
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "textMessage")
            type = "text/plain"
        }

        // Try to invoke the intent.
        try {
            startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Sorry, I can't do it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendImplicitIntent2() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:" + "+79998887766")
        }
        startActivity(intent)
    }
}

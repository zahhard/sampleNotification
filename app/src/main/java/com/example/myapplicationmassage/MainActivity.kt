package com.example.myapplicationmassage

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        var builder = NotificationCompat.Builder(this, "id")
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("My notification")
//            .setContentText("Much longer text that cannot fit one line...")
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                    .bigText("Much longer text that cannot fit one line...")
//            )
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        with(NotificationManagerCompat.from(this)){
//            val notificationId = 0
//            notify(notificationId, builder.build())
//       }

        val myWorkRequest = OneTimeWorkRequest.from(Worker::class.java)

        val uploadWorkRequest: WorkRequest =
            PeriodicWorkRequestBuilder<Worker>(30, TimeUnit.SECONDS)
                // Additional configuration
                .build()

        WorkManager.getInstance(this)
            .enqueue(uploadWorkRequest)
    }

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = ""
            val descriptionText = "getString(R.string.channel_description)"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("id", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }}

//    private fun getToken() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            // Log and toast
//            val msg = "token received $token"
//            Log.d("TAG", msg)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//        })
//    }

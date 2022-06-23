package com.odroid.movieready.view.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseActivity
import com.odroid.movieready.databinding.ActivityMainBinding
import com.odroid.movieready.notification.NotificationPublisher
import com.odroid.movieready.view.fragment.MovieSuggestionFragment

class HomeActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var suggestionFragment: MovieSuggestionFragment

    override fun getMainLayout(): Int = R.layout.activity_main

    override fun onViewReady() {
        addSuggestionsFragment()
        scheduleNotification()
    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {

    }

    private fun addSuggestionsFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        suggestionFragment = MovieSuggestionFragment()
        transaction.replace(R.id.suggestionHoldingFragment, suggestionFragment)
        transaction.addToBackStack(MovieSuggestionFragment::class.java.name)
        transaction.commitAllowingStateLoss()
    }

    private fun scheduleNotification() {
        //Todo - uncomment this code before merging
//        val notificationIntent = Intent(this, NotificationPublisher::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(
//            this,
//            0,
//            notificationIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            SystemClock.elapsedRealtime() + 60 * 1000,
//            1000 * 60 * 60 * 12,
//            pendingIntent
//        )
    }
}
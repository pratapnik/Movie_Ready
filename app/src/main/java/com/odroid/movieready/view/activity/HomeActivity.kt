package com.odroid.movieready.view.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseActivity
import com.odroid.movieready.databinding.ActivityMainBinding
import com.odroid.movieready.notification.NotificationPublisher
import com.odroid.movieready.view.NavGraphs
import com.odroid.movieready.view.views.AppRouter
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>() {

    override fun getMainLayout(): Int = R.layout.activity_main

    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialNavigationApi::class,
        ExperimentalAnimationApi::class
    )
    override fun onViewReady() {
        scheduleNotification()
        setContent {
            val bottomSheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                animationSpec = TweenSpec(durationMillis = 500),
                skipHalfExpanded = true
            )
            // BottomSheetNavigator to help navigate to bottom sheets
            val bottomSheetNavigator =
                remember { BottomSheetNavigator(sheetState = bottomSheetState) }
            // Animated nav controller is required for bottom sheet navigator to work with compose destinations
            val navController = rememberAnimatedNavController(bottomSheetNavigator)
            // Animated navHost engine is required for bottom sheet navigator to work with compose destinations
            val navHostEngine = rememberAnimatedNavHostEngine()

            navController.navigatorProvider += bottomSheetNavigator

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // ModalBottomSheetLayout of accompanist library should be used to add support for bottom sheet with compose destinations
                    ModalBottomSheetLayout(
                        bottomSheetNavigator = bottomSheetNavigator,
                        modifier = Modifier.defaultMinSize(minHeight = 1.dp)
                    ) {
                        DestinationsNavHost(
                            navController = navController,
                            navGraph = NavGraphs.root,
                            engine = navHostEngine,
                            dependenciesContainerBuilder = {
                                dependency(AppRouter(navController))
                                dependency(bottomSheetState)
                            }
                        )
                    }
                }
            }
        }

    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification() {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            SystemClock.elapsedRealtime() + 60 * 1000,
            1000 * 60 * 60 * 12,
            pendingIntent
        )
    }
}
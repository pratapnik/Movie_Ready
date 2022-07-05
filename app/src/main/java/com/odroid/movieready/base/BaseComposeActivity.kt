package com.odroid.movieready.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.google.android.material.composethemeadapter.MdcTheme

abstract class BaseComposeActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MdcTheme {
                Surface {
                    Content()
                }
            }
        }
    }

    @Composable
    abstract fun Content()
}
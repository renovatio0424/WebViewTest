package com.example.benchmark

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.benchmark.BenchmarkRule
import androidx.benchmark.measureRepeated
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewBenchmark {
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    @Test
    fun simpleViewInflate() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val inflater = LayoutInflater.from(context)
        val root = FrameLayout(context)

        benchmarkRule.measureRepeated {
            inflater.inflate(R.layout.test_simple_view, root, false)
        }
    }
}
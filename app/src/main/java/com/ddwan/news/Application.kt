package com.ddwan.news

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.ddwan.news.activity.PasswordActivity
import com.ddwan.news.config.Constants.Companion.START_ACTIVITY


class Application : Application(), Application.ActivityLifecycleCallbacks,
    DefaultLifecycleObserver {
    private var isBackground = false
    override fun onCreate() {
        super<Application>.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        registerActivityLifecycleCallbacks(this)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        isBackground = true
    }

    override fun onActivityResumed(p0: Activity) {
        if (isBackground && p0 !is PasswordActivity) {
            val intent = Intent(this, PasswordActivity::class.java)
            intent.putExtra(START_ACTIVITY, true)
            p0.startActivity(intent)
            isBackground = false
        }
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) = Unit
    override fun onActivityStarted(p0: Activity) = Unit
    override fun onActivityPaused(p0: Activity) = Unit
    override fun onActivityStopped(p0: Activity) = Unit
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) = Unit
    override fun onActivityDestroyed(p0: Activity) = Unit
}
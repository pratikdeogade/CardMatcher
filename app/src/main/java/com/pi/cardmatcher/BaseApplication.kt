package com.pi.cardmatcher

import com.pi.cardmatcher.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val daggerAppComponent = DaggerAppComponent.factory().create(applicationContext)
        daggerAppComponent.inject(this);
        return daggerAppComponent
    }
}
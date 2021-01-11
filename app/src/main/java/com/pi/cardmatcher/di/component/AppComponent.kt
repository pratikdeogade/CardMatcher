package com.pi.cardmatcher.di.component


import android.content.Context
import com.pi.cardmatcher.BaseApplication
import com.pi.cardmatcher.base.BaseActivity
import com.pi.cardmatcher.di.module.AppModule
import com.pi.cardmatcher.di.module.CardMatcherFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        CardMatcherFragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(baseActivity: BaseActivity)
}
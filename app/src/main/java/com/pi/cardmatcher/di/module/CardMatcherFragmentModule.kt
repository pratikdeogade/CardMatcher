package com.pi.cardmatcher.di.module

import androidx.lifecycle.ViewModel
import com.pi.cardmatcher.di.utils.ViewModelBuilder
import com.pi.cardmatcher.di.utils.ViewModelKey
import com.pi.cardmatcher.ui.CardMatcherFragment
import com.pi.cardmatcher.ui.CardMatcherViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class CardMatcherFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun shadiCardMatcherFragment(): CardMatcherFragment

    @Binds
    @IntoMap
    @ViewModelKey(CardMatcherViewModel::class)
    abstract fun bindViewModel(viewmodel: CardMatcherViewModel): ViewModel

}
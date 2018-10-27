package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.base.NavDrawerFragment
import com.taylorcase.hearthstonescry.dagger.modules.RoomModule
import com.taylorcase.hearthstonescry.room.CardDao
import com.taylorcase.hearthstonescry.savedcards.SavedCardsActivity
import com.taylorcase.hearthstonescry.search.SearchActivity
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TestAppModule::class, RoomModule::class))
interface TestAppComponent {

    fun cardDao(): CardDao

    fun api(): HearthstoneApi

    fun imageLoader(): ImageLoader

    fun sharedPreferencesHelper(): SharedPreferencesHelper

    fun heroUtils(): HeroUtils

    fun inject(baseActivity: BaseActivity)

    fun inject(selectHeroActivity: SelectHeroActivity)

    fun inject(searchActivity: SearchActivity)

    fun inject(splashActivity: SplashActivity)

    fun inject(cardsActivity: CardsActivity)

    fun inject(cardsActivityTest: CardsActivityTest)

    fun inject(detailedCardActivity: DetailedCardActivity)

    fun inject(savedCardsActivity: SavedCardsActivity)

    fun inject(navDrawerFragment: NavDrawerFragment)
}

package com.taylorcase.hearthstonescry.dagger.components

import com.taylorcase.hearthstonescry.*
import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.base.CardsGridActivity
import com.taylorcase.hearthstonescry.base.NavDrawerFragment
import com.taylorcase.hearthstonescry.dagger.modules.AppModule
import com.taylorcase.hearthstonescry.dagger.modules.RoomModule
import com.taylorcase.hearthstonescry.room.CardDao
import com.taylorcase.hearthstonescry.savedcards.SavedCardsActivity
import com.taylorcase.hearthstonescry.search.SearchActivity
import com.taylorcase.hearthstonescry.selecthero.DetailedSelectHeroActivity
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(selectHeroActivity: SelectHeroActivity)

    fun inject(searchActivity: SearchActivity)

    fun inject(splashActivity: SplashActivity)

    fun inject(cardsActivity: CardsActivity)

    fun inject(detailedCardActivity: DetailedCardActivity)

    fun inject(savedCardsActivity: SavedCardsActivity)

    fun inject(cardsGridActivity: CardsGridActivity)

    fun inject(detailedSelectHeroActivity: DetailedSelectHeroActivity)

    fun inject(navDrawerFragment: NavDrawerFragment)
}

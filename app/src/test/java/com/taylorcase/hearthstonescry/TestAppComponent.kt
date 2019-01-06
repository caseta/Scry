package com.taylorcase.hearthstonescry

import com.taylorcase.hearthstonescry.base.*
import com.taylorcase.hearthstonescry.dagger.components.AppComponent
import com.taylorcase.hearthstonescry.savedcards.SavedCardsActivityTest
import com.taylorcase.hearthstonescry.search.SearchActivityTest
import com.taylorcase.hearthstonescry.selecthero.DetailedSelectHeroActivityTest
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivityTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class])
interface TestAppComponent : AppComponent {

    fun inject(splashActivity: SplashActivityTest)

    fun inject(baseActivity: BaseActivityTest)

    fun inject(selectHeroActivity: SelectHeroActivityTest)

    fun inject(searchActivity: SearchActivityTest)

    fun inject(cardsActivity: CardsActivityTest)

    fun inject(detailedCardActivity: DetailedCardActivityTest)

    fun inject(savedCardsActivity: SavedCardsActivityTest)

    fun inject(cardsGridActivity: CardsGridActivityTest)

    fun inject(detailedSelectHeroActivity: DetailedSelectHeroActivityTest)

    fun inject(navDrawerFragment: NavDrawerFragmentTest)
}

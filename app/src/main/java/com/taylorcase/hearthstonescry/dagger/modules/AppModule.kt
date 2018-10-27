package com.taylorcase.hearthstonescry.dagger.modules

import android.app.Application
import android.content.Context
import com.taylorcase.hearthstonescry.*
import com.taylorcase.hearthstonescry.savedcards.SavedCardsContract
import com.taylorcase.hearthstonescry.savedcards.SavedCardsPresenter
import com.taylorcase.hearthstonescry.api.RetroClient
import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.search.SearchContract
import com.taylorcase.hearthstonescry.search.SearchPresenter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroAdapter
import com.taylorcase.hearthstonescry.selecthero.SelectHeroContract
import com.taylorcase.hearthstonescry.selecthero.SelectHeroPresenter
import com.taylorcase.hearthstonescry.utils.GlideImageLoader
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val application: Application) {

    @Provides @Singleton
    open fun provideContext(): Application {
        return application
    }

    @Provides @Singleton
    internal open fun providesHearthstoneApi(): HearthstoneApi {
        return RetroClient.apiService
    }

    @Provides @Singleton
    internal open fun providesImageLoader(): ImageLoader {
        return GlideImageLoader()
    }

    @Provides @Singleton
    internal open fun providesSplashPresenter(presenter: SplashPresenter) : SplashContract.Presenter {
        return presenter
    }

    @Provides @Singleton
    internal open fun providesCardsPresenter(presenter: CardsPresenter) : CardsContract.Presenter {
        return presenter
    }

    @Provides @Singleton
    internal open fun providesDetailedCardPresenter(presenter: DetailedCardPresenter) : DetailedCardContract.Presenter {
        return presenter
    }

    @Provides @Singleton
    internal open fun providesSavedCardsPresenter(presenter: SavedCardsPresenter) : SavedCardsContract.Presenter {
        return presenter
    }

    @Provides @Singleton
    internal open fun providesSearchPresenter(presenter: SearchPresenter) : SearchContract.Presenter {
        return presenter
    }

    @Provides @Singleton
    internal open fun providesSelectHeroPresenter(presenter: SelectHeroPresenter) : SelectHeroContract.Presenter {
        return presenter
    }

    @Provides @Singleton
    internal open fun providesSelectHeroAdapter(imageLoader: ImageLoader, context: Application, heroUtils: HeroUtils) : SelectHeroAdapter {
        return SelectHeroAdapter(imageLoader, context, heroUtils)
    }

    @Provides @Singleton
    internal open fun providesCardsAdapter(imageLoader: ImageLoader) : CardsAdapter {
        return CardsAdapter(imageLoader)
    }

    @Provides @Singleton
    internal open fun providesSharedPreferencesHelper(application: Application) : SharedPreferencesHelper {
        return SharedPreferencesHelper(application)
    }

    @Provides @Singleton
    internal open fun providesHeroUtils(sharedPreferencesHelper: SharedPreferencesHelper) : HeroUtils {
        return HeroUtils(sharedPreferencesHelper)
    }
}

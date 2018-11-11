package com.taylorcase.hearthstonescry.base

import com.nhaarman.mockito_kotlin.mock
import com.taylorcase.hearthstonescry.CardRepository
import com.taylorcase.hearthstonescry.CardsPresenter
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.NetworkManager
import org.assertj.core.api.Assertions.*
import org.junit.Test

class BasePresenterTest {

    private val mockView = mock<CardsContract.View>()
    private val mockHeroUtils = mock<HeroUtils>()
    private val mockCardRepo = mock<CardRepository>()
    private val mockNetworkManager = mock<NetworkManager>()

    @Test fun testAttachSetsReference() {
        val presenter = demandPresenter()

        presenter.attach(mockView)

        assertThat(presenter.viewReference).isNotNull()
    }

    @Test fun testDetachSetsReferenceAsNull() {
        val presenter = demandPresenter()
        presenter.attach(mockView)

        presenter.detach()

        assertThat(presenter.viewReference).isNull()
    }

    @Test fun testGetViewReturnsNullWhenViewReferenceIsNull() {
        val presenter = demandPresenter()

        assertThat(presenter.getView()).isNull()
    }

    @Test fun testGetViewReturnsNotNullWhenViewReferenceIsNotNull() {
        val presenter = demandPresenter()
        presenter.attach(mockView)

        assertThat(presenter.getView()).isNotNull()
    }

    private fun demandPresenter() : CardsPresenter {
        return CardsPresenter(mockHeroUtils, mockCardRepo, mockNetworkManager)
    }
}
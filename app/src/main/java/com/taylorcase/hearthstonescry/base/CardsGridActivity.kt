package com.taylorcase.hearthstonescry.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.taylorcase.hearthstonescry.CardsAdapter
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.Card
import kotlinx.android.synthetic.main.activity_cards.*
import android.content.Intent
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.View
import com.taylorcase.hearthstonescry.CardsViewHolder.Companion.EXTRA_POSITION
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.savedcards.SavedCardsActivity
import com.taylorcase.hearthstonescry.utils.DeviceUtils
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

@InjectLayout(R.layout.activity_cards)
abstract class CardsGridActivity : BaseActivity(), View.OnLayoutChangeListener {

    @Inject lateinit var adapter: CardsAdapter

    @VisibleForTesting lateinit var cardsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)

        setLoading(true)
        setupRecycler()
    }

    protected abstract fun loadCards()

    public override fun onStart() {
        super.onStart()
        loadCards()
    }

    private fun setupRecycler() {
        cardsRecyclerView = findViewById(R.id.cards_recycler_view)
        val layoutManager = GridLayoutManager(this, CARD_ROW_WIDTH)
        layoutManager.isAutoMeasureEnabled = false
        cardsRecyclerView.layoutManager = layoutManager
        cardsRecyclerView.adapter = adapter
    }

    fun showCards(cards: List<Card>) {
        setLoading(false)
        adapter.swapData(cards)
    }

    override fun onActivityReenter(resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK && !DeviceUtils.isSamsungDevice()) {
            val position = data.getIntExtra(EXTRA_POSITION, -1)
            postponeEnterTransition()
            cardsRecyclerView.addOnLayoutChangeListener({ _, _, _, _, _, _, _, _, _ ->
                cards_recycler_view.removeOnLayoutChangeListener(this)
                startPostponedEnterTransition()
            })
            cardsRecyclerView.scrollToPosition(position)

            toolbar.translationZ = -1f
        }
    }

    override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
        // NO-OP
    }

    companion object {
        private const val CARD_ROW_WIDTH = 3
    }
}

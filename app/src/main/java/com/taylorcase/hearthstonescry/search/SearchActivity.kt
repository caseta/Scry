package com.taylorcase.hearthstonescry.search

import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.jakewharton.rxbinding2.widget.RxTextView
import com.taylorcase.hearthstonescry.DetailedCardActivity
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.Card.Companion.CARD_EXTRA
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.include_toolbar_search.*
import java.util.ArrayList
import javax.inject.Inject

open class SearchActivity : BaseActivity(), SearchContract.View, SearchViewHolder.OnSuggestionClickListener {

    @VisibleForTesting val allNames = ArrayList<String>()
    @VisibleForTesting var adapter: SearchAdapter? = null
    private var disposable: Disposable? = null

    @Inject lateinit var presenter: SearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getComponent().inject(this)
        presenter.attach(this)
        setupToolbar(findViewById(R.id.toolbar_search), navigationMethod = BACK_ARROW)

        setLoading(true)
        presenter.populateCardNames()

        setupRecycler()
        setupTextListener()

        search_input_text.requestFocus()
    }

    override fun provideContentLayoutId(): Int {
        return R.layout.activity_search
    }

    private fun setupRecycler() {
        adapter = SearchAdapter()
        search_suggestions_recycler.adapter = adapter
        search_suggestions_recycler.layoutManager = LinearLayoutManager(this)
        adapter?.listener = this
    }

    private fun setupTextListener() {
        disposable = RxTextView.textChanges(search_input_text).filter {
            if (it.isNotEmpty()) {
                val suggestion = it.toString()
                val trimmed = suggestion.trim()
                if (trimmed.isNotEmpty()) {
                    return@filter true
                }
            }
            return@filter false
        }.subscribe(::swapData)
    }

    override fun setCardNames(names: List<String>) {
        allNames.addAll(names)
        setLoading(false)
    }

    private fun swapData(suggestion: CharSequence) {
        val stringSuggestion = suggestion.toString()
        val data = ArrayList<String>()

        for (name in allNames) {
            if (name.toLowerCase().contains(stringSuggestion.toLowerCase())) {
                data.add(name)
            }
        }
        adapter?.swapData(data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) return true
        return super.onOptionsItemSelected(item)
    }

    override fun onSuggestionClicked(suggestion: String) {
        presenter.performSearch(suggestion)
    }

    override fun navigateToProperDetailedCard(card: Card) {
        startActivity(Intent(this, DetailedCardActivity::class.java).putExtra(CARD_EXTRA, card))
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
        disposable?.dispose()
        adapter?.listener = null
    }
}

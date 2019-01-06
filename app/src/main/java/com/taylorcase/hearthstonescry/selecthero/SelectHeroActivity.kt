package com.taylorcase.hearthstonescry.selecthero

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_select_hero.*
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.base.BaseActivity
import javax.inject.Inject

open class SelectHeroActivity : BaseActivity() {

    @Inject lateinit var adapter: SelectHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getComponent().inject(this)
        setupRecycler()
    }

    override fun provideContentLayoutId(): Int {
        return R.layout.activity_select_hero
    }

    private fun setupRecycler() {
        select_hero_recycler.layoutManager = LinearLayoutManager(this)
        select_hero_recycler.adapter = adapter
    }
}

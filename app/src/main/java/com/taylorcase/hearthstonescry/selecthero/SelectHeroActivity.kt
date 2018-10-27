package com.taylorcase.hearthstonescry.selecthero

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_select_hero.*
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.ScryApplication
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import javax.inject.Inject

@InjectLayout(R.layout.activity_select_hero)
open class SelectHeroActivity : BaseActivity() {

    @Inject lateinit var adapter: SelectHeroAdapter

    @VisibleForTesting lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setupRecycler()
    }

    private fun setupRecycler() {
        layoutManager = LinearLayoutManager(this)
        select_hero_recycler.layoutManager = layoutManager
        select_hero_recycler.adapter = adapter
    }
}

package com.taylorcase.hearthstonescry

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import com.taylorcase.hearthstonescry.utils.makeGone
import com.taylorcase.hearthstonescry.utils.makeVisible
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

open class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject lateinit var heroUtils: HeroUtils
    @Inject lateinit var presenter: SplashContract.Presenter
    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getComponent().inject(this)
        setContentView(R.layout.activity_splash)

        presenter.attach(this)

        cards_loader.makeVisible()
        imageLoader.loadDrawableCenterCrop(R.drawable.wilfred_fizzlebang, findViewById(R.id.splash_screen_image_view))

        presenter.loadCards()
    }

    override fun cardsLoadedSuccessfully() {
        cards_loader.makeGone()
        if (hasFavoriteHero()) {
            startActivity(Intent(this, CardsActivity::class.java))
        } else {
            startActivity(Intent(this, SelectHeroActivity::class.java))
        }
        finish()
    }

    private fun hasFavoriteHero() = heroUtils.hasFavoriteHero()

    override fun showError() {
        runOnUiThread {
            Toast.makeText(this, R.string.whoops_error, Toast.LENGTH_LONG).show()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}

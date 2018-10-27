package com.taylorcase.hearthstonescry

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.taylorcase.hearthstonescry.base.BaseActivity
import com.taylorcase.hearthstonescry.base.InjectLayout
import com.taylorcase.hearthstonescry.savedcards.SavedCardsContract
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

open class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject lateinit var heroUtils: HeroUtils
    @Inject lateinit var presenter: SplashContract.Presenter
    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ScryApplication).getAppComponent()?.inject(this)
        setContentView(R.layout.activity_splash)

        presenter.attach(this)

        cards_loader.visibility = VISIBLE
        imageLoader.loadDrawableCenterCrop(R.drawable.wilfred_fizzlebang, findViewById(R.id.splash_screen_image_view))

        presenter.loadCards()
    }

    override fun cardsLoadedSuccessfully() {
        cards_loader.visibility = GONE
        if (hasFavoriteHero()) {
            startActivity(Intent(this, CardsActivity::class.java))
        } else {
            startActivity(Intent(this, SelectHeroActivity::class.java))
        }
        finish()
    }

    private fun hasFavoriteHero() = heroUtils.hasFavoriteHero()

    override fun showError() {
        Toast.makeText(this, R.string.whoops_error, Toast.LENGTH_LONG).show()
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
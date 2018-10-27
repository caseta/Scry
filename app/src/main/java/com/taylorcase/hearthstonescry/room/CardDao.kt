package com.taylorcase.hearthstonescry.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.enums.Hero
import io.reactivex.Single

@Dao
interface CardDao {

    @get:Query("SELECT * FROM card") val all: List<Card>

    @Query("DELETE FROM card") fun nukeTable()

    @Query("SELECT * FROM card") fun observeAllCards(): Single<List<Card>>

    @Query("SELECT * FROM card where playerClass LIKE  :hero") fun observeAllCardsWithHero(hero: String): Single<List<Card>>

    @Query("SELECT * FROM card where name LIKE  :name") fun observeCard(name: String): Single<List<Card>>

    @Query("SELECT COUNT(*) from card") fun countCards(): Int

    @Insert fun insertAll(vararg cards: Card)

    @Delete fun delete(card: Card)
}
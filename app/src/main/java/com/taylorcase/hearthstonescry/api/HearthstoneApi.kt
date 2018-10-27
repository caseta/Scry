package com.taylorcase.hearthstonescry.api

import com.taylorcase.hearthstonescry.model.AllCards
import com.taylorcase.hearthstonescry.model.Card
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface HearthstoneApi {

    @GET("cards/?collectible=1")
    fun getAllCards(@HeaderMap map: Map<String, String>): Observable<AllCards>

    @GET("cards/{cardName}")
    fun getCard(@HeaderMap map: Map<String, String>, @Path("cardName") cardName: String): Observable<List<Card>>

    @GET("cards/classes{className}")
    fun getClassCards(@HeaderMap map: Map<String, String>, @Path("className") className: String): Observable<List<Card>>

    @GET("cards/sets/{set}?collectible=1")
    fun getCardsFromSet(@HeaderMap map: Map<String, String>, @Path("set") set: String): Observable<List<Card>>
}

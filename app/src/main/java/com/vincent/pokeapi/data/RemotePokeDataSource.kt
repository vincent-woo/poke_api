package com.vincent.pokeapi.data

import android.util.Log
import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.pokeapi.model.PokemonListResultModel
import com.vincent.pokeapi.model.details.PokemonDetailsModel
import com.vincent.pokeapi.services.RetrofitPokeService
import retrofit2.Response
import java.io.IOException

class RemotePokeDataSource constructor(private val retrofitPokeService: RetrofitPokeService) : PokeDataSource {
    private val TAG = javaClass.simpleName

    override fun getPokemons(): List<Pokemon>? {
        val list = mutableListOf<Pokemon>()

        var response: Response<PokemonListResultModel>? = null

        try {
            response = retrofitPokeService.getPokemons()?.execute()
        } catch (e: IOException) {
            Log.e(TAG, "getPokemons error", e)
        }

        if (response == null || !response.isSuccessful) {
            Log.d(TAG, "$response")
            return null
        }

        val content = response.body()!!

        content.results.forEach {
            list.add(Pokemon(it.name, it.url))
        }

        return list
    }

    override fun getPokemonDetails(name: String): PokemonDetails? {
        var response: Response<PokemonDetailsModel>? = null

        try {
            response = retrofitPokeService.getPokemonDetails(name)?.execute()
        } catch (e: IOException) {
            Log.e(TAG, "getPokemonDetails error", e)
        }

        if (response == null || !response.isSuccessful) {
            Log.d(TAG, "$response")
            return null
        }

        val content = response.body()!!
        return PokemonDetails(content.sprites.frontDefault, content.weight, content.height)
    }
}

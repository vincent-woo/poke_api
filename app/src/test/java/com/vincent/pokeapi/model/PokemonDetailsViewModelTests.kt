package com.vincent.pokeapi.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vincent.entities.PokemonDetails
import com.vincent.pokeapi.BaseUnitTest
import com.vincent.pokeapi.data.PokeDataSource
import com.vincent.pokeapi.services.PokeApiService
import com.vincent.pokeapi.view.pokemons.details.PokemonDetailsViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock

@RunWith(JUnit4::class)
class PokemonDetailsViewModelTests : BaseUnitTest() {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokeDataSource: PokeDataSource

    @Test
    fun viewModel_shouldReturnPokemonDetails() {
        var result: PokemonDetails? = null
        val viewModel = PokemonDetailsViewModel(PokeApiService(pokeDataSource))

        given(pokeDataSource.getPokemonDetails(MOCK_NAME)).will { MOCK_POKEMON_DETAILS }


//        viewModel.pokemonDetails.observeForever { result = it }
//        viewModel.getPokemonDetails(MOCK_NAME)
//
//        assert(result === MOCK_POKEMON_DETAILS)
//        verify(pokeDataSource, times(1)).getPokemonDetails(MOCK_NAME)
    }
}

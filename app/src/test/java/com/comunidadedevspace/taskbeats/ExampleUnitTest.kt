package com.comunidadedevspace.taskbeats

import org.junit.Test

import org.junit.Assert.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val mockNumbersProvider : MyNumbersProvider  = mock()

    private val underTest = MyCountRepositoryImpl(
        numbersProvider = mockNumbersProvider
    )

    @Test
    fun addition_isCorrect() {
        whenever(mockNumbersProvider.getNumber()).thenReturn(2)

        val result = underTest.sum()
        assertEquals(4, result)
    }
}
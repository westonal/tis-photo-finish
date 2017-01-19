package com.coltsoftware.tis100

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class TokenizerTests {

    @Test
    fun empty() {
        assertThat(Tokenizer().tokens(""), `is`(emptyList<TS100Token>()))
    }

    @Test
    fun single_token() {
        assertThat(Tokenizer().tokens("MOV"), `is`(listOf(TS100Token(tokenString = "MOV", position = 0))))
    }

    @Test
    fun different_single_token() {
        assertThat(Tokenizer().tokens("ADD"), `is`(listOf(TS100Token(tokenString = "ADD", position = 0))))
    }
}

data class TS100Token(val tokenString: String, val position: Int)

class Tokenizer {
    fun tokens(s: String): List<TS100Token> {
        if (s.isEmpty())
            return emptyList()
        return listOf(TS100Token(tokenString = s, position = 0))
    }
}
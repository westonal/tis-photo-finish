package com.coltsoftware.tis100

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class TokenizerTests {

    @Test
    fun empty() {
        assertThat(Tokenizer().tokens(""), `is`(emptyList<Token>()))
    }

    @Test
    fun single_token() {
        assertThat(Tokenizer().tokens("MOV"),
                `is`(listOf(Token(tokenString = "MOV", position = 0))))
    }

    @Test
    fun different_single_token() {
        assertThat(Tokenizer().tokens("ADD"),
                `is`(listOf(Token(tokenString = "ADD", position = 0))))
    }

    @Test
    fun two_tokens() {
        assertThat(Tokenizer().tokens("ADD LEFT"),
                `is`(listOf(Token(tokenString = "ADD", position = 0),
                        Token(tokenString = "LEFT", position = 4)
                )))
    }

    @Test
    fun two_tokens_more_spaces() {
        assertThat(Tokenizer().tokens("  ADD   LEFT "),
                `is`(listOf(Token(tokenString = "ADD", position = 2),
                        Token(tokenString = "LEFT", position = 8)
                )))
    }

    @Test
    fun colon_is_own_token() {
        assertThat(Tokenizer().tokens("A:B"),
                `is`(listOf(Token(tokenString = "A", position = 0),
                        Token(tokenString = ":", position = 1),
                        Token(tokenString = "B", position = 2)
                )))
    }

    @Test
    fun colon_is_own_token_spaces() {
        assertThat(Tokenizer().tokens("A : B"),
                `is`(listOf(Token(tokenString = "A", position = 0),
                        Token(tokenString = ":", position = 2),
                        Token(tokenString = "B", position = 4)
                )))
    }
}


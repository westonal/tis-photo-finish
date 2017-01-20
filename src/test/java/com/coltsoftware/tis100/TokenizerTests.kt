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
        assertThat(Tokenizer().tokens("MOV"),
                `is`(listOf(TS100Token(tokenString = "MOV", position = 0))))
    }

    @Test
    fun different_single_token() {
        assertThat(Tokenizer().tokens("ADD"),
                `is`(listOf(TS100Token(tokenString = "ADD", position = 0))))
    }

    @Test
    fun two_tokens() {
        assertThat(Tokenizer().tokens("ADD LEFT"),
                `is`(listOf(TS100Token(tokenString = "ADD", position = 0),
                        TS100Token(tokenString = "LEFT", position = 4)
                )))
    }

    @Test
    fun two_tokens_more_spaces() {
        assertThat(Tokenizer().tokens("  ADD   LEFT "),
                `is`(listOf(TS100Token(tokenString = "ADD", position = 2),
                        TS100Token(tokenString = "LEFT", position = 8)
                )))
    }
}

data class TS100Token(val tokenString: String, val position: Int)

class Tokenizer {
    fun tokens(s: String): List<TS100Token> {
        if (s.isEmpty())
            return emptyList()
        val list = mutableListOf<TS100Token>()
        val currentToken = StringBuilder()
        var currentTokenStart = 0
        var index = 0
        s.forEach {
            if (it === ' ') {
                if (!currentToken.isEmpty()) {
                    list.add(TS100Token(tokenString = currentToken.toString(),
                            position = currentTokenStart))
                    currentToken.delete(0, currentToken.length)
                }
            } else {
                if (currentToken.isEmpty())
                    currentTokenStart = index
                currentToken.append(it)
            }
            index++
        }
        if (!currentToken.isEmpty())
            list.add(TS100Token(tokenString = currentToken.toString(),
                    position = currentTokenStart))
        return list
    }
}
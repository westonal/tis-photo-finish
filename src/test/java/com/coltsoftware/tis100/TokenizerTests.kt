package com.coltsoftware.tis100

import org.junit.Assert
import org.junit.Test

class TokenizerTests {

    @Test
    fun empty() {
        Assert.assertThat(Tokenizer().tokens(""), org.hamcrest.core.IsEqual<Any>(emptyList<Any>()))
    }
}

class Tokenizer {
    fun tokens(s: String): List<Any> {
        return emptyList()
    }
}
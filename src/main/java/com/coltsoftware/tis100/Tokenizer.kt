package com.coltsoftware.tis100

class Tokenizer {
    fun tokens(s: String): List<Token> {
        if (s.isEmpty())
            return emptyList()
        val list = mutableListOf<Token>()
        val currentToken = StringBuilder()
        var currentTokenStart = 0
        var index = 0
        s.forEach {
            if (it === ' ') {
                if (!currentToken.isEmpty()) {
                    list.add(Token(tokenString = currentToken.toString(),
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
            list.add(Token(tokenString = currentToken.toString(),
                    position = currentTokenStart))
        return list
    }
}
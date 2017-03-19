package com.coltsoftware.tis100

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
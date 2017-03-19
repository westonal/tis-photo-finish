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
            when (it) {
                ' ' -> closeToken(currentToken, currentTokenStart, list)
                ':' -> {
                    closeToken(currentToken, currentTokenStart, list)
                    currentToken.append(it)
                    closeToken(currentToken, index, list)
                }
                else -> {
                    if (currentToken.isEmpty())
                        currentTokenStart = index
                    currentToken.append(it)
                }
            }
            index++
        }
        if (!currentToken.isEmpty())
            list.add(Token(tokenString = currentToken.toString(),
                    position = currentTokenStart))
        return list
    }

    private fun closeToken(currentToken: StringBuilder, currentTokenStart: Int, list: MutableList<Token>) {
        if (!currentToken.isEmpty()) {
            list.add(Token(tokenString = currentToken.toString(),
                    position = currentTokenStart))
            currentToken.delete(0, currentToken.length)
        }
    }
}
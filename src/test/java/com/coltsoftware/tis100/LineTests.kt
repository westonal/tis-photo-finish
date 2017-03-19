package com.coltsoftware.tis100

import org.junit.Test
import kotlin.test.assertEquals

class LineTests {
    @Test
    fun mov_read() {
        val l = LineBuilder(Tokenizer().tokens("MOV 1 ACC"))
                .nextLine()
        assertEquals(Operation.MOV, l.operation)
    }

    @Test
    fun nop_read() {
        val l = LineBuilder(Tokenizer().tokens("NOP"))
                .nextLine()
        assertEquals(Operation.NOP, l.operation)
    }


}

data class Line(val operation: Operation)//, var arg1: Token, var arg2: Token)

enum class Operation {MOV, NOP }

class LineBuilder(val tokens: List<Token>) {
    fun nextLine(): Line {
        val opToken = tokens.get(0)
        return Line(tokenToOperation(opToken))
    }

    private fun tokenToOperation(opToken: Token): Operation {
        var op = Operation.MOV
        when (opToken.tokenString) {
            "MOV" -> op = Operation.MOV
            "NOP" -> op = Operation.NOP
        }
        return op
    }
}
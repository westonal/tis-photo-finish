package com.coltsoftware.tis100

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LineTests {
    @Test
    fun mov_read() {
        val l = LineBuilder(Tokenizer().tokens("MOV 1 ACC"))
                .nextLine()
        assertEquals(Operation.MOV, l.operation)
        assertEquals("1", l.source?.token?.tokenString)
        assertEquals("ACC", l.destination?.token?.tokenString)
        assertNull(l.label)
    }

    @Test
    fun nop_read() {
        val l = LineBuilder(Tokenizer().tokens("NOP"))
                .nextLine()
        assertEquals(Operation.NOP, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertNull(l.label)
    }


}

class Line(val operation: Operation,
           val source: Source?,
           val destination: Destination?,
           val label: Token?
)

class Source(val token: Token)

class Destination(val token: Token)

enum class Operation {MOV, NOP }

class LineBuilder(val tokens: List<Token>) {
    private var idx = 0

    fun nextLine(): Line {
        when (tokens[idx++].tokenString) {
            "MOV" -> return Line(Operation.MOV, nextSource(), nextDestination(), null)
            "NOP" -> return Line(Operation.NOP, null, null, null)
            else -> throw RuntimeException("")
        }
    }

    private fun nextSource() = Source(tokens[idx++])

    private fun nextDestination() = Destination(tokens[idx++])
}
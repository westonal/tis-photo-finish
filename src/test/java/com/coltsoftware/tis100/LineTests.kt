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

    @Test
    fun swap_read() {
        val l = LineBuilder(Tokenizer().tokens("SWP"))
                .nextLine()
        assertEquals(Operation.SWP, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertNull(l.label)
    }

    @Test
    fun sav_read() {
        val l = LineBuilder(Tokenizer().tokens("SAV"))
                .nextLine()
        assertEquals(Operation.SAV, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertNull(l.label)
    }

    @Test
    fun neg_read() {
        val l = LineBuilder(Tokenizer().tokens("NEG"))
                .nextLine()
        assertEquals(Operation.NEG, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertNull(l.label)
    }

    @Test
    fun add_read() {
        val l = LineBuilder(Tokenizer().tokens("ADD ACC"))
                .nextLine()
        assertEquals(Operation.ADD, l.operation)
        assertEquals("ACC", l.source?.token?.tokenString)
        assertNull(l.destination)
        assertNull(l.label)
    }

    @Test
    fun sub_read() {
        val l = LineBuilder(Tokenizer().tokens("SUB 10"))
                .nextLine()
        assertEquals(Operation.SUB, l.operation)
        assertEquals("10", l.source?.token?.tokenString)
        assertNull(l.destination)
        assertNull(l.label)
    }

    @Test
    fun jmp_read() {
        val l = LineBuilder(Tokenizer().tokens("JMP EXIT"))
                .nextLine()
        assertEquals(Operation.JMP, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertEquals("EXIT", l.label?.token?.tokenString)
    }

    @Test
    fun jez_read() {
        val l = LineBuilder(Tokenizer().tokens("JEZ EQZ"))
                .nextLine()
        assertEquals(Operation.JEZ, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertEquals("EQZ", l.label?.token?.tokenString)
    }

    @Test
    fun jnz_read() {
        val l = LineBuilder(Tokenizer().tokens("JNZ NEQZ"))
                .nextLine()
        assertEquals(Operation.JNZ, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertEquals("NEQZ", l.label?.token?.tokenString)
    }

    @Test
    fun jgz_read() {
        val l = LineBuilder(Tokenizer().tokens("JGZ GTZ"))
                .nextLine()
        assertEquals(Operation.JGZ, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertEquals("GTZ", l.label?.token?.tokenString)
    }

    @Test
    fun jlz_read() {
        val l = LineBuilder(Tokenizer().tokens("JLZ LTZ"))
                .nextLine()
        assertEquals(Operation.JLZ, l.operation)
        assertNull(l.source)
        assertNull(l.destination)
        assertEquals("LTZ", l.label?.token?.tokenString)
    }

    @Test
    fun jro_read() {
        val l = LineBuilder(Tokenizer().tokens("JRO LEFT"))
                .nextLine()
        assertEquals(Operation.JRO, l.operation)
        assertEquals("LEFT", l.source?.token?.tokenString)
        assertNull(l.destination)
        assertNull(l.label)
    }


}

class Line(val operation: Operation,
           val source: Source?,
           val destination: Destination?,
           val label: Label?
)

class Source(val token: Token)

class Destination(val token: Token)

class Label(val token: Token)

enum class Operation {
    MOV,
    NOP,
    SWP,
    SAV,
    JMP,
    ADD,
    SUB,
    NEG,
    JEZ,
    JNZ,
    JGZ,
    JLZ,
    JRO
}

class LineBuilder(val tokens: List<Token>) {
    private var idx = 0

    fun nextLine(): Line {
        when (tokens[idx++].tokenString) {
            "MOV" -> return Line(Operation.MOV, nextSource(), nextDestination(), null)
            "ADD" -> return Line(Operation.ADD, nextSource(), null, null)
            "SUB" -> return Line(Operation.SUB, nextSource(), null, null)
            "NOP" -> return Line(Operation.NOP, null, null, null)
            "SWP" -> return Line(Operation.SWP, null, null, null)
            "NEG" -> return Line(Operation.NEG, null, null, null)
            "SAV" -> return Line(Operation.SAV, null, null, null)
            "JMP" -> return Line(Operation.JMP, null, null, nextLabel())
            "JEZ" -> return Line(Operation.JEZ, null, null, nextLabel())
            "JNZ" -> return Line(Operation.JNZ, null, null, nextLabel())
            "JGZ" -> return Line(Operation.JGZ, null, null, nextLabel())
            "JLZ" -> return Line(Operation.JLZ, null, null, nextLabel())
            "JRO" -> return Line(Operation.JRO, nextSource(), null, null)
            else -> throw RuntimeException("")
        }
    }

    private fun nextSource() = Source(tokens[idx++])
    private fun nextDestination() = Destination(tokens[idx++])
    private fun nextLabel() = Label(tokens[idx++])
}
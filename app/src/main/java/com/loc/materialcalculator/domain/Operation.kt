package com.loc.materialcalculator.domain

enum class Operation(val symbol: Char) {
    Add('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    PERCENT('%'),
}

val operationSymbols = Operation.entries.map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char): Operation {
    return Operation.entries.find { it.symbol == symbol } ?: throw IllegalArgumentException("Invalid symbol")
}
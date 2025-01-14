package com.loc.materialcalculator.domain

class ExpressionParser(
    private val calculation: String
) {
    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()

        var i = 0
        while (i < calculation.length) {
            val currChar = calculation[i]
            when {
                currChar in operationSymbols -> {
                    result.add(ExpressionPart.Op(operationFromSymbol(currChar)))
                }

                currChar.isDigit() -> {
                    i = parseNumber(i, result)
                    continue
                }

                currChar in "()" -> {
                    parseParenthesis(currChar, result)
                }
            }
            i++
        }
        return result
    }

    private fun parseParenthesis(currentChar: Char, result: MutableList<ExpressionPart>) {
        result.add(
            ExpressionPart.Parentheses(
                type = when (currentChar) {
                    '(' -> ParenthesesType.Opening
                    ')' -> ParenthesesType.Closing
                    else -> throw IllegalArgumentException("Invalid parenthesis type")
                }
            )
        )
    }

    private fun parseNumber(startingIndex: Int, result: MutableList<ExpressionPart>): Int {
        var i = startingIndex
        val numberAsString = buildString {
            while(i < calculation.length && calculation[i] in "0123456789.") {
                append(calculation[i])
                i++
            }
        }
        result.add(ExpressionPart.Number(numberAsString.toDouble()))
        return i
    }
}
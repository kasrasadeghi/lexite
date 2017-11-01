package main

import java.util.*

enum class TokenType(val pattern: String) {
  IntLiteral("-?[0-9]+"),
  StringLiteral("\".*?\""),
  //NewLine("\n"),
  Word("[a-z]+"),
  LParen("\\("),
  RParen("\\)"),
  LBrace("\\{"),
  RBrace("\\}"),
  RArrow("->"),
  Plus("\\+"),
}

data class Token(val type: TokenType, val value: String) {
  override fun toString(): String {
    return "(${type.name} $value)"
  }
}

class TokenStack(private val tokens: LinkedList<Token>) {
  fun pop(p: (Token) -> Boolean): Token {
    if (tokens.isEmpty() || p(tokens.peek())) throw IllegalStateException("Expecting more tokens to pop.")
    return tokens.pop()
  }

  fun pop(t: TokenType): Token {
    return pop { it.type == t }
  }

  fun empty(): Boolean {
    return tokens.isEmpty()
  }

  fun isNotEmpty(): Boolean {
    return tokens.isNotEmpty()
  }
}

fun lex(contents: String): LinkedList<Token> {
  val result = LinkedList<Token>()

  val matcher = TokenType.values()
      .joinToString(separator = "") { type -> "|(?<${type.name}>${type.pattern})" }
      .substring(1)
      .toPattern()
      .matcher(contents)

  while (matcher.find()) {
    val type = TokenType.values().first { matcher.group(it.name) != null }
    result.add(Token(type, matcher.group(type.name)))
  }

  return result
}
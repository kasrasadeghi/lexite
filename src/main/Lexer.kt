package main

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

data class Token(var type: TokenType, var value: String) {
  override fun toString(): String {
    return "(${type.name} $value)"
  }
}

fun lex(contents: String): List<Token> {
  var result = ArrayList<Token>()

  val matcher = TokenType.values()
      .joinToString(separator = "") { type ->  "|(?<${type.name}>${type.pattern})" }
      .substring(1)
      .toPattern()
      .matcher(contents)

  while (matcher.find()) {
    for (type in TokenType.values()) {
      if (matcher.group(type.name) != null) {
        result.add(Token(type, matcher.group(type.name)))
        break
      }
    }
  }

  return result
}
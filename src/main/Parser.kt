package main

data class Node(val value: String,
                val children: ArrayList<Node> = ArrayList(),
                var parent: Node? = null) {
  operator fun plusAssign(n: Node) {
    children += n
    n.parent = this
  }

  override fun toString(): String {
    val acc = StringBuilder()
    acc.append((1..level()).joinToString("") { "  " })
    acc.append("($value")
    if (children.isNotEmpty()) {
      acc.append("\n")
      acc.append(children.joinToString(""))
    }
    if (hasNextSibling()) {
      acc.append(")")
      acc.append("\n")
    } else {
      acc.append(")")
    }

    return acc.toString()
  }

  fun hasNextSibling(): Boolean {
    if (parent == null ) return false
    return parent!!.children.indexOf(this) != parent!!.children.size - 1
  }

  fun level(): Int {
    var l = 0
    var curr: Node? = this
    while (curr != null) {
      l += 1
      curr = curr.parent
    }
    return l - 1
  }
}

fun parse(tokens: TokenStack): Node {
  val parser = Parser(tokens)
  return parser.pProgram()
}

class Parser(private val tokens: TokenStack) {

  fun p(type: TokenType): Node
  { return Node(tokens.pop(type).value) }

  fun p(pred: (Token) -> Boolean)
  { if (pred(tokens.peek())) tokens.pop() }

  fun pProgram(): Node {
    val program = Node("program")
    while(tokens.isNotEmpty()) {
      program += pDefinition()
    }
    return program
  }

  private fun pDefinition(): Node {
    val type = pWord()
    val def = pWord()
    def += type
    p(TokenType.LParen)
    p(TokenType.RParen)
    def += pBlock()
    return def
  }

  private fun pBlock(): Node {
    p(TokenType.LBrace)
    val block = Node("Block")
    while (tokens.not(TokenType.RBrace)) block += pStatement()
    p(TokenType.RBrace)
    return block
  }

  private fun pWord(): Node
  { return p(TokenType.Word) }

  private fun pStatement(): Node {
    val statement = Node("print")
    p{ it.value == "print"}
    p(TokenType.LParen)
    statement += pStringLiteral()
    p(TokenType.RParen)
    return statement
  }

  private fun pStringLiteral(): Node {
    val value = tokens.pop(TokenType.StringLiteral).value
    //TODO check that substring "" returns emptystring
    return Node(value.substring(1, value.length))
  }
}
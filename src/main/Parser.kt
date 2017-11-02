package main

enum class NodeType {
  Program,
  Function,
  Type,
  Block,
  Call,
  StringLiteral,
  IntLiteral
}

data class Node(val type: NodeType,
                val value: String = "",
                val children: ArrayList<Node> = ArrayList(),
                var parent: Node? = null): Iterable<Node> {
  operator fun plusAssign(n: Node) {
    children.add(n)
    n.parent = this
  }

  operator fun get(i: Int): Node {
    return children[i]
  }

  override operator fun iterator(): MutableIterator<Node> {
    return children.iterator()
  }

  override fun toString(): String {
    operator fun (StringBuilder).plusAssign(a: String) {
      this.append(a)
    }

    val acc = StringBuilder()
    acc += (1..level()).joinToString("") { "  " }
    acc += "($type $value"
    if (children.isNotEmpty()) {
      acc += "\n${children.joinToString("")}"
    }
    acc += if (hasNextSibling()) ")\n" else ")"

    return acc.toString()
  }

  private fun hasNextSibling(): Boolean {
    if (parent == null) return false
    return parent!!.children.indexOf(this) != parent!!.children.size - 1
  }

  private fun level(): Int {
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

  fun p(type: TokenType): Node {
    return p(NodeType.valueOf(type.name), type)
  }

  fun pop(type: TokenType) {
    p { it.type == type }
  }

  fun p(pred: (Token) -> Boolean) {
    if (pred(tokens.peek())) tokens.pop()
  }

  fun p(type: NodeType, t: TokenType): Node {
    return Node(type, tokens.pop(t).value)
  }

  fun pProgram(): Node {
    val program = Node(NodeType.Program)
    while (tokens.isNotEmpty()) {
      program += pDefinition()
    }
    return program
  }

  private fun pDefinition(): Node {
    val type = pType()
    val def = pFunctionName()
    def += type
    pop(TokenType.LParen)
    pop(TokenType.RParen)
    def += pBlock()
    return def
  }

  private fun pFunctionName(): Node {
    return p(NodeType.Function, TokenType.Word)
  }

  private fun pCall(): Node {
    return p(NodeType.Call, TokenType.Word)
  }

  private fun pType(): Node {
    return p(NodeType.Type, TokenType.Word)
  }

  private fun pBlock(): Node {
    pop(TokenType.LBrace)
    val block = Node(NodeType.Block)
    while (tokens.not(TokenType.RBrace)) block += pStatement()
    pop(TokenType.RBrace)
    return block
  }

  private fun pStatement(): Node {
    val statement = pCall()
    p { it.value == "print" }
    pop(TokenType.LParen)
    statement += pStringLiteral()
    pop(TokenType.RParen)
    return statement
  }

  private fun pStringLiteral(): Node {
    val value = tokens.pop(TokenType.StringLiteral).value
    return Node(NodeType.StringLiteral, value)
  }
}
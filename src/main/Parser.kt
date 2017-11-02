package main

data class Node(val value: String,
                val children: ArrayList<Node> = ArrayList()) {
  operator fun plusAssign(n: Node) {
    children += n
  }
}

fun parse(tokens: TokenStack): Node {
  val parser = Parser(tokens)
  return parser.pProgram()
}

class Parser(private val stack: TokenStack) {

  fun p(type: TokenType): Node {
    return Node(stack.pop(type).value)
  }

  fun pProgram(): Node {
    val program = Node("program")
    while(stack.isNotEmpty()) {
      program += pDefinition()
    }
    return program
  }

  private fun pDefinition(): Node {
    val type = pType()
    val def = pFunctionName()
    p(TokenType.LParen)
    p(TokenType.RParen)
    def += pBlock()
    return def
  }

  private fun pBlock(): Node {
    p(TokenType.LBrace)
    val block = Node("Block")
    p(TokenType.RBrace)
  }

  private fun pWord(): Node {
    return p(TokenType.Word)
  }

  private fun pType(): Node {
    return pWord()
  }

  private fun pFunctionName(): Node {
    return pWord()
  }



}
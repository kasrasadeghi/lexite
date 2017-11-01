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

  fun p(type: TokenType): String {
    return stack.pop(type).value
  }

  fun pProgram(): Node {
    val program = Node("program")
    while(stack.isNotEmpty()) {
      program += pDefinition()
    }
    return program
  }

  private fun pDefinition(): Node {
    TODO("definition")
  }


}
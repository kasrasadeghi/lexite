package main

fun eval(node: Node) {
  eProgram(node)
}

fun eProgram(program: Node) {
  val main = program.first { it.value == "main" }
  eDefinition(main)
}

fun eDefinition(def: Node) {
  eBlock(def[1])
}

fun eBlock(block: Node) {
  for (statement in block) {
    ePrint(statement)
  }
}

fun ePrint(statement: Node) {
  val value = statement[0].value
  println(value.substring(1, value.length - 1))
}

package main

fun eval(node: Node) {
  eProgram(node)
}

fun eProgram(program: Node) {
  for (node in program) {
    eDefinition(node)
  }
}

fun eDefinition(def: Node) {
  eBlock(def[2])
}

fun eBlock(block: Node) {
  for (statement in block) {
    ePrint(statement)
  }
}

fun ePrint(statement: Node) {

}

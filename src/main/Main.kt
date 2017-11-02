package main

import java.nio.file.Paths

fun main(args: Array<String>) {
  val contents = Paths.get("examples/hello.k").toFile().readText()
  val tokens = lex(contents)

  val AST = parse(tokens)

  eval(AST)
}
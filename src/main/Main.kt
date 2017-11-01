package main

import java.nio.file.Paths

fun main(args: Array<String>) {
  val contents = Paths.get("examples/hello.k").toFile().readText()
  lex(contents).forEach{t -> println(t)}
}
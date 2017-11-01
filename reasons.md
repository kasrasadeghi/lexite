# Why am I writing this compiler?

- To learn about compilers
- To fix some things I personally do not like about some other languages, 
  while retaining enough similarity to them so that the transition is smooth
- To write a stack based interpreter as a common backend for compiler optimization

# So what languages are people supposed to transfer from, or might know before?

Java, C, or C++.

# What's wrong with C?

- The ambiguity of '*'
- Extra semicolons

# What's wrong with C++?

- The ambiguity of >> in templates

# What's wrong with Java?

- Everything is a monitor, but monitors have the fatal nested monitor problem
  - Investigate Pony's object model for ideas about actor programming
- No type inference
- No operator overloading
- No reference semantics
  - This may be a good thing (?). Depends on whether or not the lang has GC.
- Requires a class per file.
  - Use namespace per file instead.
- Way too verbose ("public static" everywhere)
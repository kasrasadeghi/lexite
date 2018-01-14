# cornerstone
A compiler for a C-like language.

# NOTE

The original plan for this was to make a C-like language with enough abstractions 
that making a compiler for it will be ~~easy~~ possible. As time went on, I realized 
that the abstractions I was using included streams and regexes, both of which I did 
not know the exact implementation details of in C or Assembly.

I got to thinking. What if I redesigned this architecture to be much more modular?

Currently compilers (as I understand it) have the following structure:

String --(Lexical Analysis)-->      List of Tokens 
       --(Syntactic Analysis)-->    Tree 
       --(Semantic Analysis)-->*    Tree 
       --(Generator/Interpreter)--> ASM/Result

But what if I plugged in a different string?

My current plan was to have a compiled C-like language that can bootstrap itself, but 
the lexing and parsing stages are going to be rather complex, and I'd have to work up
to that level of complexity. To simplify the work, I could do:

C-like --(S1)-> Lisp-like --(S2)-> Stack-like --(S3)-> ASM

S1  = cornerstone :: C-like -> Lisp-like
S2  = backbone    :: Lisp-like -> Stack-like
S3  = ferra       :: Stack-like -> ASM       // generates asm
S3' = otto        :: Stack-like -> IO ()     // interprets the stack-like language

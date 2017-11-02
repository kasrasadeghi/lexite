This is the directory where I work out how C is compiled with GCC.

Commands:

$ gcc -S -fno-asynchronous-unwind-tables hello.c

The fno... flag is for removing the .cfi-* gnu directives. They're a
directive that stores debugging information about preserving call
stacks and unwinding them during exceptions.

$ gcc hello.s -o hello

Assembles the object file and links it.

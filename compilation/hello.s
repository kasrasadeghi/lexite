	.file	"hello.c"
	.section	.rodata
.A:
	.string	"hello"
.:
	.string	"hello again"
	.text
	.globl	main
	.type	main, @function
main:
	pushq	%rbp
	movq	%rsp, %rbp
	leaq	.LC0(%rip), %rdi
	call	puts@PLT
	leaq	.LC1(%rip), %rdi
	call	puts@PLT
	movl	$0, %eax
	popq	%rbp
	ret
	.size	main, .-main
	.section	.rodata
.LC2:
	.string	"hi"
.LC3:
	.string	"will"
.LC4:
	.string	""
	.text
	.globl	temp
	.type	temp, @function
temp:
	pushq	%rbp
	movq	%rsp, %rbp
	leaq	.LC2(%rip), %rdi
	call	puts@PLT
	leaq	.LC3(%rip), %rdi
	call	puts@PLT
	leaq	.LC4(%rip), %rdi
	call	puts@PLT
	nop
	popq	%rbp
	ret
	.size	temp, .-temp
	.ident	"GCC: (GNU) 7.2.0"
	.section	.note.GNU-stack,"",@progbits

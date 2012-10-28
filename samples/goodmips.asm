.data

helloworld: .asciiz "Hello World!"

.text
printHello:
    addi $r2, $r0, 4
    lui  $r4, helloworld
    ori  $r4, $r4, helloworld
    syscall
    j exit

main:
    j printHello

exit:
    addi $r2, $r0, 10
    syscall


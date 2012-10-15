.data
.text
main:
#derp this. is a comment
    add $r2, $r2, $r3 #this too is a comment
    sll $r2, $r3, 10
    thing2:
    and $r1, $r5, $r9
label_w_comment: #comment following a label
    beq $r2, $r30, -4
    lui $r5, SOME_LABEL
    ori $r5, $r5, SOME_LABEL
    lw  $r2, 12( $r5 )

#here are some multi-line comments
# comment is getting longer lul. more stufff
# WHY YOU BREAK????

    sw $r1, 15($r16)
    label:

    addi $r1, $r2, 0x10

    sw $r4, 0xffff( $r2 )

    j 94
    
    j SOME_LABEL


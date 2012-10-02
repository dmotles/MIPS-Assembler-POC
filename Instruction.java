public enum Instruction {

    ADDI ((byte)0x8, (byte)0x0),
     
    ADD ((byte)0x0, (byte)0x20),
    SUB ((byte)0x0, (byte)0x22),
    SLT ((byte)0x0, (byte)0x2a),
    BEQ ((byte)0x4, (byte)0x0),
     
    BNE ((byte)0x5, (byte)0x0),
     
    SYSCALL ((byte)0x0, (byte)0xc),
    LW ((byte)0x23, (byte)0x0),
     
    SW ((byte)0x2b, (byte)0x0),
     
    J ((byte)0x2, (byte)0x0),
     
    SLL ((byte)0x0, (byte)0x0),
    LUI ((byte)0xf, (byte)0x0),
     
    AND ((byte)0x0, (byte)0x24),
    ORI ((byte)0xd, (byte)0x0),
     
    NOR ((byte)0x0, (byte)0x27);

    byte opcode;
    byte funct;
 
    Instruction( byte opcode, byte funct ) {
        this.opcode = opcode;
        this.funct = funct;
    }
}

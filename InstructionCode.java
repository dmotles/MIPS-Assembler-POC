public enum InstructionCode {

    ADDI ((byte)0x8, (byte)0x0, false, IFormatters.IFMT, ImmProcessors.LOWER16 ),
     
    ADD ((byte)0x0, (byte)0x20, false, IFormatters.RFMT, ImmProcessors.NONE ),
    SUB ((byte)0x0, (byte)0x22, false, IFormatters.RFMT, ImmProcessors.NONE ),
    SLT ((byte)0x0, (byte)0x2a, false, IFormatters.RFMT, ImmProcessors.NONE ),
    BEQ ((byte)0x4, (byte)0x0, true, IFormatters.BFMT, ImmProcessors.BRANCH ),
     
    BNE ((byte)0x5, (byte)0x0, true, IFormatters.BFMT, ImmProcessors.BRANCH ),
     
    SYSCALL ((byte)0x0, (byte)0xc, false, IFormatters.SYSFMT, ImmProcessors.NONE ),
    LW ((byte)0x23, (byte)0x0, false, IFormatters.MEMFMT, ImmProcessors.LOWER16 ),
     
    SW ((byte)0x2b, (byte)0x0, false, IFormatters.MEMFMT, ImmProcessors.LOWER16 ),
     
    J ((byte)0x2, (byte)0x0, true, IFormatters.JFMT, ImmProcessors.JUMP ),
     
    SLL ((byte)0x0, (byte)0x0, true, IFormatters.SHIFTFMT, ImmProcessors.SHAMT ),
    LUI ((byte)0xf, (byte)0x0, true, IFormatters.ISINGLEREG, ImmProcessors.UPPER16 ),
     
    AND ((byte)0x0, (byte)0x24, false, IFormatters.RFMT, ImmProcessors.NONE ),
    ORI ((byte)0xd, (byte)0x0, true, IFormatters.IFMT, ImmProcessors.LOWER16 ),
     
    NOR ((byte)0x0, (byte)0x27, false, IFormatters.RFMT, ImmProcessors.NONE );

    byte opcode;
    byte funct;
    boolean canHasLabel;
    InstructionPrettyFormatter out; 
    Immediate imm;


 
    InstructionCode( byte opcode, byte funct, boolean label, InstructionPrettyFormatter o, Immediate i ) {
        this.opcode = opcode;
        this.funct = funct;
        this.canHasLabel = label;
        this.out = o;
        this.imm = i;
    }
}

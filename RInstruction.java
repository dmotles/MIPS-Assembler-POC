public class RInstruction extends Instruction {
    private int regsource;
    private int regtarget;
    private int regdest;
    private int shiftamt;

    public RInstruction( int line, int col, String opname, int rs, int rt, int rd ) throws IllegalArgumentException {
        super( line, col, opname );
        regsource = rs;
        regtarget = rt;
        regdest = rd;
        shiftamt = 0;
    }

    public RInstruction( int line, int col, String opname, int rs, int rt, int rd, int shamt ) {
        this( line, col, opname, rs, rt, rd );
        shiftamt = shamt;
    }

    public boolean resolveLabel( long addr ) {
        return true;
    }

    public long getEncoded() {
        long inst = super.getEncoded();
        long shamt =  ( shiftamt << SHAMSHIFT );
        long rd = ( regdest << RD_SHIFT );
        long rt = ( regtarget << RT_SHIFT );
        long rs = ( regsource << RS_SHIFT );
        inst |= shamt | rd | rt | rs;
        //System.err.printf("shamt: %x, rd: %x, rt: %x, rs: %x\n", shamt, rd, rt, rs );
        return inst;
    }
    public String toString() {
        return String.format( "%s $%d, $%d, $%d(OP:%X, FUNCT:%X, INST:%08X)", code.toString(), regdest, regsource, regtarget, code.opcode, code.funct, getEncoded() );
    }
}

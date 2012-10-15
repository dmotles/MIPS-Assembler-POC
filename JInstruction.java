public class JInstruction extends Instruction {

    public JInstruction( int line, int col, String opname, long imm )
        throws Exception
    {
        super( line, col, opname, imm );
        if( imm > 67108863L || imm < -33554432L ) {
            String msg = opname.toLowerCase() + " on line " + line;
            msg += " has an immediate value larger than 26 bits.";
            throw new Exception( msg );
        }
    }

    public JInstruction( int line, int col, String opname, String l )
        throws Exception
    {
        super( line, col, opname, l );
        if( ! code.canHasLabel ) {
            String msg = code.toString() + " on line: " + line;
            msg += " had a label. This operation does not take labels.";
            throw new Exception( msg );
        }
    }

    public boolean resolveLabel( long addr ) {
        if( code.canHasLabel) {
            if( code.imm == ImmProcessors.UPPER16 ) {
                addr = addr >>> 16;
                immediate = addr;
            } else if( code.imm == ImmProcessors.BRANCH ) {
                addr = addr - ( address + 4 );
                addr = addr >> 2;
                immediate = addr;
            } else if( code.imm == ImmProcessors.JUMP ) {
                immediate = addr >> 2;
            } else {
                immediate = addr;
            }
        }
        return true;
    }

    public int getRD() { return 0; }
    public int getRS() { return 0; }
    public int getRT() { return 0; }

    public long getEncoded() {
        long inst = super.getEncoded();
        long imm =  processImmediate();
        inst |= imm;
        //System.err.printf("shamt: %x, rd: %x, rt: %x, rs: %x\n", shamt, rd, rt, rs );
        return inst;
    }
}

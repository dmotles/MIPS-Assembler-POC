public class IInstruction extends Instruction {
    private int regsource;
    private int regtarget;

    public IInstruction( int line, int col, String opname, int rs, int rt, int imm )
        throws Exception
    {
        super( line, col, opname, imm );
        regsource = rs;
        regtarget = rt;
        if( imm > 65535 || imm < Short.MIN_VALUE ) {
            String msg = opname.toLowerCase() + " on line " + line;
            msg += " has an immediate value larger than 16 bits.";
            throw new Exception( msg );
        }
    }

    public IInstruction( int line, int col, String opname, int rs, int rt, String l )
        throws Exception
    {
        super( line, col, opname, l );
        regsource = rs;
        regtarget = rt;
        if( ! code.canHasLabel ) {
            String msg = code.toString() + " on line: " + line;
            msg += " had a label. This operation does not take labels.";
            throw new Exception( msg );
        }
    }

    public boolean resolveLabel( long addr ) {
        return true;
    }

    public int getRD() { return 0; }
    public int getRS() { return regsource & FIVEBIT_MASK; }
    public int getRT() { return regtarget & FIVEBIT_MASK; }

    public long getEncoded() {
        long inst = super.getEncoded();
        long imm =  processImmediate();
        long rt = ( regtarget << RT_SHIFT );
        long rs = ( regsource << RS_SHIFT );
        inst |= imm | rt | rs;
        //System.err.printf("shamt: %x, rd: %x, rt: %x, rs: %x\n", shamt, rd, rt, rs );
        return inst;
    }
}

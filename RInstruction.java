public class RInstruction extends Instruction {
    private int regsource;
    private int regtarget;
    private int regdest;

    public RInstruction( int line, int col, String opname, int rs, int rt, int rd, long shamt )
        throws Exception
    {
        super( line, col, opname, shamt );
        regsource = rs;
        regtarget = rt;
        regdest = rd;
        if( shamt > 32 ) {
            String msg = opname.toLowerCase() + " on line: " + line;
            msg += " was larger than 32, which cannot be encoded in 5 bits.";
            throw new Exception( msg );
        }
    }

    public boolean resolveLabel( long addr ) {
        return true;
    }
    public int getRD() { return regdest & FIVEBIT_MASK; }
    public int getRS() { return regsource & FIVEBIT_MASK; }
    public int getRT() { return regtarget & FIVEBIT_MASK; }

    public long getEncoded() {
        long inst = super.getEncoded();
        long shamt = processImmediate() << SHAMSHIFT;
        long rd = ( regdest << RD_SHIFT );
        long rt = ( regtarget << RT_SHIFT );
        long rs = ( regsource << RS_SHIFT );
        inst |= shamt | rd | rt | rs;
        //System.err.printf("shamt: %x, rd: %x, rt: %x, rs: %x\n", shamt, rd, rt, rs );
        return inst;
    }
}

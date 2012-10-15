public abstract class Instruction {
    public static final int FUNCTSHIFT = 0;
    public static final int SHAMSHIFT = 6;
    public static final int RD_SHIFT = 11;
    public static final int RT_SHIFT = 16;
    public static final int RS_SHIFT = 21;
    public static final int OPSHIFT = 26;

    public static final int FIVEBIT_MASK = 0x01F;
    public static final long WORD_MASK = 0x00000000FFFFFFFFL;
    private int linenum;
    private int colnum;
    protected long address;
    protected String label;
    protected InstructionCode code;
    protected long immediate;


    public Instruction( int line, int col, String opname ) {
        code = InstructionCode.valueOf( opname );
        linenum = line;
        colnum = col;
        label = null;
        address = 0;
        immediate = 0;
    }

    public Instruction( int line, int col, String opname, String l ) {
        this( line, col, opname );
        label = l;
    }

    public Instruction( int line, int col, String opname, long i ) {
        this( line, col, opname );
        immediate = i;
    }

    public long processImmediate() {
        return code.imm.stripUnnecessaryBits( address, immediate );
    }

    public int lineNum() {
        return linenum;
    }

    public int colNum() {
        return colnum;
    }

    public boolean hasLabel() {
        return ( code.canHasLabel && label != null );
    }

    public String getLabel() {
        return label;
    }

    public void setAddress( long a ) {
        address = a;
    }

    public long getAddr() {
        return address;
    }

    public abstract boolean resolveLabel( long addr );
    public abstract int getRD();
    public abstract int getRS();
    public abstract int getRT();

    public long getEncoded() {
        long inst = 0;
        inst |= ( code.opcode << OPSHIFT );
        inst |= ( code.funct << FUNCTSHIFT );
        return inst & WORD_MASK ;
    }

    public String toString() {
        return code.out.string( address, 
                         code,
                         getRD(),
                         getRS(),
                         getRT(),
                         processImmediate(),
                         label,
                         getEncoded() );
    }
}

public abstract class Instruction {
    public static final int FUNCTSHIFT = 0;
    public static final int SHAMSHIFT = 6;
    public static final int RD_SHIFT = 11;
    public static final int RT_SHIFT = 16;
    public static final int RS_SHIFT = 21;
    public static final int OPSHIFT = 26;
    private int linenum;
    private int colnum;
    protected String label;
    protected InstructionCode code;


    public Instruction( int line, int col, String opname ) throws IllegalArgumentException {
        code = InstructionCode.valueOf( opname );
        linenum = line;
        colnum = col;
        label = null;
    }

    public Instruction( int line, int col, String opname, String l ) throws IllegalArgumentException {
        code = InstructionCode.valueOf( opname );
        linenum = line;
        colnum = col;
        label = l;
    }

    public int lineNum() {
        return linenum;
    }

    public int colNum() {
        return colnum;
    }

    public boolean hasLabel() {
        return label == null;
    }

    public String getLabel() {
        return label;
    }

    public abstract boolean resolveLabel( long addr );

    public long getEncoded() {
        long inst = 0;
        inst |= ( code.opcode << OPSHIFT );
        inst |= ( code.funct << FUNCTSHIFT );
        return inst;
    }

    public String toString() {
        return String.format( "%s(OP:%X, FUNCT:%X, INST:%08X)", code.toString(), code.opcode, code.funct, getEncoded() );
    }
}

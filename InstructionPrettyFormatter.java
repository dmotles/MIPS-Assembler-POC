public interface InstructionPrettyFormatter {
    public final static String HWORD = "0x%08X";
    public final static String REG   = "$r%d";
    public final static String HEX   = "0x%X";
    public final static String META  = "(op:" + HEX + ",funct" + HEX + ",code:" + HWORD + ")";

    public abstract String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e );
}

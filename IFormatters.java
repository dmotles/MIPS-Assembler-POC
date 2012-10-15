public class IFormatters {
    public static final InstructionPrettyFormatter RFMT = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            return String.format( HWORD + ": %s " + REG + ", " + REG + ", " + REG + META,
                                                               addr,
                                                               c.toString(),
                                                               rd,
                                                               rs,
                                                               rt,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };

    public static final InstructionPrettyFormatter SHIFTFMT = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            return String.format( HWORD + ": %s " + REG + ", " + REG + ", %d" + META,
                                                               addr,
                                                               c.toString(),
                                                               rd,
                                                               rt,
                                                               imm,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };

    public static final InstructionPrettyFormatter IFMT = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            String labelFmt = ( l != null && c.canHasLabel  ) ? "%s" : "%d";
            Object immOrLab = ( l != null && c.canHasLabel  ) ? l : imm;
            return String.format( HWORD + ": %s " + REG + ", " + REG + ", " + labelFmt + META,
                                                               addr,
                                                               c.toString(),
                                                               rt,
                                                               rs,
                                                               immOrLab,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };

    public static final InstructionPrettyFormatter BFMT = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            String labelFmt = ( l != null && c.canHasLabel  ) ? "%s" : "%d";
            Object immOrLab = ( l != null && c.canHasLabel  ) ? l : imm;
            return String.format( HWORD + ": %s " + REG + ", " + REG + ", " + labelFmt + META,
                                                               addr,
                                                               c.toString(),
                                                               rs,
                                                               rt,
                                                               immOrLab,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };

    public static final InstructionPrettyFormatter ISINGLEREG = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            String labelFmt = ( l != null && c.canHasLabel  ) ? "%s" : "%d";
            Object immOrLab = ( l != null && c.canHasLabel  ) ? l : imm;
            return String.format( HWORD + ": %s " + REG + ", " + labelFmt + META,
                                                               addr,
                                                               c.toString(),
                                                               rt,
                                                               immOrLab,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };

    public static final InstructionPrettyFormatter MEMFMT = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            return String.format( HWORD + ": %s " + REG + ", %d(" + REG + ") " + META,
                                                               addr,
                                                               c.toString(),
                                                               rt,
                                                               imm,
                                                               rs,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };

    public static final InstructionPrettyFormatter JFMT = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            String labelFmt = ( l != null && c.canHasLabel  ) ? "%s" : "%d";
            Object immOrLab = ( l != null && c.canHasLabel  ) ? l : imm;
            return String.format( HWORD + ": %s " + labelFmt + META,
                                                               addr,
                                                               c.toString(),
                                                               immOrLab,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };

    public static final InstructionPrettyFormatter SYSFMT = new InstructionPrettyFormatter() {
        public String string( long addr, InstructionCode c, int rd, int rs, int rt, long imm, String l, long e ) {
            return String.format( HWORD + ": syscall" + META,
                                                               addr,
                                                               c.opcode,
                                                               c.funct,
                                                               e );
        }

    };
}

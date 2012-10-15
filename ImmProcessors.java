public class ImmProcessors {
    public static final Immediate NONE = new Immediate() {
        public long stripUnnecessaryBits( long addr, long i ) {
            return 0;
        }
    };

    public static final Immediate SHAMT = new Immediate() {
        public long stripUnnecessaryBits( long addr, long i ) {
            return i & IMM5;
        }
    };

    public static final Immediate LOWER16 = new Immediate() {
        public long stripUnnecessaryBits( long addr, long i ) {
            return i & IMM16;
        }
    };
    
    public static final Immediate UPPER16 = new Immediate() {
        public long stripUnnecessaryBits( long addr, long i ) {
            //return ( i >> 16 ) & IMM16;
            return i & IMM16;
        }
    };

    public static final Immediate BRANCH = new Immediate() {
        public long stripUnnecessaryBits( long addr, long i ) {
            //long addrdiff = i - (addr + 4);
            //return ( addrdiff >> 2 ) & IMM16;
            return i & IMM16;
        }
    };

    public static final Immediate JUMP = new Immediate() {
        public long stripUnnecessaryBits( long addr, long i ) {
            //return ( i >> 2 ) & IMM26;
            return i & IMM26;
        }
    };
}

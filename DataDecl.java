import java.util.*;
public class DataDecl implements Iterable<Byte> {
    protected String label;
    protected long start_addr;
    protected int length;
    protected ArrayList<Byte> data;
    private int line;
    private int col;

    public static final long BYTEMASK = 0x00000000000000FFL;
    public static final long HWMASK = 0x000000000000FFFFL;
    public static final long WMASK = 0x00000000FFFFFFFFL;
    public static final Byte ZERO = new Byte( (byte)0 );

    public DataDecl( int l, int c, String lab ) {
        label = lab;
        data = new ArrayList<Byte>();
    }

    public DataDecl( int l, int c, String lab, int len ) {
        this( l,c,lab );
        pad( len );
    }

    public DataDecl( int l, int c, String lab, ArrayList<String> slist, boolean terminate ) {
        this(l,c,lab);

        for( String s: slist ) {
            addString( s, terminate );
        }
    }

    public DataDecl( int l , int c, String lab, ArrayList<Long> immlist, int bitcount ) throws Exception {
        this(l,c,lab);
        long MASK = 0;
        switch( bitcount ) {
            case 8:
                MASK = BYTEMASK;
                break;
            case 16:
                MASK = HWMASK;
                break;
            case 32:
                MASK = WMASK;
                break;
        }

        for( Long v: immlist ) {
            long val = v.longValue();
            if( ( val & ( ~MASK ) ) != 0 ) {
                String msg = "Value: " + val + " in data section label " + l;
                msg += " is greater than " + bitcount + " bits wide.";
                throw new Exception( msg );
            }

            for( int i = 0 ; i < bitcount; i += 8 ) {
                byte cur = (byte)(((val & MASK) >> i*8) & BYTEMASK);
                data.add( cur );
                length++;
            }
        }

        
    }

    public void addString( String s, boolean terminate ) {
        char [] strc = s.toCharArray();
        for( int i = 0; i < strc.length; i++ ) {
            data.add( new Byte( (byte)strc[i] ) );
        }

        length += strc.length;

        if( terminate ) { 
            data.add( ZERO );
            length++;
        }
    }

    public void pad( int amt ) {
        for( int i = 0; i < amt; i++ ) {
            data.add( ZERO );
            length++;
        }
    }

    public int length() {
        return length;
    }
    
    public void setStartAddress( long addr ) {
        start_addr = addr;
    }

    public String label() {
        return label;
    }

    public long address() {
        return start_addr;
    }

    public int line() {
        return line;
    }

    public int column() {
        return col;
    }

    public String toString() {
        return String.format( "%s(start:0x%08X,len:%d)", label, start_addr, length );
    }

    public Iterator<Byte> iterator() {
        return data.iterator();
    }
}

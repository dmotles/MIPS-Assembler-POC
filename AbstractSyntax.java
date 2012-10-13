import java.util.*;

public class AbstractSyntax {
    public static final long DATASEC_START = 0x1001000;
    Hashtable<String,Long> symbolTable;
    ArrayList<Instruction> instructionList;
    ArrayList<DataItem> dataList;
    private long nextaddr;

    public AbstractSyntax() {
        symbolTable = new Hashtable<String,Long>();
        instructionList = new ArrayList<Instruction>();
        dataList = new ArrayList<DataItem>();
        nextaddr = DATASEC_START;
    }

    public boolean containsSymbol( String sym ) {
        return symbolTable.contains( sym );
    }

    public boolean addSymbol( String sym, Long addr ) {
        return ( symbolTable.put( sym, addr ) == null );
    }

    public boolean addData( DataItem d ) {
        boolean ret = dataList.add( d );
        if( ret ) {
            nextaddr += d.length() / 4;
            if( d.length() % 4 > 0 ) {
                nextaddr += 4;
            }
        }
        return ret;
    }

    public boolean addInstruction( Instruction i ) {
        return addInstruction( i );
    }

    public long nextDataAddr() {
        return nextaddr;
    }
}

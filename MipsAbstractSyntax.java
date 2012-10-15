import java.util.*;

public class MipsAbstractSyntax {
    public static final long DATASEC_START = 0x1001000;
    Hashtable<String,Label> symtable;
    ArrayList<Instruction> ilist;

    MipsAbstractSyntax( TextSection ts ) {
        symtable = ts.getSymTable();
        ilist = ts.getInstructionList();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder( "MipsAbstractSyntax(\n\tSymbolTable(\n" );
        for( Label l : symtable.values() ) {
            sb.append( "\t\t" );
            sb.append( l.toString() );
            sb.append( "\n" );
        }
        sb.append( "\t)\n\tInstructions(\n" );
        for( Instruction i : ilist ) {
            sb.append( "\t\t" );
            sb.append( i.toString() );
            sb.append( "\n" );
        }
        sb.append( "\t)\n)" );
        return sb.toString();
    }

}

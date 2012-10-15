import java.util.*;

public class MipsAbstractSyntax {
    public static final long DATASEC_START = 0x1001000;
    Hashtable<String,Label> symtable;
    ArrayList<Instruction> ilist;
    ArrayList<DataDecl> data;

    MipsAbstractSyntax( TextSection ts ) {
        symtable = ts.getSymTable();
        ilist = ts.getInstructionList();
    }

    MipsAbstractSyntax( DataSection ds ) {
        symtable = ds.getSymTable();
        data = ds.getDataList();
    }

    MipsAbstractSyntax( DataSection ds, TextSection ts ) throws Exception {
        symtable = ts.getSymTable();
        Hashtable<String,Label> dsymtable = ds.getSymTable();
        data = ds.getDataList();
        ilist = ts.getInstructionList();

        for( Label l: dsymtable.values() ) {
            Label m = symtable.get( l.name() );
            if( m != null ) {
                String msg = "Symbol " + l.name() + " declared twice, once in data section";
                msg += " and once in text section.";
                throw new Exception(msg);
            }
            symtable.put( l.name(), l );
        }
    }
    
    public void ResolveAllLabels() {
        for( Instruction i : ilist ) {
            if( i.hasLabel() ) {
                String l = i.getLabel();
                Label match = symtable.get( l );
                if( match == null ) resolveError( i );
                else
                    i.resolveLabel( match.address() );
            }
        }
    }

    private void resolveError( Instruction i ) {
        System.err.println( "Unable to resolve: " + i );
    }

    public String assemble() {
        StringBuilder sb = new StringBuilder();
        for( Instruction i : ilist ) {
            sb.append( String.format( "0x%08X\n", i.getEncoded() ) );
        }
        if( data.size() > 0 ) {
            sb.append( "DATA SEGMENT\n" );
            long wordaddr = 0x1001000L;
            for( DataDecl d: data ) {
                sb.append( String.format( "0x%08X ", wordaddr ) );
                int bytes = 0;
                long dataword = 0;
                for( Byte b: d ) {
                    if( bytes == 4 ) {
                        sb.append( String.format( "0x%08X\n", dataword ) );
                        wordaddr += 4;
                        sb.append( String.format( "0x%08X ", wordaddr ) );
                        dataword = 0;
                        bytes = 0;
                    }
                    long bval = (long)b.byteValue() & DataDecl.BYTEMASK;
                    bval = bval << 8*bytes;
                    dataword |= bval;
                    bytes++;
                }
                sb.append( String.format( "0x%08X\n", dataword ) );
                wordaddr += 4;
                dataword = 0;
                bytes = 0;
            }
        }

        return sb.toString();

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
        sb.append( "\t)\n\tData(\n" );
        for( DataDecl d : data ) {
            sb.append( "\t\t" );
            sb.append( d.toString() );
            sb.append( "\n" );
        }
        sb.append( "\t)\n)" );
        return sb.toString();
    }

}

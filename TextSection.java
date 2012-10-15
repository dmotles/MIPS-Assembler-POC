import java.util.*;
public class TextSection {
    ArrayList<Instruction> ilist;
    Hashtable<String,Label> symtable;
    public TextSection() {
        ilist = new ArrayList<Instruction>();
        symtable = new Hashtable<String,Label>();
    }

    public void appendTextSection( TextSection ts ) throws Exception {
        ArrayList<Instruction> list = ts.getInstructionList();
        Hashtable<String,Label> syms = ts.getSymTable();
        if( list.size() == 0 ) {
            // no instructions, only labels, add labels
            for( Label l: syms.values() ) {
                addLabel( l );
            }
        } else {
            for( Instruction i: list ) {
                // Add Labels that are at instruction address
                for( Label l: syms.values() ) {
                    if( l.address() == i.getAddr() ) {
                        addLabel( l );
                        syms.remove( l.name() );
                    } else if ( l.address() > i.getAddr() ) {
                        break;
                    }
                }
                addInstruction( i );
            }
            // Add labels that are after last instruction
            if( syms.size() > 0 ) {
                for( Label l: syms.values() ) {
                    addLabel( l );
                }
            }
        }
    }

    public void addInstructions( ArrayList<Instruction> list ) {
        for( Instruction i: list ) {
            i.setAddress( ilist.size() * 4 );
            ilist.add( i );
        }
    }

    public void addInstruction( Instruction i ) {
        i.setAddress( ilist.size() * 4 );
        ilist.add( i );
    }

    public void addLabel( Label l ) throws Exception {
        if( symtable.contains( l.name() ) ) {
            throw new Exception( l + " was declared more than once. You idiot." );
        }
        l.setAddress( ilist.size() * 4 );
        symtable.put( l.name(), l );
    }

    public Hashtable<String,Label> getSymTable() {
        return symtable;
    }

    public ArrayList<Instruction> getInstructionList() {
        return ilist;
    }
}

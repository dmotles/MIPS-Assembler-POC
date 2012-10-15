import java.util.*;
public class DataSection {
    ArrayList<DataDecl> dataItems;
    Hashtable<String,Label> symtable;
    long next_free;
    public DataSection() {
        dataItems = new ArrayList<DataDecl>();
        symtable = new Hashtable<String,Label>();
        next_free = 0x10010000;
    }

    public void addData( DataDecl d ) throws Exception {
        if( symtable.contains( d.label() ) ) { 
            throw new Exception( d.label() + " was declared more than once (in data section)." );
        }

        d.setStartAddress( next_free );
        next_free += d.length();
        int danglingbytes = (int)(next_free % 4L);
        if( danglingbytes > 0 ) {
            next_free += ( 4 - danglingbytes );
        }
        dataItems.add( d );
        symtable.put( d.label(), new Label( d.line(), d.column(), d.label(), d.address() ) );
    }

    public Hashtable<String,Label> getSymTable() {
        return symtable;
    }

    public ArrayList<DataDecl> getDataList() {
        return dataItems;
    }
}

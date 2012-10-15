public class Label {
    private int line;
    private int col;
    private String name;
    private long address;

    public Label( int l, int c, String n, long a ) {
        line = l;
        col = c;
        name = n;
        address = a;
    }

    public int line() {
        return line;
    }

    public int col() {
        return col;
    }

    public String name() {
        return name;
    }

    public long address() {
        return address;
    }

    public void setAddress( long a ) {
        address = a;
    }

    public String toString() {
        return String.format( "%s(line: %d,col: %d,addr: 0x%08X)",
                              name,
                              line,
                              col,
                              address
                            );
    }
}

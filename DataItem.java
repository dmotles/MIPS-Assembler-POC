public abstract class DataItem {
    protected String label;
    protected long start_addr;
    protected int length;
    protected long [] data;

    public DataItem( String l, long start, int len, long [] d ) {
        label = l;
        start_addr = start;
        length = len;
        data = d;
    }

    public int length() {
        return length;
    }

    public int getByte( int i ) {
        int retval = 0;
        if( i < length ) {
            int index = i / 4;
            int offset = i % 4;
            long word = data[ index ];
            long bytemask = 0x0FF << ( 2*offset );
            retval = (int)( (bytemask & word) >>> (2*offset) );
        }
        return retval;
    }

    public long getWord( int i ) {
        long ret = 0;
        if( i < data.length ) {
            ret = data[i];
        }
        return ret;
    }
}

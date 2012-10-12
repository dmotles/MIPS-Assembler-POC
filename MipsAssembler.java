public class MipsAssembler {
    public static void main( String [] args ) {
        Instruction i = new RInstruction( 0 , 0, "ADD", 1, 2, 3 );
        Instruction shift = new RInstruction( 0,0,"SLL", 0, 2, 1, 8 );
        System.out.println( i );
        System.out.println( shift );
    }
}

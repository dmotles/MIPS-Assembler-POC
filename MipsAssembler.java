public class MipsAssembler {
    public static void main( String [] args ) {
        Instruction i = Instruction.ADDI;
        System.out.println( i + " op: " + Byte.toString( i.opcode ) );
    }
}

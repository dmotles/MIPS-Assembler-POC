import java_cup.runtime.Symbol;
import java.io.*;

public class MipsAssembler {
    public static final int PARAMETER_ERROR = 10;
    public static void main( String [] args ) {
        int exitcode = 0;
        if( args.length > 0 ) {
            Symbol resultSymbol = null;
            String filename = args[0];
            try {
                MipsLexer ml = new MipsLexer( new FileInputStream( filename ) );
                MipsParser mp = new MipsParser( ml );
                resultSymbol = mp.parse();

                MipsAbstractSyntax mas = (MipsAbstractSyntax)resultSymbol.value;
                System.out.println( mas );

            } catch (IOException e) {
                System.err.println("ERROR: " + e.getMessage() );
                exitcode = PARAMETER_ERROR;
            } catch (Exception e) {
                e.printStackTrace( System.err );
                exitcode = 255;
            }

        } else {
            System.err.println( "Usage: java MipsAssembler [input file]" );
            exitcode = 1;
        }

        if( exitcode != 0 ) {
            System.exit( exitcode );
        }
    }
}

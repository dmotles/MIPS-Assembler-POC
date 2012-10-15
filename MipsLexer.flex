import java_cup.runtime.Symbol;

%%
%class MipsLexer
%cup
%line
%column
%ignorecase
%eofclose

%{
    private static final boolean DEBUG = false;
    private void error(String message) {
        System.err.println( "Error at line " + (yyline+1) +
                            ", col " + (yycolumn+1) +
                            " : " + message );
    }

    private void debugSymbols( int t, int l, int c, Object o ) {
        String str;
        switch ( t ) {
   case 13: str = "IMEMOPER"; break;
   case 16: str = "ISINGREGOP"; break;
   case 8: str = "RPAREN"; break;
   case 6: str = "STARTTEXT"; break;
   case 17: str = "REG"; break;
   case 10: str = "ROPER"; break;
   case 7: str = "LPAREN"; break;
   case 4: str = "COLON"; break;
   case 2: str = "EOL"; break;
   case 3: str = "COMMA"; break;
   case 14: str = "ILABELOPER"; break;
   case 0: str = "EOF"; break;
   case 15: str = "IBRANCHOP"; break;
   case 1: str = "error"; break;
   case 5: str = "STARTDATA"; break;
   case 12: str = "IOPER"; break;
   case 11: str = "RSOPER"; break;
   case 18: str = "IMM"; break;
   case 9: str = "LABEL"; break;
         default: str = Integer.toString( t ); break;
         }

        System.err.print( "Returning symbol:" + str );
        System.err.print( " line: " + (l+1) );
        System.err.print( " col: " + (c+1) );
        if( o != null ) {
            System.err.print( " o: " + o.toString() );
        }
        System.err.println();
    }

    private Symbol symbol(int type) {
        debugSymbols( type, yyline, yycolumn, null );
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        debugSymbols( type, yyline, yycolumn, value );
        return new Symbol(type, yyline, yycolumn, value);
    }

    private Integer convertToValid32I( String str, int base ) {
        long num = Long.parseLong( str, base );
        Integer ret = null;

        if( num > Integer.MAX_VALUE || num > 4294967295L ) {
            error( str + " cannot be represented by the word size (32-bits)." +
                   "Maximum value allowed is " + 4294967295L );
        } else if( num < Integer.MIN_VALUE ) {
            error( str + " cannot be represented by the word size (32-bits)." +
                   "Minimum value allowed is " + Integer.MIN_VALUE );
        } else {
            ret = new Integer( (int)num );
        }

        return ret;
    }

    private Integer convertToValidReg( String str ) {
        int r = Integer.parseInt( str );
        Integer ret = null;
        if( r > 31 ) {
            error( str + " is not a valid register. Too large." );
        } else {
            ret = new Integer( r );
        }
        return ret;
    }
%} 
int         = 0 | -?[1-9][0-9]*
hex         = "0x" [0-9A-Fa-f]{1,8}
new_line    = \r|\n|\r\n|\z
space       = [ \t\f]
whitespace  = {new_line} | {space}
comment     = "#" ~{new_line}
ident       = [a-zA-Z_] [a-zA-Z_0-9]*

EOL         = {comment} | {new_line} ( {whitespace} | {comment} )*
LABEL       = {ident}
IMM         = {int}
HEX         = "0x" [0-9A-Fa-f]{1,8}
REG         = "$r" [0-9]+
STARTTEXT   = ".text"
STARTDATA   = ".data"
ROPER       = add | sub | slt | and | nor
RSOPER      = sll
IOPER       = addi
IMEMOPER    = lw | sw
IBRANCHOP   = beq | bne
ILABELOPER  = ori
ISINGREGOP  = lui
JOPER       = j

%%

<YYINITIAL> {
{IMM}                       {
                                Integer val = convertToValid32I( yytext(), 10 );
                                if( val != null ) return symbol( sym.IMM, val );
                                else throw new Error( "Lexer failed due to bad integer value." );
                            }

{HEX}                       {
                                Integer val = convertToValid32I( yytext().substring(2), 16 );
                                if( val != null ) return symbol( sym.IMM, val );
                                else throw new Error( "Lexer failed due to bad integer value." );
                            }

{ROPER}                     { return symbol( sym.ROPER, yytext() ); }
{RSOPER}                    { return symbol( sym.RSOPER, yytext() ); }
{IOPER}                     { return symbol( sym.IOPER, yytext() ); }
{IMEMOPER}                  { return symbol( sym.IMEMOPER, yytext() ); }
{ILABELOPER}                { return symbol( sym.ILABELOPER, yytext() ); }
{ISINGREGOP}                { return symbol( sym.ISINGREGOP, yytext() ); }
{IBRANCHOP}                 { return symbol( sym.IBRANCHOP, yytext() ); }
{JOPER}                     { return symbol( sym.JOPER, yytext() ); }

{REG}                       {
                                Integer r = convertToValidReg( yytext().substring(2) ); 
                                if( r != null ) return symbol( sym.REG, r );
                                else throw new Error( "Lexer failed due to invalid register specification." );
                            }
{EOL}                       { if (DEBUG) System.err.println( "EOL match: < " + yytext() + " >" ); return symbol( sym.EOL ); }
","                         { return symbol( sym.COMMA ); }
":"                         { return symbol( sym.COLON ); }
"("                         { return symbol( sym.LPAREN ); }
")"                         { return symbol( sym.RPAREN ); }
{STARTTEXT}                 { return symbol( sym.STARTTEXT ); }
{STARTDATA}                 { return symbol( sym.STARTDATA ); }
{LABEL}                     { return symbol( sym.LABEL, yytext() ); }

{space}                     { /* IGNORE */ if( DEBUG) System.err.println("Ignoreing Space! <" +yytext()+ ">"); }
//{comment}                   { /* IGNORE */ System.err.println("Ignoreing Comment! <" +yytext()+ ">"); }

}


/* error fallback */
.|\n                         { 
                                error( "Unknown token! Did you accidentally a letter?" );
                                throw new Error("Illegal character <"+yytext()+">"); 
                             }

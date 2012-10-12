import java_cup.runtime.Symbol;

%%
%class MipsLexer
%cup
%implements sym, Constants
%line
%column
%ignorecase
%eofclose

%{
    private void error(String message) {
        System.err.println( "Error at line " + (yyline+1) +
                            ", col " + (yycolumn+1) +
                            " : " + message );
    }
%} 
int = 0 | -?[1-9][0-9]*
new_line = \r|\n|\r\n|\z
white_space = {new_line} | [ \t\f]
comment = # ~{new_line}

%%


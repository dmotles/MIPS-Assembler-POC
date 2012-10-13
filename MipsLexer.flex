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
hex = 0x [0-9a-fA-F]{1,8}
immediate = {int}
new_line = \r|\n|\r\n|\z
white_space = {new_line} | [ \t\f]
comment = # ~{new_line}
linecomment = {whitespace}+ {comment}
rinst       = add | sub | slt | and | nor
reg         = $ ( [0-9] | [1-3][0-9] )
%%

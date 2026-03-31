grammar Expr;

@header {
package exparser;
}

// Règle d'entrée
root : expr EOF ;

expr
    : '(' expr ')'                       # Parens
    | expr op=('*'|'/') expr             # MulDiv
    | expr op=('+'|'-') expr             # AddSub
    | INT                                # Int
    ;

INT : [0-9]+ ;
WS  : [ \t\r\n]+ -> skip ;
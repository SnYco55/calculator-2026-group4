grammar Expr;

@header {
package exparser;
}

prog: expr EOF ;

expr: expr '*' expr   # Time
    | expr '+' expr   # Add
    | expr '-' expr   # Minus
    | expr '/' expr   # Div
    | INT             # Int
    | '(' expr ')'    # Parens
    ;

INT: [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;
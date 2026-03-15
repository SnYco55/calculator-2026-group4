grammar Expr;

@header {
package exparser;
}

expr:
      expr '**' expr  # Pow
    | expr '*' expr   # Time
    | expr '/' expr   # Div
    | expr '+' expr   # Add
    | expr '-' expr   # Minus
    | INT             # Int
    | '(' expr ')'    # Parens
    ;

INT: [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;
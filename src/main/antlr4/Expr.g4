grammar Expr;

@header {
package exparser;
}

// Règle d'entrée
root : expr EOF ;

expr
    : '(' expr ')'                       # Parens
    | complex                            # Comps
    | rational                           # Rats
    | expr op=('*'|'/') expr             # MulDiv
    | expr op=('+'|'-') expr             # AddSub
    | INT                                # Int
    | FLOAT                              # Float
    ;

rational
    : INT '/' INT                       # Rat
    ;

complex
    : (real=(INT|FLOAT) op=('+'|'-'))? imag=(INT|FLOAT) 'i'  # Comp
    ;

INT : [0-9]+ ;
FLOAT : [0-9]+ '.' [0-9]*;
WS  : [ \t\r\n]+ -> skip ;
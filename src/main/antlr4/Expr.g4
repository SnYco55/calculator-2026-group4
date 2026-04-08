grammar Expr;

@header {
package exparser;
}

// Règle d'entrée
root : expr EOF ;

expr
    : '(' expr ')'                       # Parens
    | op='-' expr                        # UnaryMinus
    | func=('sin'|'cos'|'tan'|'log'|'sqrt') '(' expr ')' # Funcs
    | complex                            # Comps
    | <assoc=right> expr '^' expr        # Power
    | expr 'E' expr           # Scientific
    | expr op=('*'|'/') expr             # MulDiv
    | expr op=('+'|'-') expr             # AddSub
    | INT                                # Int
    | FLOAT                              # Float
    | 'π'                                  # Pi
    ;

complex
    : (real=(INT|FLOAT) op=('+'|'-'))? imag=(INT|FLOAT) 'i'  # Comp
    ;

INT : [0-9]+ ;
FLOAT : [0-9]+ '.' [0-9]*;
WS  : [ \t\r\n]+ -> skip ;
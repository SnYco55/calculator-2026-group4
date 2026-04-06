grammar Expr;

@header {
package exparser;
}

// Règle d'entrée
root : expr EOF ;

expr
    : '(' expr ')'                       # Parens
    | func=('sin'|'cos'|'tan'|'log') '(' expr ')' # Trig
    | complex                            # Comps
    | <assoc=right> expr '^' expr        # Power
    | FLOAT 'E' minus='-'? INT           # Scientific
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
grammar Expr;

@header {
package visitor.parser;
}

// Règle principale
prog: expr EOF ;

// Règle d'expression avec des labels (#) pour le Visitor
expr: expr '*' expr   # Mul
    | expr '+' expr   # Add
    | INT             # Int
    | '(' expr ')'    # Parens
    ;

INT: [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;
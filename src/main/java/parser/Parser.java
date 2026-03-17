package parser;

import calculator.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import exparser.ExprParser;
import java.util.Arrays;

public class Parser extends exparser.ExprBaseVisitor<Expression> implements ExpressionParser {
    @Override
    public Expression parse(String expression) {
        CharStream input = CharStreams.fromString(expression);

        exparser.ExprLexer lexer = new exparser.ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        exparser.ExprParser parser = new exparser.ExprParser(tokens);

        ParseTree tree = parser.expr();

        return this.visit(tree);
    }

    @Override
    public Expression visitAdd(ExprParser.AddContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        try{
            return new Plus(Arrays.asList(left, right));
        }catch (IllegalConstruction _){
            throw new IllegalConstruction();
        }
    }

    @Override
    public Expression visitTime(ExprParser.TimeContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        try{
            return new Times(Arrays.asList(left, right));
        }catch (IllegalConstruction _){
            throw new IllegalConstruction();
        }

    }

    @Override
    public Expression visitMinus(ExprParser.MinusContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        try{
            return new Minus(Arrays.asList(left, right));
        }catch (IllegalConstruction _){
            throw new IllegalConstruction();
        }

    }

    @Override
    public Expression visitDiv(ExprParser.DivContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        try{
            return new Divides(Arrays.asList(left, right));
        }catch (IllegalConstruction _){
            throw new IllegalConstruction();
        }

    }

    @Override
    public Expression visitParens(ExprParser.ParensContext ctx){
        return visit(ctx.expr());
    }

    @Override
    public Expression visitInt(ExprParser.IntContext ctx){
        String text = ctx.INT().getText();

        return new MyNumber(Integer.parseInt(text));
    }
}

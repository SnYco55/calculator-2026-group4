package parser;

import calculator.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import exparser.ExprParser;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
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
    public Expression visitAddSub(ExprParser.AddSubContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        try{
            if (ctx.op.getText().equals("+"))
            {
                return new Plus(Arrays.asList(left, right));
            }
            else{
                return new Minus(Arrays.asList(left, right));
            }
        }catch (IllegalConstruction _){
            throw new IllegalConstruction();
        }
    }

    @Override
    public Expression visitMulDiv(ExprParser.MulDivContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        try{
            if (ctx.op.getText().equals("*"))
            {
                return new Times(Arrays.asList(left, right));
            }
            else{
                return new Divides(Arrays.asList(left, right));
            }
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

    @Override
    public Expression visitFloat(ExprParser.FloatContext ctx){
        String text = ctx.FLOAT().getText();

        return new MyReal(new BigDecimal(text));
    }

    @Override
    public Expression visitComp(ExprParser.CompContext ctx){
        BigDecimal imaginary = new BigDecimal(ctx.imag.getText());

        BigDecimal real = new BigDecimal("0");

        if (ctx.real != null){
            real = new BigDecimal(ctx.real.getText());

            if (ctx.op.getText().equals("-")){
                imaginary = imaginary.multiply(new BigDecimal("-1"));
            }
        }


        return new MyComplex(real, imaginary);
    }

    @Override
    public Expression visitScientific(ExprParser.ScientificContext ctx) {
        return new MyReal(new BigDecimal(ctx.getText().trim()));
    }

    @Override
    public Expression visitPower(ExprParser.PowerContext ctx){
        Expression base = visit(ctx.expr(0));
        Expression exp = visit(ctx.expr(1));

        return new Pow(Arrays.asList(base, exp));
    }

    @Override
    public Expression visitTrig(ExprParser.TrigContext ctx){
        Expression arg = visit(ctx.expr());
        double value = new Calculator().eval(arg).toComplex().getReal().doubleValue();

        String functionName = ctx.func.getText();

        if (AngleMode.getMode() == AngleMode.Mode.DEG){
            value = value * (Math.PI/180);
        }

        switch (functionName) {
            case "sin":
                return (Expression) Operation.format(new MyReal(new BigDecimal(String.valueOf(Math.sin(value)), Precision.getMathContext())).toComplex());
            case "cos":
                return (Expression) Operation.format(new MyReal(new BigDecimal(String.valueOf(Math.cos(value)), Precision.getMathContext())).toComplex());
            case "tan":
                return (Expression) Operation.format(new MyReal(new BigDecimal(String.valueOf(Math.tan(value)), Precision.getMathContext())).toComplex());
            case "log":
                return (Expression) Operation.format(new MyReal(new BigDecimal(String.valueOf(Math.log10(value)), Precision.getMathContext())).toComplex());
            default:
                throw new IllegalArgumentException("Invalid function call");
        }
    }

    @Override
    public Expression visitPi(ExprParser.PiContext ctx){
        return new MyReal(new BigDecimal(String.valueOf(Math.PI)));
    }

}

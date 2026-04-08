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
        BigDecimal value = new BigDecimal(text);

        int decimalPlaces = Precision.getDecimalPlaces();
        BigDecimal rounded = value.setScale(decimalPlaces, java.math.RoundingMode.HALF_EVEN);

        return new MyReal(rounded);
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
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        return new Scientific(Arrays.asList(left, right));
    }

    @Override
    public Expression visitPower(ExprParser.PowerContext ctx){
        Expression base = visit(ctx.expr(0));
        Expression exp = visit(ctx.expr(1));

        return new Pow(Arrays.asList(base, exp));
    }

    @Override
    public Expression visitFuncs(ExprParser.FuncsContext ctx){
        Expression arg = visit(ctx.expr());
        double value = new Calculator().eval(arg).toComplex().getReal().doubleValue();

        String functionName = ctx.func.getText();

        if (AngleMode.getMode() == AngleMode.Mode.DEG && !functionName.equals("sqrt")){
            value = value * (Math.PI/180);
        }

        double result;
        switch (functionName) {
            case "sin":
                result = Math.sin(value);
                break;
            case "cos":
                result = Math.cos(value);
                break;
            case "tan":
                if (value % Math.PI/2 == 0 && value != 0){
                    return new MyReal(BigDecimal.valueOf(0), MyReal.State.UNDEFINED);
                }
                result = Math.tan(value);
                break;
            case "log":
                if (value <= 0){
                    return new MyReal(BigDecimal.valueOf(0), MyReal.State.UNDEFINED);
                }
                result = Math.log10(value);
                break;
            case "sqrt":
                if (value < 0){
                    return new MyReal(BigDecimal.valueOf(0), MyReal.State.UNDEFINED);
                }
                result = Math.sqrt(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid function call");
        }

        int decimalPlaces = Precision.getDecimalPlaces();
        BigDecimal bd = BigDecimal.valueOf(result);
        BigDecimal rounded = bd.setScale(decimalPlaces, java.math.RoundingMode.HALF_EVEN);
        MyReal myReal = new MyReal(rounded);

        return (Expression) Operation.format(myReal.toComplex());
    }

    @Override
    public Expression visitPi(ExprParser.PiContext ctx){
        return new MyReal(new BigDecimal(String.valueOf(Math.PI)));
    }

    @Override
    public Expression visitUnaryMinus(ExprParser.UnaryMinusContext ctx){
        Expression v = visit(ctx.expr());
        return new Times(Arrays.asList(new MyNumber(-1), v));
    }
}

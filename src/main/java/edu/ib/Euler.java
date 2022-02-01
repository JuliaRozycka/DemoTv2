package edu.ib;


import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class Euler implements ODEStep{

    @Override
    public double step(double x, double t, double h, String equation) {
        Argument xPars = new Argument("x", x);
        Argument tPars = new Argument("t", t);
        Expression expression = new Expression(equation, xPars, tPars);
        return expression.calculate() * h + x;
    }
}

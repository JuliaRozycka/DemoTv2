package edu.ib;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class EulerModified implements ODEStep {
        @Override
        public double step(double x, double t, double h, String equation) {
            Argument xPars = new Argument("x", x);
            Argument tPars = new Argument("t", t);
            Expression expression = new Expression(equation, xPars, tPars);
            double xMid = expression.calculate() * h / 2 + x;
            double tMid = t + h / 2;
            Argument xPars2 = new Argument("x", xMid);
            Argument tPars2 = new Argument("t", tMid);
            Expression expression2 = new Expression(equation, xPars2, tPars2);
            return expression2.calculate() * h + x;
        }
}

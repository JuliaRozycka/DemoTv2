package edu.ib;

public class ODEIntegrator {
    private double tLeft;
    private double tRight;
    private double x0;
    private String equation;
    private ODEStep method;
    private StepHandler stepHandler;

    public ODEIntegrator(double tLeft, double tRight, double x0, String equation, ODEStep method, StepHandler stepHandler) {
        this.tLeft = tLeft;
        this.tRight = tRight;
        this.x0 = x0;
        this.equation = equation;
        this.method = method;
        this.stepHandler = stepHandler;
    }

    public void integrate(double h){
        double x = x0;
        double t;
        for (t = tLeft; t < tRight; t += h) {
            stepHandler.update(x, t);
            x = method.step(x, t, h, equation);
        }
        stepHandler.update(x, t);
    }
}

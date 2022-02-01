package edu.ib;

public interface ODEStep {
    double step(double x, double t, double h, String equation);
}

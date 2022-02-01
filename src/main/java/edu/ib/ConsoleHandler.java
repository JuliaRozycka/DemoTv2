package edu.ib;

import java.util.ArrayList;

public class ConsoleHandler implements StepHandler {
    protected ArrayList<Double> xList;
    protected ArrayList<Double> tList;

    public ConsoleHandler() {
        xList = new ArrayList<>();
        tList = new ArrayList<>();
    }

    public ArrayList<Double> getxList() {
        return xList;
    }

    public ArrayList<Double> gettList() {
        return tList;
    }

    @Override
    public void update(double x, double t) {
        xList.add(x);
        tList.add(t);
    }

    public void clearData() {
        xList.clear();
        tList.clear();
    }
}
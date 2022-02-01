package edu.ib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tabulator {
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;

    Tabulator(){
        xValues= new ArrayList<>();
        yValues= new ArrayList<>();
    }

    public ArrayList<Double> getxValues() {
        return xValues;
    }

    public void setxValues(ArrayList<Double> xValues) {
        this.xValues = xValues;
    }

    public ArrayList<Double> getyValues() {
        return yValues;
    }

    public void setyValues(ArrayList<Double> yValues) {
        this.yValues = yValues;
    }

    public void calculate(double a, double b, int n, ScalarFunction func){

        clearData();
        double x=a;
        double y=0;
        double dx=(b-a)/(n-1);
        for(int i=0; i<n; i++){
            y=func.f(x);
            xValues.add(x);
            yValues.add(y);
            x+=dx;
        }

    }
    void terminalDisplay(){
        for(int i=0; i< numberOfPoints(); i++){
            System.out.printf("%15.10f \t %15.10f \n",xValues.get(i),yValues.get(i));
        }
    }

    void saveToFile(String fileName
    ) throws IOException {

        StringBuilder sb= new StringBuilder();
        for(int i=0; i<numberOfPoints(); i++){
            double x=xValues.get(i);
            double y=yValues.get(i);
            String sx=String.format("%15.10f",x);
            String sy=String.format("%15.10f",y);
            String line= sx+ "\t" + sy + "\n";
            sb.append(line);
        }


        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.append(sb.toString());
        writer.close();

    }

    private void clearData(){
        xValues.clear();
        yValues.clear();
    }

    public int numberOfPoints(){
        return xValues.size();
    }

}

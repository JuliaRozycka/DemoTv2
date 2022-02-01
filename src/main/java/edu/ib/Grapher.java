package edu.ib;

import javafx.scene.chart.XYChart;
import java.util.ArrayList;

public class Grapher {
    private XYChart graph;

    public Grapher(XYChart graph) {
        this.graph = graph;
    }

    public XYChart getGraph() {
        return graph;
    }

    public void setGraph(XYChart graph) {
        this.graph = graph;
    }

    public void makePlot(ArrayList<Double> xList, ArrayList<Double> tList) {
        XYChart.Series series = new XYChart.Series<>();
        for (int i = 0; i < xList.size(); i++)
            plotPoint(xList.get(i), tList.get(i), series);
        graph.getData().add(series);
    }

    private void plotPoint(double x, double y, XYChart.Series series) {
        series.getData().add(new XYChart.Data(x, y));
    }

    public void clear() {
        graph.getData().clear();
    }
}
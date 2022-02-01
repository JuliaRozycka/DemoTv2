package edu.ib;
import com.sun.glass.ui.CommonDialogs;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class TVDemoController{

    private ConsoleHandler consoleHandler;
    private ObservableList<PointTX> list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<?, ?> graph;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextField tfMax;

    @FXML
    private TextField tfMin;

    @FXML
    private TextField tfH;

    @FXML
    private TextField tfX0;

    @FXML
    private TextField tfEquation;

    @FXML
    private TableColumn<PointTX, Double> x;

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnCalculateModified;

    @FXML
    private Button btnClear;

    @FXML
    private TableColumn<PointTX, Double> time;

    @FXML
    private TableView<PointTX> table;

    @FXML
    VBox vbMenu;

    FileChooser fileChooser = new FileChooser();

    @FXML
    void btnCalculateClicked(ActionEvent event) throws IOException {
        try {
            table.getItems().clear();
            consoleHandler.clearData();
            double a = Double.parseDouble(tfMin.getText());
            double b = Double.parseDouble(tfMax.getText());
            double h = Double.parseDouble(tfH.getText());
            double x0 = Double.parseDouble(tfX0.getText());
            String equation = tfEquation.getText();

            ODEIntegrator integrator = new ODEIntegrator(a,b,x0, equation, new Euler(), consoleHandler);
            integrator.integrate(h);
            list.addAll(PointTX.getPointsTX(consoleHandler.gettList(), consoleHandler.getxList()));

            grapher.makePlot(consoleHandler.gettList(), consoleHandler.getxList());
        } catch (Exception e) {
            Parent error = FXMLLoader.load(getClass().getResource("/fxml/errorData.fxml"));
            Scene sceneError = new Scene(error,250,200);
            Stage errorWindow = new Stage();
            errorWindow.initModality(Modality.WINDOW_MODAL);
            errorWindow.initOwner(((Node)event.getSource()).getScene().getWindow());
            errorWindow.setScene(sceneError);
            errorWindow.setTitle("Error");
            errorWindow.show();
        }

    }

    public void btnCalculateModifiedClicked(ActionEvent actionEvent) throws IOException {
        try{
            table.getItems().clear();
            consoleHandler.clearData();
            double a = Double.parseDouble(tfMin.getText());
            double b = Double.parseDouble(tfMax.getText());
            double h = Double.parseDouble(tfH.getText());
            double x0 = Double.parseDouble(tfX0.getText());
            String equation = tfEquation.getText();

            ODEIntegrator integrator = new ODEIntegrator(a,b,x0, equation, new EulerModified(), consoleHandler);
            integrator.integrate(h);
            list.addAll(PointTX.getPointsTX(consoleHandler.gettList(), consoleHandler.getxList()));

            grapher.makePlot(consoleHandler.gettList(), consoleHandler.getxList());
        } catch (Exception e) {
            Parent error = FXMLLoader.load(getClass().getResource("/fxml/errorData.fxml"));
            Scene sceneError = new Scene(error,250,200);
            Stage errorWindow = new Stage();
            errorWindow.initModality(Modality.WINDOW_MODAL);
            errorWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            errorWindow.setScene(sceneError);
            errorWindow.setTitle("Error");
            errorWindow.show();
        }
    }

    public void btnClearClicked(ActionEvent actionEvent) {
        table.getItems().clear();
        grapher.clear();
    }

    @FXML
    private void handleSaveClicked(ActionEvent event){
        Window stage = vbMenu.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("untitled");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());

            if (file != null) {
                saveTextToFile(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveTextToFile(File file ) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            for (int i = 0; i < consoleHandler.xList.size(); i++) {
                writer.println(consoleHandler.tList.get(i)+"\t"+consoleHandler.xList.get(i));
            }
            writer.close();
        } catch (Exception ex) {
            Logger.getLogger(TVDemoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Grapher grapher;

    @FXML
    void initialize() {
        assert tfMax != null : "fx:id=\"tfMax\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert tfMin != null : "fx:id=\"tfMin\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert tfH != null : "fx:id=\"tfH\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert tfX0 != null : "fx:id=\"tfX0\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert tfEquation != null : "fx:id=\"tfEquation\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'graph.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'graph.fxml'.";
        assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert btnCalculate != null : "fx:id=\"btnCalculate\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert btnCalculateModified != null : "fx:id=\"btnCalculateModified\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tvdemo.fxml'.";

        consoleHandler = new ConsoleHandler();

        time.setCellValueFactory(new PropertyValueFactory<PointTX, Double>("time"));
        x.setCellValueFactory(new PropertyValueFactory<PointTX, Double>("x"));
        table.setItems(list);

        grapher = new Grapher(graph);

        fileChooser.setInitialDirectory(new File("C:\\Users\\User\\Desktop\\SEMESTR 5\\Metody numeryczne"));
    }

    @FXML
    private void handleAboutClicked(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/about.fxml"));
            Parent root = (Parent)loader.load();
            //Parent root = FXMLLoader.load(getClass().getResource("/fxml/about.fxml"));
            Scene scene= new Scene(root,400,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("About application");
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void handleExitClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}

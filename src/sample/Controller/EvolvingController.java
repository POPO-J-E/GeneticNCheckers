package sample.Controller;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.dames.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import sample.Utils.Resolver;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 *
 * Created by jeremy on 28/03/2017.
 */
public class EvolvingController<R extends Resolver<R>> implements Initializable, Observer {
    @FXML
    private AreaChart<Number,Number> chart_evolution;

    private R resolver;

    public EvolvingController(R resolver) {
        this.resolver = resolver;
        resolver.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("azertyuiop");
        final NumberAxis xAxis = new NumberAxis(1, 31, 1);
        final NumberAxis yAxis = new NumberAxis();
        chart_evolution = new AreaChart<Number,Number>(xAxis,yAxis);
        chart_evolution.setTitle("Temperature Monitoring (in Degrees C)");

        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("April");
        seriesApril.getData().add(new XYChart.Data(1, 4));
        seriesApril.getData().add(new XYChart.Data(3, 10));
        seriesApril.getData().add(new XYChart.Data(6, 15));
        seriesApril.getData().add(new XYChart.Data(9, 8));
        seriesApril.getData().add(new XYChart.Data(12, 5));
        seriesApril.getData().add(new XYChart.Data(15, 18));
        seriesApril.getData().add(new XYChart.Data(18, 15));
        seriesApril.getData().add(new XYChart.Data(21, 13));
        seriesApril.getData().add(new XYChart.Data(24, 19));
        seriesApril.getData().add(new XYChart.Data(27, 21));
        seriesApril.getData().add(new XYChart.Data(30, 21));

        XYChart.Series seriesMay = new XYChart.Series();
        seriesMay.setName("May");
        seriesMay.getData().add(new XYChart.Data(1, 20));
        seriesMay.getData().add(new XYChart.Data(3, 15));
        seriesMay.getData().add(new XYChart.Data(6, 13));
        seriesMay.getData().add(new XYChart.Data(9, 12));
        seriesMay.getData().add(new XYChart.Data(12, 14));
        seriesMay.getData().add(new XYChart.Data(15, 18));
        seriesMay.getData().add(new XYChart.Data(18, 25));
        seriesMay.getData().add(new XYChart.Data(21, 25));
        seriesMay.getData().add(new XYChart.Data(24, 23));
        seriesMay.getData().add(new XYChart.Data(27, 26));
        seriesMay.getData().add(new XYChart.Data(31, 26));

        chart_evolution.getData().addAll(seriesApril, seriesMay);

    }

    void open()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/evolving.fxml"));
            fxmlLoader.setController(this);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startResolve();
    }

    private void startResolve() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                resolver.start();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof Board)
            this.updateInfos((Board)o);
    }

    private void updateInfos(Board board) {
        System.out.println("sout "+Utils.getFistness(board));
    }
}

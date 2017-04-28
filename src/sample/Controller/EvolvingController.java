package sample.Controller;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.dames.Utils;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Utils.EvolvingInputBuilder;
import sample.Utils.Resolver;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 *
 * Created by jeremy on 28/03/2017.
 */
public abstract class EvolvingController<R extends Resolver<R>> implements Initializable, Observer {

    @FXML
    private AreaChart<Number,Number> chart_evolution;
    @FXML
    private NumberAxis xAxis ;
    @FXML
    private NumberAxis yAxis ;
    private int yvalue = 0;

    @FXML
    private AnchorPane anchor_inputs;

    private XYChart.Series<Number, Number> seriesFitness;


    private R resolver;
    private EvolvingInputBuilder<R> builder;

    private final int COORD_SIZE = 500;
    private int coordSize;
    private int coordI;
    private Coordinate[] coordinates;

    public EvolvingController() {
        this.builder = new EvolvingInputBuilder<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart_evolution.setTitle("Fitness evolution");

        xAxis.setAutoRanging(true);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(24);
        xAxis.setTickUnit(3);

        yAxis.setAutoRanging(true);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);
        yAxis.setTickUnit(10);

        seriesFitness= new XYChart.Series<>();
        seriesFitness.setName("Fitness");

        chart_evolution.getData().add(seriesFitness);

        buildSettings(builder);
        anchor_inputs.getChildren().add(builder.build(this));
    }

    public abstract void buildSettings(EvolvingInputBuilder<R> builder);


    void open(R resolver)
    {
        this.resolver = resolver;
        resolver.addObserver(this);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/evolving.fxml"));
            fxmlLoader.setController(this);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setOnCloseRequest(this::exitApplication);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startResolve();
    }

    private void startResolve() {
        this.initCoord(COORD_SIZE);
        Thread t = new Thread(() -> resolver.start());
        t.setDaemon(true);
        t.start();
    }

    @Override
    public synchronized void update(Observable observable, Object o) {
        if(o instanceof Board)
        {
            Platform.runLater(() -> {
                Board board = (Board)o;
                addFitness(board);
                //updateInfos(board);
            });
        }
        else if(o instanceof String)
        {
            String action = (String)o;
            if(action.equals("end"))
            {
                Platform.runLater(this::endResolve);
            }
        }

        this.builder.updateInputs(resolver);
    }

    private synchronized void endResolve() {
        long t0 = System.nanoTime();
        GeometryFactory gf = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[coordI];
        for (int i = 0; i < coordI; i++) {
            coordinates[i] = this.coordinates[i];
        }
        Geometry geom = new LineString(new CoordinateArraySequence(coordinates), gf);
        Geometry simplified = DouglasPeuckerSimplifier.simplify(geom, 1);
        List<XYChart.Data<Number, Number>> update = new ArrayList<>();
        for (Coordinate each : simplified.getCoordinates()) {
            update.add(new XYChart.Data<>(each.x, each.y));
        }
        long t1 = System.nanoTime();
        System.out.println(String.format("Reduces points from %d to %d in %.1f ms", coordinates.length, update.size(), (t1 - t0) / 1e6));
        ObservableList<XYChart.Data<Number, Number>> list = FXCollections.observableArrayList(update);
        this.seriesFitness.getData().addAll(list);

        this.initCoord(COORD_SIZE);
    }

    //protected abstract void updateInfos(Board board);

    public R getResolver() {
        return resolver;
    }

    protected synchronized void addFitness(Board board)
    {
        coordinates[coordI++] = new Coordinate(yvalue++, Utils.getFistness(board));

        if(coordI >= coordSize)
        {
            long t0 = System.nanoTime();
            GeometryFactory gf = new GeometryFactory();
            Geometry geom = new LineString(new CoordinateArraySequence(coordinates), gf);
            Geometry simplified = DouglasPeuckerSimplifier.simplify(geom, 1);
            List<XYChart.Data<Number, Number>> update = new ArrayList<>();
            for (Coordinate each : simplified.getCoordinates()) {
                update.add(new XYChart.Data<>(each.x, each.y));
            }
            long t1 = System.nanoTime();
            System.out.println(String.format("Reduces points from %d to %d in %.1f ms", coordinates.length, update.size(),
                    (t1 - t0) / 1e6));
            ObservableList<XYChart.Data<Number, Number>> list = FXCollections.observableArrayList(update);
            this.seriesFitness.getData().addAll(list);

            this.initCoord(COORD_SIZE);
        }
    }

    private void initCoord(int size)
    {
        this.coordSize = size;
        this.coordI = 0;
        this.coordinates = new Coordinate[coordSize];
    }

    public void exitApplication(WindowEvent event) {
        resolver.stop();
    }
}

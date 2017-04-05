package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable{
    @FXML
    private ListView<String> listView;
    @FXML
    private Label lb_algoDescription;
    @FXML
    private Button btn_next;

    private ALGORITHM selectedAlgorithm;
    private enum ALGORITHM {
        SIMULATED_ANNEALING,
        TABOO,
        GENETIC
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initListView();
       btn_next.setOnAction(e-> openSettings());
    }

    private void openSettings() {
        SettingsController settingsController = null;
        switch (selectedAlgorithm)
        {
            case SIMULATED_ANNEALING :
                settingsController = new SimulatedAnnealingSettingsController();
                break;
            case TABOO :
                settingsController = new TabooSettingsController();
                break;
            case GENETIC :
                settingsController = new GeneticSettingsController();
                break;
            default :
                throw new NoClassDefFoundError();
        }
        settingsController.openSettings();
    }

    private void initListView()
    {
        int ROW_HEIGHT = 50;

        String description_simulatedAnnealingAlgorithm = "Simulated annealing algorithm : \n eq;eyrdbsuqseyr xjexfdbqwyze xqwzfqytwfjxytxdfx gzf <eyd fwyjsetdwhg fesdyqtfesuXTQ DF?WHGF DN";
        HBox hBox_simulatedAnnealingAlgorithm = setItem(
                "Simulated annealing algorithm",
                description_simulatedAnnealingAlgorithm,
                "/sample/Resources/img_thermometer.png",
                ALGORITHM.SIMULATED_ANNEALING
        );

        String description_tabooAlgorithm = "Taboo algorithm : \n eq;eyrdbsuqseyr xjexfdbqwyze xqwzfqytwfjxytxdfx gzf <eyd fwyjsetdwhg fesdyqtfesuXTQ DF?WHGF DN";
        HBox hBox_tabooAlgorithm = setItem(
                "Taboo algorithm",
                description_tabooAlgorithm,
                "/sample/Resources/img_list.png",
                ALGORITHM.TABOO
        );

        String description_geneticAlgorithm = "Genetic algorithm : \n eq;eyrdbsuqseyr xjexfdbqwyze xqwzfqytwfjxytxdfx gzf <eyd fwyjsetdwhg fesdyqtfesuXTQ DF?WHGF DN";
        HBox hBox_geneticAlgorithm = setItem(
                "Genetic algorithm",
                description_geneticAlgorithm,
                "/sample/Resources/img_dna.png",
                ALGORITHM.GENETIC
        );

        ObservableList items = FXCollections.observableArrayList(hBox_simulatedAnnealingAlgorithm,hBox_tabooAlgorithm,hBox_geneticAlgorithm);

//        listView.setOnMouseClicked(e -> System.out.println(selectedAlgorithm));
        listView.setFixedCellSize(ROW_HEIGHT);
        listView.setItems(items);

    }

    private HBox setItem(String title, String description, String url, ALGORITHM algorithm)
    {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(url));
        imageView.setFitWidth(32);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        Label lb = new Label(title);
        lb.setPadding(new Insets(0,0,0,20));
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(lb);

        hBox.setOnMouseClicked(e -> {
            selectedAlgorithm = algorithm;
            lb_algoDescription.setText("" + description);
            btn_next.setDisable(false);
        });

        return hBox;
    }


}

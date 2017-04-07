package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Utils.Resolver;
import sample.Utils.ResolverFactory;
import sample.Utils.SettingsBuilder;
import sample.setttings.SettingsInput;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * Created by jeremy on 21/03/2017.
 */
public abstract class SettingsController<R extends Resolver<R>> implements Initializable{
    @FXML
    public ImageView img_algorithm;
    @FXML
    public Label lb_titleAlgorithm;
    @FXML
    public Button btn_start;
    @FXML
    public Label lb_descriptionAlgorithm;
    @FXML
    public Label lb_InputLabel;
    @FXML
    public AnchorPane anchor_settings;

    protected R resolver;
    private SettingsBuilder builder;
    private String titleWindow;
    private String imgUrl;

    public SettingsController(String titleWindow, String imgUrl, ResolverFactory<R> factory) {
        this.titleWindow = titleWindow;
        this.resolver = factory.generateResolver();
        this.builder = new SettingsBuilder();
        this.imgUrl = imgUrl;
    }

    void openSettings()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/settings.fxml"));
            fxmlLoader.setController(this);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(titleWindow);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @FXML
    public final void initialize(URL location, ResourceBundle resources) {
        img_algorithm.setImage(new Image(imgUrl));
        lb_titleAlgorithm.setText(titleWindow);
        buildSettings(builder);
        anchor_settings.getChildren().add(builder.build(this));
//        btn_start.setOnMouseClicked(e->startResolve());

        btn_start.setOnMouseClicked(e-> startResolve());
    }

    protected void setTitleAndDescription(String title, String description)
    {
        lb_descriptionAlgorithm.setText(description);
        lb_InputLabel.setText(title);
    }

    public void startResolve(){
        EvolvingController evolvingController = new EvolvingController(resolver);
        evolvingController.open();
//        resolver.start();
    }

    public abstract void buildSettings(SettingsBuilder builder);

    public void onDisplayDescription(SettingsInput<R, ?, ?> input) {
        this.setTitleAndDescription(input.getLabel(), input.getDescription());
    }

    public R getResolver() {
        return resolver;
    }
}

package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * Created by jeremy on 07/04/2017.
 */
public class BoardController implements Initializable {

    @FXML
    ImageView imageView;
    @FXML
    ImageView imageView_2;
    @FXML
    VBox menuView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image("/sample/Resources/img_board.png"));
        imageView_2.setImage(new Image("/sample/Resources/img_board.png"));
    }

    private void initBoard(int size){

        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setGridLinesVisible(true);
        for (int col = 0; col < size; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(50);
            RowConstraints rowConstraints = new RowConstraints(50);

            gridpane.getColumnConstraints().add(columnConstraints);
            gridpane.getRowConstraints().add(rowConstraints);

            if(col%2==0){
                for (int row = 0; row < size; row+=2) {
                    gridpane.add(generateDarkCell(),col,row);
                }
            }
            else {
                for (int row = 1; row < size; row+=2) {
                    gridpane.add(generateDarkCell(),col,row);
                }

            }
        }

        menuView.getChildren().add(gridpane);
    }

    private Rectangle generateDarkCell(){
        Rectangle rectangle = new Rectangle(50,50);
        rectangle.fillProperty().set(new Color(0,0,0,1));
        return rectangle;
    }
}

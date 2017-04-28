package sample.Controller;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.dames.Dame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
    private ImageView iv_icon_1;
    @FXML
    private ImageView iv_icon_2;
    @FXML
    private VBox vb_menuView;

    private Board board;
    private GridPane gridpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iv_icon_1.setImage(new Image("/sample/Resources/img_board.png"));
        iv_icon_2.setImage(new Image("/sample/Resources/img_board.png"));
        initBoard();
        showQueens();
    }

    public BoardController(Board board) {
        iv_icon_1 = new ImageView();
        iv_icon_2 = new ImageView();
        vb_menuView = new VBox();
        this.board = board;
    }

    private void initBoard(){
        int size = board.getSize();
        gridpane = new GridPane();
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
        vb_menuView.getChildren().add(gridpane);
    }

    private Rectangle generateDarkCell(){
        Rectangle rectangle = new Rectangle(50,50);
        rectangle.fillProperty().set(new Color(0.5,0.5,0.5,1));
        return rectangle;
    }

    private void showQueens(){
        for (int i = 0; i < board.getSize() ; i++) {
            ImageView imageView = new ImageView();
            if (!board.getDames().get(i).isConflict())
            {
                Image image = new Image("/sample/Resources/crown.png");
                imageView.setImage(image);
            }
            else {
                Image image = new Image("/sample/Resources/crown_conflict.png");
                imageView.setImage(image);
            }
            int col = board.getDames().get(i).getColumn();
            gridpane.add(imageView,col,i);
        }
    }
}

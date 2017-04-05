package sample.Utils;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by jeremy on 22/03/2017.
 */
public class SettingsBuilder {

    private Map<String, Control> settings;


    public SettingsBuilder() {
        settings = new HashMap<>();
    }

    public SettingsBuilder add(String label, Control control){
        settings.put(label, control);
        return this;
    }

    public GridPane build(){
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(45);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(8));
        gridPane.getColumnConstraints().add(column1);
        int row = 0;
        for (Map.Entry<String,Control> entry : settings.entrySet())
        {
            buildInput(gridPane,entry.getKey(),entry.getValue(),row++);
        }
        return gridPane;
    }

    private void buildInput(GridPane grid, String label, Control control, int row)
    {
        grid.add(new Label(label),0,row);
        grid.add(control,1,row);

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPrefHeight(30);
        grid.getRowConstraints().add(rowConstraint);

    }

}

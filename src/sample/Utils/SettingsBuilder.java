package sample.Utils;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.Controller.SettingsController;
import sample.setttings.NumberTextFieldSettingsInput;
import sample.setttings.SettingsInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by jeremy on 22/03/2017.
 */
public class SettingsBuilder {

    private List<SettingsInput> inputs;

    public SettingsBuilder() {
        inputs = new ArrayList<>();
    }

    public SettingsBuilder add(String label, String description){
        inputs.add(new NumberTextFieldSettingsInput(label, description));
        return this;
    }

    public SettingsBuilder add(SettingsInput input){
        inputs.add(input);
        return this;
    }

    public GridPane build(SettingsController settingsController){
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(45);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(8));
        gridPane.getColumnConstraints().add(column1);
        int row = 0;
        for (SettingsInput input : inputs)
        {
            buildInput(settingsController, gridPane, input, row++);
        }
        return gridPane;
    }

    private void buildInput(SettingsController settingsController, GridPane grid, SettingsInput input, int row)
    {
        grid.add(new Label(input.getLabel()),0,row);
        grid.add(input.getControl(),1,row);

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPrefHeight(30);
        grid.getRowConstraints().add(rowConstraint);

        input.setController(settingsController);

        try {
            input.updateControlFromResolver(settingsController.getResolver());
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

}

package sample.Utils;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.Controller.EvolvingController;
import sample.Controller.SettingsController;
import sample.setttings.NumberTextFieldSettingsInput;
import sample.setttings.SettingsInput;
import sample.setttings.evolving.EvolvingInput;
import sample.setttings.evolving.IntegerLabelSettingsInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by kifkif on 07/04/2017.
 */
public class EvolvingInputBuilder<R extends Resolver<R>> {

    private List<EvolvingInput<R, ?, ?>> inputs;

    public EvolvingInputBuilder() {
        inputs = new ArrayList<>();
    }

    public EvolvingInputBuilder add(String label, Function<R, Integer> getter){
        inputs.add(new IntegerLabelSettingsInput<R>(label, getter));
        return this;
    }

    public EvolvingInputBuilder add(EvolvingInput<R, ?, ?> input){
        inputs.add(input);
        return this;
    }

    public GridPane build(EvolvingController evolvingController){
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(23);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(8));
        gridPane.getColumnConstraints().add(column1);
        int row = 0;
        int col = 0;
        for(EvolvingInput input : inputs) {
            buildInput(evolvingController, gridPane, input, row, col);
            col += 2;
            if(col > 2)
            {
                row++;
                col = 0;
            }
        }
        return gridPane;
    }

    private void buildInput(EvolvingController evolvingController, GridPane grid, EvolvingInput input, int row, int col)
    {
        grid.add(new Label(input.getLabel()),col,row);
        grid.add(input.getControl(),col+1,row);

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPrefHeight(30);
        grid.getRowConstraints().add(rowConstraint);

        try {
            input.updateControlFromResolver(evolvingController.getResolver());
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public void updateInputs(R resolver)
    {
        for(EvolvingInput input : inputs) {
            try {
                input.updateControlFromResolver(resolver);
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
    }
}

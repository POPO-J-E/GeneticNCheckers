package sample.controls.factory;

import javafx.scene.control.Control;

/**
 * Created by kifkif on 05/04/2017.
 */
public interface ControlFactory<C extends Control> {
    C generate();
}

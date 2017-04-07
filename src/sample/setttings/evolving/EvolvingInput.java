package sample.setttings.evolving;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import sample.Utils.Resolver;
import sample.controls.factory.ControlFactory;
import sample.setttings.SettingsInput;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by kifkif on 07/04/2017.
 */
public class EvolvingInput <R extends Resolver<R>, C extends Control, V> extends SettingsInput<R, C, V> {

    public EvolvingInput(String label, String description, ControlFactory<C> factory, Function<R, V> resolverValueGetter, BiConsumer<C, V> controlValueSetter) {
        super(label, description, factory, null, controlValueSetter, resolverValueGetter, null);
    }
}

package sample.setttings;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import sample.Utils.Resolver;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by kifkif on 05/04/2017.
 */
public class TextFieldSettingsInput<R extends Resolver<R>> extends SettingsInput<R, TextField, String> {

    public TextFieldSettingsInput(String label, String description,  Function<R, String> resolverValueGetter, BiConsumer<R, String> resolverValueSetter) {
        super(label, description, TextField::new, TextInputControl::getText, TextInputControl::setText, resolverValueGetter, resolverValueSetter);

        getControl().setOnMouseClicked(event -> this.notifyDisplayDescription());
    }

    public TextFieldSettingsInput(String label, String description) {
        this(label, description, null, null);
    }
}

package sample.setttings;

import javafx.scene.control.TextInputControl;
import sample.Utils.Resolver;
import sample.controls.NumberTextField;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by kifkif on 05/04/2017.
 */
public class IntegerTextFieldSettingsInput<R extends Resolver> extends SettingsInput<R, NumberTextField, Integer> {

    public IntegerTextFieldSettingsInput(String label, String description, Function<R, Integer> resolverValueGetter, BiConsumer<R, Integer> resolverValueSetter) {
        super(label, description, NumberTextField::new, c->Integer.valueOf(c.getText()), (c, v)->c.setText(v.toString()), resolverValueGetter, resolverValueSetter);

        getControl().setOnMouseClicked(event -> this.notifyDisplayDescription());
    }

    public IntegerTextFieldSettingsInput(String label, String description) {
        this(label, description, null, null);
    }
}

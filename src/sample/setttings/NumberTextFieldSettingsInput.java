package sample.setttings;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import sample.Utils.Resolver;
import sample.controls.NumberTextField;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by kifkif on 05/04/2017.
 */
public class NumberTextFieldSettingsInput<R extends Resolver<R>> extends SettingsInput<R, NumberTextField, Float> {

    public NumberTextFieldSettingsInput(String label, String description, Function<R, Float> resolverValueGetter, BiConsumer<R, Float> resolverValueSetter) {
        super(label, description, NumberTextField::new, c->Float.valueOf(c.getText()), (c, v)->c.setText(v.toString()), resolverValueGetter, resolverValueSetter);

        getControl().setOnMouseClicked(event -> this.notifyDisplayDescription());
    }

    public NumberTextFieldSettingsInput(String label, String description) {
        this(label, description, null, null);
    }
}

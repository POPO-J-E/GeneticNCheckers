package sample.setttings.evolving;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import sample.Utils.Resolver;
import sample.setttings.SettingsInput;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by kifkif on 07/04/2017.
 */
public class LabelSettingsInput<R extends Resolver<R>, V> extends EvolvingInput<R, Label, V> {

    public LabelSettingsInput(String label, String description, Function<R, V> resolverValueGetter, Function<V, String> valueConverter) {
        super(label, description, Label::new, resolverValueGetter, (c, v)->c.setText(valueConverter.apply(v)));
    }
}

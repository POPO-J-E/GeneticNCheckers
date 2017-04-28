package sample.setttings.evolving;

import sample.Utils.Resolver;

import java.util.function.Function;

/**
 * Created by kifkif on 07/04/2017.
 */
public class NumberLabelSettingsInput<R extends Resolver<R>> extends LabelSettingsInput<R, Float> {
    public NumberLabelSettingsInput(String label, String description, Function<R, Float> resolverValueGetter) {
        super(label, description, resolverValueGetter, String::valueOf);
    }

    public NumberLabelSettingsInput(String label, Function<R, Float> resolverValueGetter) {
        this(label, "", resolverValueGetter);
    }
}

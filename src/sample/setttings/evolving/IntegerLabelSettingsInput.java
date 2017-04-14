package sample.setttings.evolving;

import sample.Utils.Resolver;

import java.util.function.Function;

/**
 * Created by kifkif on 07/04/2017.
 */
public class IntegerLabelSettingsInput<R extends Resolver<R>> extends LabelSettingsInput<R, Integer> {
    public IntegerLabelSettingsInput(String label, String description, Function<R, Integer> resolverValueGetter) {
        super(label, description, resolverValueGetter, String::valueOf);
    }

    public IntegerLabelSettingsInput(String label, Function<R, Integer> resolverValueGetter) {
        this(label, "", resolverValueGetter);
    }
}

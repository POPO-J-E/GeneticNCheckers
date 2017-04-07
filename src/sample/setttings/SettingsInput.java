package sample.setttings;

import javafx.application.Platform;
import javafx.scene.control.Control;
import sample.Controller.SettingsController;
import sample.Utils.Resolver;
import sample.controls.factory.ControlFactory;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by kifkif on 05/04/2017.
 */
public class SettingsInput<R extends Resolver<R>, C extends Control, V>
{
    private String label;
    private String description;

    private C control;

    private Function<C, V> controlValueGetter;
    private BiConsumer<C, V> controlValueSetter;

    private Function<R, V> resolverValueGetter;
    private BiConsumer<R, V> resolverValueSetter;

    private SettingsController<R> controller;

    private SettingsInput(String label, String description, C control, Function<C, V> controlValueGetter, BiConsumer<C, V> controlValueSetter, Function<R, V> resolverValueGetter, BiConsumer<R, V> resolverValueSetter) {
        this.label = label;
        this.description = description;
        this.control = control;
        this.controlValueGetter = controlValueGetter;
        this.controlValueSetter = controlValueSetter;
        this.resolverValueGetter = resolverValueGetter;
        this.resolverValueSetter = resolverValueSetter;

        control.setOnMouseDragEntered(event -> this.notifyDisplayDescription());
    }

    public SettingsInput(String label, String description, C control) {
        this(label, description, control, null, null, null, null);
    }

    public SettingsInput(String label, String description) {
        this(label, description, (C) null);
    }

    public SettingsInput(String label, String description, ControlFactory<C> controlFactory, Function<C, V> controlValueGetter, BiConsumer<C, V> controlValueSetter, Function<R, V> resolverValueGetter, BiConsumer<R, V> resolverValueSetter) {
        this(label, description, controlFactory.generate(), controlValueGetter, controlValueSetter, resolverValueGetter, resolverValueSetter);
    }

    public SettingsInput(String label, String description, ControlFactory<C> controlFactory) {
        this(label, description, controlFactory, null,  null, null, null);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public C getControl() {
        return control;
    }

    public SettingsInput<R, C, V> setControl(C control) {
        this.control = control;
        return this;
    }

    public SettingsInput<R, C, V> setControl(ControlFactory<C> controlFactory) {
        return setControl(controlFactory.generate());
    }

    public Function<C, V> getControlValueGetter() {
        return controlValueGetter;
    }

    public SettingsInput<R, C, V> setControlValueGetter(Function<C, V> controlValueGetter) {
        this.controlValueGetter = controlValueGetter;
        return this;
    }

    public Function<R, V> getResolverValueGetter() {
        return resolverValueGetter;
    }

    public SettingsInput<R, C, V> setResolverValueGetter(Function<R, V> resolverValueGetter) {
        this.resolverValueGetter = resolverValueGetter;
        return this;
    }

    public BiConsumer<R, V> getResolverValueSetter() {
        return resolverValueSetter;
    }

    public SettingsInput<R, C, V> setResolverValueSetter(BiConsumer<R, V> resolverValueSetter) {
        this.resolverValueSetter = resolverValueSetter;
        return this;
    }

    public void updateResolverFromControl(R resolver) throws NullPointerException
    {
        this.resolverValueSetter.accept(resolver, this.getControlValue());
    }

    public V getResolverValue(R resolver) throws NullPointerException
    {
        return this.resolverValueGetter.apply(resolver);
    }

    public void updateControlFromResolver(R resolver) throws NullPointerException
    {
        Platform.runLater(() -> controlValueSetter.accept(control, getResolverValue(resolver)));
    }

    public V getControlValue() throws NullPointerException
    {
        return this.controlValueGetter.apply(this.control);
    }

    public void notifyDisplayDescription()
    {
        try {
            controller.onDisplayDescription(this);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public void setController(SettingsController<R> controller) {
        this.controller = controller;
    }
}

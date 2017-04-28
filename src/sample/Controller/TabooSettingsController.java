package sample.Controller;

import com.polytech.ndames.recuit.Recuit;
import com.polytech.ndames.tabou.Tabou;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Utils.SettingsBuilder;
import sample.setttings.IntegerTextFieldSettingsInput;
import sample.setttings.NumberTextFieldSettingsInput;

/**
 *
 * Created by jeremy on 21/03/2017.
 */
public class TabooSettingsController extends SettingsController<Tabou> {
    public TabooSettingsController() {
        super("Taboo SettingsInput","/sample/Resources/img_list.png", Tabou::new, TabooEvolvingController::new);
    }

    @Override
    public void buildSettings(SettingsBuilder builder) {
        builder.add(new IntegerTextFieldSettingsInput<Tabou>("Board size", "The board size is...........")
                .setResolverValueGetter(Tabou::getSize)
                .setResolverValueSetter(Tabou::setSize)
            )
            .add(new IntegerTextFieldSettingsInput<Tabou>("Number Of Iterations", "The board size is...........")
                    .setResolverValueGetter(Tabou::getNbIteration)
                    .setResolverValueSetter(Tabou::setNbIteration)
            )
            .add(new IntegerTextFieldSettingsInput<Tabou>("Taboo List Size", "The board size is...........")
                    .setResolverValueGetter(Tabou::getTabouSize)
                    .setResolverValueSetter(Tabou::setTabouSize)
            )
        ;
    }
}

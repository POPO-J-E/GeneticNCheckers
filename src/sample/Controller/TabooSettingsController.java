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
        builder.add(new IntegerTextFieldSettingsInput<Tabou>("Board size", "The board size is the height or the width of the checker board. there will be the same number of queens than the board size")
                .setResolverValueGetter(Tabou::getSize)
                .setResolverValueSetter(Tabou::setSize)
            )
            .add(new IntegerTextFieldSettingsInput<Tabou>("Number Of Iterations", "The number of iterations is the number of time that the search will be done")
                    .setResolverValueGetter(Tabou::getNbIteration)
                    .setResolverValueSetter(Tabou::setNbIteration)
            )
            .add(new IntegerTextFieldSettingsInput<Tabou>("Taboo List Size", "The Taboo list size is the number that will be possible to save in the taboo algorithm")
                    .setResolverValueGetter(Tabou::getTabouSize)
                    .setResolverValueSetter(Tabou::setTabouSize)
            )
        ;
    }
}

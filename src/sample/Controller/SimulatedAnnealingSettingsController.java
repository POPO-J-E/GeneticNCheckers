package sample.Controller;


import com.polytech.ndames.recuit.Recuit;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Utils.SettingsBuilder;
import sample.setttings.IntegerTextFieldSettingsInput;
import sample.setttings.NumberTextFieldSettingsInput;

/**
 *
 * Created by jeremy on 21/03/2017.
 */
public class SimulatedAnnealingSettingsController extends SettingsController<Recuit> {
    public SimulatedAnnealingSettingsController() {
        super("Simulated Annealing SettingsInput","/sample/Resources/img_thermometer.png", Recuit::new, SimulatedAnnealingEvolvingController::new);
    }

    @Override
    public void buildSettings(SettingsBuilder builder)
    {
        builder.add(new IntegerTextFieldSettingsInput<Recuit>("Board size", "The board size is...........")
                .setResolverValueGetter(Recuit::getSize)
                .setResolverValueSetter(Recuit::setSize)
            )
            .add(new IntegerTextFieldSettingsInput<Recuit>("Number Of Iterations", "The board size is...........")
                .setResolverValueGetter(Recuit::getNbIteration)
                .setResolverValueSetter(Recuit::setNbIteration)
            )
            .add(new NumberTextFieldSettingsInput<Recuit>("Alpha", "The board size is...........")
                    .setResolverValueGetter(Recuit::getAlpha)
                    .setResolverValueSetter(Recuit::setAlpha)
            )
        ;
    }
}

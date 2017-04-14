package sample.Controller;

import com.polytech.ndames.recuit.Recuit;
import sample.Utils.SettingsBuilder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * Created by jeremy on 21/03/2017.
 */
public class GeneticSettingsController extends SettingsController {
    public GeneticSettingsController() {
        super("Genetic SettingsInput","/sample/Resources/img_dna.png", Recuit::new, SimulatedAnnealingEvolvingController::new);
    }

    @Override
    public void buildSettings(SettingsBuilder builder) {

    }
}

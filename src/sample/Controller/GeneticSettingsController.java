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
        super("Genetic Settings","/sample/Resources/img_dna.png", Recuit::new);
    }

    @Override
    public void buildSettings(SettingsBuilder builder) {

    }
}

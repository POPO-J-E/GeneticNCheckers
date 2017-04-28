package sample.Controller;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.recuit.Recuit;
import sample.Utils.EvolvingInputBuilder;
import sample.setttings.evolving.IntegerLabelSettingsInput;
import sample.setttings.evolving.NumberLabelSettingsInput;

/**
 * Created by kifkif on 07/04/2017.
 */
public class SimulatedAnnealingEvolvingController extends EvolvingController<Recuit> {

    @Override
    public void buildSettings(EvolvingInputBuilder<Recuit> builder) {
        builder.add("Board Size", Recuit::getSize);
        builder.add(new NumberLabelSettingsInput<>("Best Fitness", Recuit::getBestFitness));
        builder.add(new NumberLabelSettingsInput<>("Temperature", Recuit::getTemperature));
        builder.add(new NumberLabelSettingsInput<>("DeltaF", Recuit::getDeltaf));
    }
}

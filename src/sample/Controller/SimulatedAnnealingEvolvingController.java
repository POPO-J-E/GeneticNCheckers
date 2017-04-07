package sample.Controller;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.recuit.Recuit;
import sample.Utils.EvolvingInputBuilder;
import sample.setttings.evolving.IntegerLabelSettingsInput;

/**
 * Created by kifkif on 07/04/2017.
 */
public class SimulatedAnnealingEvolvingController extends EvolvingController<Recuit> {

    @Override
    public void buildSettings(EvolvingInputBuilder<Recuit> builder) {
        builder.add("Size", Recuit::getSize);
        builder.add("Best fitness", Recuit::getBestFitness);
    }

    @Override
    protected void updateInfos(Board board) {
        System.out.println("coucouc!");
    }
}

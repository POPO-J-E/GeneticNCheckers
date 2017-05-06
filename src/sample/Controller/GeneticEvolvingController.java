package sample.Controller;

import com.polytech.ndames.genetic.GeneticResolver;
import com.polytech.ndames.tabou.Tabou;
import sample.Utils.EvolvingInputBuilder;
import sample.setttings.IntegerTextFieldSettingsInput;
import sample.setttings.NumberTextFieldSettingsInput;
import sample.setttings.evolving.IntegerLabelSettingsInput;
import sample.setttings.evolving.NumberLabelSettingsInput;

/**
 * Created by kifkif on 28/04/2017.
 */
public class GeneticEvolvingController extends EvolvingController<GeneticResolver> {

    public GeneticEvolvingController() {
        super(0.01);
    }

    @Override
    public void buildSettings(EvolvingInputBuilder<GeneticResolver> builder) {
        builder.add("Board Size", GeneticResolver::getBoardSize);
        builder.add(new NumberLabelSettingsInput<>("Best Fitness", GeneticResolver::getBestFitness));
        builder.add(new IntegerLabelSettingsInput<>("Number of Generations", GeneticResolver::getNbGen));
        builder.add(new NumberLabelSettingsInput<>("Mutation Rate", GeneticResolver::getMutationRate));
    }
}

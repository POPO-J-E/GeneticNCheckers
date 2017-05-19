package sample.Controller;

import com.polytech.ndames.genetic.GeneticResolver;
import sample.Utils.EvolvingInputBuilder;
import sample.setttings.evolving.IntegerLabelSettingsInput;
import sample.setttings.evolving.NumberLabelSettingsInput;

/**
 * Created by kifkif on 28/04/2017.
 */
public class GeneticEvolvingController extends EvolvingController<GeneticResolver> {

    public GeneticEvolvingController() {
        super(500, 0.01);
    }

    @Override
    public void buildSettings(EvolvingInputBuilder<GeneticResolver> builder) {
        builder.add("Board Size", GeneticResolver::getBoardSize);
        builder.add(new NumberLabelSettingsInput<>("Best Fitness", GeneticResolver::getBestFitness));
        builder.add(new IntegerLabelSettingsInput<>("Number of Generations", GeneticResolver::getNbGen));
        builder.add(new NumberLabelSettingsInput<>("Mutation Rate", GeneticResolver::getMutationRate));
        builder.add(new IntegerLabelSettingsInput<>("Time (Millis)", r->(int)r.getMillisExecutionTime()));
    }
}

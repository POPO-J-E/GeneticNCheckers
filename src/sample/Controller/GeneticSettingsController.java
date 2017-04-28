package sample.Controller;

import com.polytech.ndames.genetic.GeneticResolver;
import com.polytech.ndames.recuit.Recuit;
import com.polytech.ndames.tabou.Tabou;
import sample.Utils.SettingsBuilder;
import sample.setttings.IntegerTextFieldSettingsInput;
import sample.setttings.NumberTextFieldSettingsInput;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * Created by jeremy on 21/03/2017.
 */
public class GeneticSettingsController extends SettingsController<GeneticResolver> {
    public GeneticSettingsController() {
        super("Genetic SettingsInput","/sample/Resources/img_dna.png", GeneticResolver::new, GeneticEvolvingController::new);
    }

    @Override
    public void buildSettings(SettingsBuilder builder) {
        builder.add(new IntegerTextFieldSettingsInput<GeneticResolver>("Board size", "The board size is...........")
                .setResolverValueGetter(GeneticResolver::getBoardSize)
                .setResolverValueSetter(GeneticResolver::setBoardSize)
        )
                .add(new IntegerTextFieldSettingsInput<GeneticResolver>("Population Size", "The board size is...........")
                        .setResolverValueGetter(GeneticResolver::getPopulationSize)
                        .setResolverValueSetter(GeneticResolver::setPopulationSize)
                )
                .add(new IntegerTextFieldSettingsInput<GeneticResolver>("Number of Generations", "The board size is...........")
                        .setResolverValueGetter(GeneticResolver::getNbGen)
                        .setResolverValueSetter(GeneticResolver::setNbGen)
                )
                .add(new NumberTextFieldSettingsInput<GeneticResolver>("Mutation Rate", "The board size is...........")
                        .setResolverValueGetter(GeneticResolver::getMutationRate)
                        .setResolverValueSetter(GeneticResolver::setMutationRate)
                )

        ;
    }
}

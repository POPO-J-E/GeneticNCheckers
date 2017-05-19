package sample.Controller;

import com.polytech.ndames.tabou.Tabou;
import sample.Utils.EvolvingInputBuilder;
import sample.setttings.evolving.IntegerLabelSettingsInput;
import sample.setttings.evolving.NumberLabelSettingsInput;

/**
 * Created by kifkif on 07/04/2017.
 */
public class TabooEvolvingController extends EvolvingController<Tabou> {

    public TabooEvolvingController() {
        super(2);
    }

    @Override
    public void buildSettings(EvolvingInputBuilder<Tabou> builder) {
        builder.add("Board Size", Tabou::getSize);
        builder.add(new NumberLabelSettingsInput<>("Best Fitness", Tabou::getBestFitness));
        builder.add("Taboo List Size", Tabou::getTabouSize);
        builder.add("Number Forbidden Move(s)", t -> {
            try{
                return t.getFifo().size();
            }
            catch (NullPointerException e)
            {
                return 0;
            }
        });
        builder.add(new IntegerLabelSettingsInput<>("Time (Millis)", r->(int)r.getMillisExecutionTime()));
    }
}

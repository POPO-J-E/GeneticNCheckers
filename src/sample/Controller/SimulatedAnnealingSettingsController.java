package sample.Controller;


import com.polytech.ndames.recuit.Recuit;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Utils.SettingsBuilder;
import sample.setttings.IntegerTextFieldSettingsInput;

/**
 *
 * Created by jeremy on 21/03/2017.
 */
public class SimulatedAnnealingSettingsController extends SettingsController<Recuit> {
    public SimulatedAnnealingSettingsController() {
        super("Simulated Annealing SettingsInput","/sample/Resources/img_thermometer.png", Recuit::new, SimulatedAnnealingEvolvingController::new);
    }

    @Override
    public void buildSettings(SettingsBuilder builder) {
        TextField tf_size = new TextField();
        tf_size.setOnMouseClicked(this::onSizeChanged);
        tf_size.setOnMouseClicked(e-> setTitleAndDescription("Board Size","The board size is..........."));
        
        TextField tf_iterations = new TextField();
        tf_iterations.setOnMouseClicked(this::onIterationsChanged);
        tf_iterations.setOnMouseClicked(e-> setTitleAndDescription("Number Of Iterations","The number of iterations is.........."));

        TextField tf_alpha = new TextField();
        tf_alpha.setOnMouseClicked(this::onAlphaChanged);
        tf_alpha.setOnMouseClicked(e-> setTitleAndDescription("Alpha","The alpha constant is.........."));

//        builder.add("Board size", tf_size)
//            .add("Number of iterations", tf_iterations)
//            .add("Alpha", tf_alpha);

        builder.add(new IntegerTextFieldSettingsInput<Recuit>("Board size", "The board size is...........")
                .setResolverValueGetter(Recuit::getSize)
                .setResolverValueSetter(Recuit::setSize)
            )
            //.add("Number of iterations", "The number of iterations is..........")
            //.add("Alpha", "The alpha constant is..........")
        ;
    }

    private void onAlphaChanged(MouseEvent actionEvent) {
        System.out.println("alpha");
    }

    private void onSizeChanged(MouseEvent actionEvent){
        System.out.println("Size");
    }
    
    private void onIterationsChanged(MouseEvent actionEvent){
        System.out.println("iteration");
    }

}

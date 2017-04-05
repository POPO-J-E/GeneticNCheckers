package sample.Controller;

import com.polytech.ndames.tabou.Tabou;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Utils.SettingsBuilder;

/**
 *
 * Created by jeremy on 21/03/2017.
 */
public class TabooSettingsController extends SettingsController<Tabou> {
    public TabooSettingsController() {
        super("Taboo SettingsInput","/sample/Resources/img_list.png", Tabou::new);
    }

    @Override
    public void buildSettings(SettingsBuilder builder) {
        TextField tf_size = new TextField();
        tf_size.setOnMouseClicked(this::onSizeChanged);
        tf_size.setOnMouseClicked(e-> setTitleAndDescription("Board Size","The board size is..........."));

        TextField tf_tabooListSize = new TextField();
        tf_tabooListSize.setOnMouseClicked(this::onTabooListSizeChanged);
        tf_tabooListSize.setOnMouseClicked(e-> setTitleAndDescription("Taboo list size","The Taboo list size is.........."));

        TextField tf_alpha = new TextField();
        tf_alpha.setOnMouseClicked(this::onAlphaChanged);
        tf_alpha.setOnMouseClicked(e-> setTitleAndDescription("Alpha","The alpha constant is.........."));

//        builder.add("Board size", tf_size)
//                .add("Taboo list size", tf_tabooListSize)
//                .add("Alpha", tf_alpha);
    }

    private void onTabooListSizeChanged(MouseEvent mouseEvent) {
        System.out.println("taboo size");
    }

    private void onAlphaChanged(MouseEvent actionEvent) {
        System.out.println("alpha");
    }

    private void onSizeChanged(MouseEvent actionEvent){
        System.out.println("Size");
    }

}

package sample.Controller.factory;

import sample.Controller.EvolvingController;

/**
 * Created by kifkif on 06/04/2017.
 */
public interface EvolvingControllerFactory<E extends EvolvingController> {
    E generate();
}

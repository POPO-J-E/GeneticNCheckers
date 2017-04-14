package sample.Controller.factory;

import sample.Controller.EvolvingController;
import sample.Utils.Resolver;

/**
 * Created by kifkif on 06/04/2017.
 */
public interface EvolvingControllerFactory<R extends Resolver<R>> {
    EvolvingController<R> generate();
}

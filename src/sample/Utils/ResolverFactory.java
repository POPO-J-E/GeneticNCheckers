package sample.Utils;

/**
 * Created by jeremy on 23/03/2017.
 */
public interface ResolverFactory<R extends Resolver> {
    public R generateResolver();
}

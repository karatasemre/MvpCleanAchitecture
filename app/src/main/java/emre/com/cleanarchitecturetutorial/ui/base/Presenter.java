package emre.com.cleanarchitecturetutorial.ui.base;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class Presenter<V> {

    private V view;

    public void bindView(V view){
        this.view = view;
    }

    public void unbindView(){
        view = null;
    }

    public V getView(){
        return view;
    }
}

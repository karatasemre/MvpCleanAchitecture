package emre.com.cleanarchitecturetutorial.ui.main;

import java.util.List;

import emre.com.cleanarchitecturetutorial.data.model.Item;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public interface MainView {

    void showLoadingProgress(boolean show);

    void showItems(List<Item> items);

    void showItemsLoadingError();
}

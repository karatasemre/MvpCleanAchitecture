package emre.com.cleanarchitecturetutorial.ui.detail;

import emre.com.cleanarchitecturetutorial.data.model.ItemDetails;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public interface DetailView {
    void showLoadingProgress(boolean show);

    void showItemDetails(ItemDetails itemDetails);

    void showItemsLoadingError();
}

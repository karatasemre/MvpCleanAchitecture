package emre.com.cleanarchitecturetutorial.ui.detail;

import javax.inject.Inject;

import emre.com.cleanarchitecturetutorial.data.api.RestApi;
import emre.com.cleanarchitecturetutorial.data.model.Item;
import emre.com.cleanarchitecturetutorial.data.model.ItemDetails;
import emre.com.cleanarchitecturetutorial.ui.base.Presenter;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class DetailPresenter extends Presenter<DetailView> {

    private final RestApi restApi;
    private Subscription subscription;

    @Inject
    public DetailPresenter(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public void unbindView() {
        super.unbindView();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void loadItemDetails(final Item item) {
        if (item == null) {
            getView().showItemsLoadingError();
            return;
        }

        getView().showLoadingProgress(true);

        subscription = restApi.getItemDetails(item.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<ItemDetails>() {
                    @Override
                    public void onSuccess(ItemDetails itemDetails) {
                        getView().showLoadingProgress(false);
                        getView().showItemDetails(itemDetails);
                    }

                    @Override
                    public void onError(Throwable error) {
                        getView().showLoadingProgress(false);
                        getView().showItemsLoadingError();
                    }
                });
    }
}

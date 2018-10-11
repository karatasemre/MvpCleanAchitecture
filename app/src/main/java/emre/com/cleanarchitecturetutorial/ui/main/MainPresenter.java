package emre.com.cleanarchitecturetutorial.ui.main;

import javax.inject.Inject;

import emre.com.cleanarchitecturetutorial.data.api.RestApi;
import emre.com.cleanarchitecturetutorial.data.model.Items;
import emre.com.cleanarchitecturetutorial.ui.base.Presenter;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class MainPresenter extends Presenter<MainView> {

    private final RestApi restApi;
    private Subscription subscription;

    @Inject
    public MainPresenter(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public void unbindView() {
        super.unbindView();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void loadItems() {
        getView().showLoadingProgress(true);

        subscription = restApi.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Items>() {
                    @Override
                    public void onSuccess(Items items) {
                        getView().showLoadingProgress(false);
                        getView().showItems(items.getItems());
                    }

                    @Override
                    public void onError(Throwable error) {
                        getView().showLoadingProgress(false);
                        getView().showItemsLoadingError();
                    }
                });
    }
}

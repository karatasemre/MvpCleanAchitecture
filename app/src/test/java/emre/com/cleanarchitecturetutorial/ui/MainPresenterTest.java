package emre.com.cleanarchitecturetutorial.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import emre.com.cleanarchitecturetutorial.api.MockRestApi;
import emre.com.cleanarchitecturetutorial.data.api.RestApi;
import emre.com.cleanarchitecturetutorial.data.model.Items;
import emre.com.cleanarchitecturetutorial.ui.main.MainPresenter;
import emre.com.cleanarchitecturetutorial.ui.main.MainView;
import emre.com.cleanarchitecturetutorial.util.TestRxSchedulersHooks;
import rx.Single;

/**
 * Created by Emre.Karatas on 11.10.2018.
 */

public class MainPresenterTest {

    private RestApi restApi;
    private MainPresenter mainPresenter;
    private MainView mainView;

    private TestRxSchedulersHooks testRxSchedulersHooks = new TestRxSchedulersHooks();


    @Before
    public void beforeEachTest(){
        testRxSchedulersHooks.registerHooks();

        restApi = Mockito.mock(RestApi.class);
        mainPresenter = new MainPresenter(restApi);
        mainView = Mockito.mock(MainView.class);

        mainPresenter.bindView(mainView);
    }

    @After
    public void afterEachTest(){
        testRxSchedulersHooks.unregisterHooks();
    }

    @Test
    public void loadItems_shouldSendDataToTheView(){
        Items items = new Items(MockRestApi.TEST_ITEMS);

        Mockito.when(restApi.getItems()).thenReturn(Single.just(items));

        mainPresenter.loadItems();

        Mockito.verify(mainView).showLoadingProgress(true);
        Mockito.verify(mainView).showLoadingProgress(false);
        Mockito.verify(mainView).showItems(MockRestApi.TEST_ITEMS);
        Mockito.verify(mainView, Mockito.never()).showItemsLoadingError();
    }
}

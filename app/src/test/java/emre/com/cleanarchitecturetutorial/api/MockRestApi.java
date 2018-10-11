package emre.com.cleanarchitecturetutorial.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import emre.com.cleanarchitecturetutorial.data.api.RestApi;
import emre.com.cleanarchitecturetutorial.data.model.Item;
import emre.com.cleanarchitecturetutorial.data.model.ItemDetails;
import emre.com.cleanarchitecturetutorial.data.model.Items;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import rx.Single;

/**
 * Created by Emre.Karatas on 11.10.2018.
 */

public class MockRestApi implements RestApi {

    public static final List<Item> TEST_ITEMS = Arrays.asList(
            new Item("charmender", RestApi.BASE_URL+"/pokemon/4/"),
            new Item("charmeleon", RestApi.BASE_URL+"/pokemon/5/"),
            new Item("charizard", RestApi.BASE_URL+"/pokemon/6/")
    );

    public static final Item TEST_ITEM = new Item("squirtle", RestApi.BASE_URL+ "/pokemon/7/");

    public static final ItemDetails TEST_ITEM_DETAILS = new ItemDetails();

    private final BehaviorDelegate<RestApi> delegate;

    public MockRestApi(BehaviorDelegate<RestApi> delegate){
        this.delegate = delegate;
    }

    @Override
    public Single<Items> getItems() {
        Items items = new Items(TEST_ITEMS);
        return delegate.returningResponse(items).getItems();
    }

    @Override
    public Single<ItemDetails> getItemDetails(int itemId) {
        return delegate.returningResponse(TEST_ITEMS).getItemDetails(itemId);
    }

    public static class Factory{
        public static RestApi createRestApi(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestApi.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            NetworkBehavior behavior = NetworkBehavior.create();

            MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                    .networkBehavior(behavior)
                    .build();

            BehaviorDelegate<RestApi> delegate = mockRetrofit.create(RestApi.class);
            return new MockRestApi(delegate);
        }
    }
}

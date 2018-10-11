package emre.com.cleanarchitecturetutorial.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import emre.com.cleanarchitecturetutorial.data.api.RestApi;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    RestApi provideRestApi(){
        return RestApi.Factory.createRestApi();
    }

}
